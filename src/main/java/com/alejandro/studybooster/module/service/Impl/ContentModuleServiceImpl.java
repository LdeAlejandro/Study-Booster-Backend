package com.alejandro.studybooster.module.service.Impl;

import com.alejandro.studybooster.module.controller.dto.ContentModule.CreateContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.GetContentModuleDTO;
import com.alejandro.studybooster.module.controller.dto.ContentModule.UpdateContentModuleDTO;
import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Doc;
import com.alejandro.studybooster.module.entity.Question;
import com.alejandro.studybooster.module.entity.Subject;
import com.alejandro.studybooster.module.repository.ContentModuleRepository;
import com.alejandro.studybooster.module.repository.SubjectRepository;
import com.alejandro.studybooster.module.service.ContentModuleService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentModuleServiceImpl implements ContentModuleService {

    private final SubjectRepository subjectRepository;
    private final ContentModuleRepository contentModuleRepository;

    public ContentModuleServiceImpl(SubjectRepository subjectRepository, ContentModuleRepository contentModuleRepository) {
        this.subjectRepository = subjectRepository;
        this.contentModuleRepository =  contentModuleRepository;
    }


    // get subjects modules pageable
    @Override
    public Page<GetContentModuleDTO> getPageModules(Long subjectId, @PageableDefault(size = 16, page = 0) Pageable pageable) {

        // limit page elements to 16
        //create a sage pagination
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(), // check number of page in the request
                Math.min(pageable.getPageSize(), 16), // limit to 16
                Sort.by(Sort.Direction.DESC, "id") // sort by id decrecent

        );

        // get modules
        Page <ContentModule> modules = contentModuleRepository.findBySubjectId(subjectId, safePageable);

        //map module to return object
        return modules.map(module -> new GetContentModuleDTO(
                module.getId(),
                module.getName(),
                module.getSubject().getId(),
                module.getParent() != null ? module.getParent().getId() : null, // check if parent is null and return null if it is
                module.getChildren().stream().map(ContentModule::getId).collect(Collectors.toSet()), // get module children ids
                module.getQuestions().stream().map(Question::getId).collect(Collectors.toSet()), // get module question ids
                module.getDocs().stream().map(Doc::getId).collect(Collectors.toSet())

        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getModuleById(Long moduleId) {

        Optional <ContentModule> targetModule = contentModuleRepository.findById(moduleId);

        Map<String, Object> response = new HashMap<>();

        // if empty return an error
        if(targetModule.isEmpty()){

            response.put("message", "Module not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

        //create a new module object that stores the data
        ContentModule module = targetModule.get();

        // map dto to return data
        GetContentModuleDTO moduleDTO = new GetContentModuleDTO(
                module.getId(),
                module.getName(),
                module.getSubject().getId(),
                module.getParent() != null ? module.getParent().getId() : null, // check if parent is null and return null if it is
                module.getChildren().stream().map(ContentModule::getId).collect(Collectors.toSet()), // get module children ids
                module.getQuestions().stream().map(Question::getId).collect(Collectors.toSet()), // get module question ids
                module.getDocs().stream().map(Doc::getId).collect(Collectors.toSet())
        );

        response.put("message", "Module found");
        response.put("module", moduleDTO);


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get modules with depth pageable
    @Override
    public Page<GetContentModuleDTO> getModulesWithDepth(
            Long subjectId, int depth,
            Pageable pageable) {

        // create a list to store current depth modules
        List<GetContentModuleDTO> modulesAtCurrentDepth = new ArrayList<>();

        // get root modules and add then to the list
        List<ContentModule> rootModules = contentModuleRepository.findBySubjectIdAndParentIsNull(subjectId);

        // recursive call to get modules with current depth
        for(ContentModule module : rootModules){
            filterByDepthRecursive(module, modulesAtCurrentDepth, depth, 0);
        }

        //pagination
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(), // check number of current page in the request
                Math.min(pageable.getPageSize(), 16), // limit max page elements to 16
                Sort.by(Sort.Direction.DESC, "id") // sort by id decrecent

        );

        // calculate start and end for pagination and create paged list
        int start = (int) safePageable.getOffset(); // check how many elements have to skip to get the current page (number of elements to skip)
        int end = Math.min(start + safePageable.getPageSize(), modulesAtCurrentDepth.size()); // check until which element to stop
        List<GetContentModuleDTO> paged = modulesAtCurrentDepth.subList(start, end); // create a list of element of the current page

        return new PageImpl<>(paged, safePageable, modulesAtCurrentDepth.size());

    }

    //collectModulesWiththeCurrentDepth auxiliar function
    private void filterByDepthRecursive(ContentModule module, List<GetContentModuleDTO> result, int targetDepth, int currentDepth){

        //check id the current depth is equal to the target depth
        if (currentDepth == targetDepth) {
            // add module to list with depth
            result.add(new GetContentModuleDTO(
                    module.getId(),
                    module.getName(),
                    module.getSubject().getId(),
                    module.getParent() != null ? module.getParent().getId() : null, // check if parent is null and return null if it is
                    module.getChildren().stream().map(ContentModule::getId).collect(Collectors.toSet()), // get module children ids
                    module.getQuestions().stream().map(Question::getId).collect(Collectors.toSet()), // get module question ids
                    module.getDocs().stream().map(Doc::getId).collect(Collectors.toSet())
            ));
            // stop recursion when reaching expected target depth
            return;

        }

        // recursive call if current depth is not equal to target depth repeat until target depth is reached
        for (ContentModule child : module.getChildren()){
            filterByDepthRecursive(child, result, targetDepth, currentDepth + 1);
        }
    }

    //Create module@Override
    public ResponseEntity<Map<String, Object>> createModule(Long subjectId, CreateContentModuleDTO dto){

        // response object
        Map<String, Object> response = new HashMap<>();

        Optional<Subject> subjectExist = subjectRepository.findById(subjectId);

        // check if does not exist, (CHANGE THIS LATER WHEN IMPLEMENTING GROUPS)
        if (subjectExist.isEmpty()) {
            response.put("message", "Subject not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Optional <ContentModule> contentModuleExit = contentModuleRepository.findBySubjectIdAndName(subjectId, dto.name());

        if (contentModuleExit.isPresent()) {
            response.put("message", "Module already exists, please change the module name and try again");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // create module object
        ContentModule newModule = new ContentModule();
        newModule.setName(dto.name());
        newModule.setSubject(subjectExist.get());


        // check if parent Module exist in sent DTO
        if(dto.parentId() != null){
            contentModuleRepository.findById(dto.parentId()).ifPresent(newModule::setParent);
        }

        //save new module in DB
        contentModuleRepository.save(newModule);
        response.put("message", "Module created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Override
    public ResponseEntity<Map<String, Object>> updateModule(Long moduleId, UpdateContentModuleDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteModule(Long moduleId) {
        return null;
    }
}

