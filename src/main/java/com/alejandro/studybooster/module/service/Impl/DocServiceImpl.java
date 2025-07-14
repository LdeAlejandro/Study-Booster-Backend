package com.alejandro.studybooster.module.service.Impl;

import com.alejandro.studybooster.module.controller.dto.Doc.CreateDocDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.GetDocDTO;
import com.alejandro.studybooster.module.controller.dto.Doc.UpdateDocDTO;
import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Doc;

import com.alejandro.studybooster.module.repository.ContentModuleRepository;
import com.alejandro.studybooster.module.repository.DocRepository;
import com.alejandro.studybooster.module.service.DocService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DocServiceImpl implements DocService {

    DocRepository docRepository;
    ContentModuleRepository contentModuleRepository;

    public DocServiceImpl(DocRepository docRepository, ContentModuleRepository contentModuleRepository) {
        this.docRepository = docRepository;
        this.contentModuleRepository = contentModuleRepository;
    }

    //create doc
    @Override
    public ResponseEntity<Map<String, Object>> createDoc(Long moduleId, CreateDocDTO createDocDTO) {
        Map<String, Object> response = new HashMap<>();

        Optional<ContentModule> moduleOpt = contentModuleRepository.findById(moduleId);
        if (moduleOpt.isEmpty()) {
            response.put("message", "Module not found for the given subject");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ContentModule module = moduleOpt.get();

        Doc doc = new Doc();
        doc.setTitle(createDocDTO.title());
        doc.setContent(createDocDTO.content());
        doc.getModules().add(module); // relação ManyToMany
        module.getDocs().add(doc); // relacion ManyToMany add doc to module

        docRepository.save(doc);

        response.put("message", "Doc created successfully");
        response.put("docId", doc.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get doc by id
    @Override
    public ResponseEntity<Map<String, Object>> getDocById(Long docId) {
        Optional<Doc> targetDoc = docRepository.findById(docId);
        Map<String, Object> response = new HashMap<>();

        if (targetDoc.isEmpty()) {
            response.put("message", "Doc not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // store targe doc data in new object
        Doc doc = targetDoc.get();
        // unlink doc from the module so it can be deleted
        for (ContentModule module : doc.getModules()) {
            module.getDocs().remove(doc); //remove doc link from module
        }
        GetDocDTO dto = new GetDocDTO(doc.getId(), doc.getTitle(), doc.getContent());

        response.put("message", "Doc found");
        response.put("doc", dto);
        return ResponseEntity.ok(response);
    }

    // update doc
    @Override
    public ResponseEntity<Map<String, Object>> updateDoc(Long docId, UpdateDocDTO updateDocDTO) {
        Optional<Doc> targetDoc = docRepository.findById(docId);
        Map<String, Object> response = new HashMap<>();

        if (targetDoc.isEmpty()) {
            response.put("message", "Doc not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Doc doc = targetDoc.get();
        doc.setTitle(updateDocDTO.title());
        doc.setContent(updateDocDTO.content());

        docRepository.save(doc);

        response.put("message", "Doc updated successfully");
        response.put("doc", new GetDocDTO(doc.getId(), doc.getTitle(), doc.getContent()));
        return ResponseEntity.ok(response);
    }

    // delete doc
    @Override
    public ResponseEntity<Map<String, Object>> deleteDoc(Long docId) {
        Optional<Doc> targetDoc = docRepository.findById(docId);
        Map<String, Object> response = new HashMap<>();

        if (targetDoc.isEmpty()) {
            response.put("message", "Doc not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        docRepository.delete(targetDoc.get());
        response.put("message", "Doc deleted successfully");
        return ResponseEntity.ok(response);
    }
}
