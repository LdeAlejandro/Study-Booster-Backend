package com.alejandro.studybooster.module.service.Impl;

import com.alejandro.studybooster.module.controller.dto.Question.CreateQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.GetQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.Question.UpdateQuestionDTO;
import com.alejandro.studybooster.module.controller.dto.QuestionOption.GetQuestionOptionDTO;
import com.alejandro.studybooster.module.controller.dto.QuestionOption.UpdateQuestionOptionDTO;
import com.alejandro.studybooster.module.controller.dto.auxiliarsDTO.ContextEntitiesSubjectAndModuleDTO;
import com.alejandro.studybooster.module.controller.dto.auxiliarsDTO.ContextEntitiesSubjectModuleAndQuestionDTO;
import com.alejandro.studybooster.module.entity.ContentModule;
import com.alejandro.studybooster.module.entity.Question;
import com.alejandro.studybooster.module.entity.QuestionOption;
import com.alejandro.studybooster.module.repository.ContentModuleRepository;
import com.alejandro.studybooster.module.repository.QuestionRepository;
import com.alejandro.studybooster.module.repository.SubjectRepository;
import com.alejandro.studybooster.module.service.QuestionService;
import com.alejandro.studybooster.module.util.DataBaseValidator;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final DataBaseValidator validator;

    public QuestionServiceImpl(QuestionRepository questionRepository, ContentModuleRepository contentModuleRepository, SubjectRepository subjectRepository, DataBaseValidator validator) {
        this.questionRepository = questionRepository;
        this.validator = validator;
    }

    // create question
    public ResponseEntity<Map<String, Object>> createQuestion(Long subjectId, Long moduleId, CreateQuestionDTO dto) {

        // validate if subject and module exist (personalize auxiliar method and DTO)
        Optional <?> contextDataValidationResult = validator.validateIfSubjectAndModuleExist(subjectId, moduleId);
        // check if validation failed and return error response
        if (contextDataValidationResult.get() instanceof ResponseEntity<?> response) {
            return (ResponseEntity<Map<String, Object>>) response;
        }
        //after validation get and store data reference
        ContextEntitiesSubjectAndModuleDTO validatedResult = (ContextEntitiesSubjectAndModuleDTO) contextDataValidationResult.get();

        // get module reference
        ContentModule module = validatedResult.module();

        // create response object
        Map<String, Object> response = new HashMap<>();


        // create question
        Question question = new Question();
        question.setQuestion(dto.question());
        question.setAnswerExplanation(dto.answerExplanation());
        question.setModules(Set.of(module)); // set the module for the question

        // add and asociate question to current module
        module.getQuestions().add(question);
        // create and asociate options
        List<QuestionOption> options = dto.options().stream().map(optionDTO -> {
            QuestionOption option = new QuestionOption();
            option.setOption(optionDTO.option());
            option.setCorrectOption(optionDTO.correct());
            option.setQuestion(question); // set the question for the option
            return option;
        }).toList();

        //save question
        question.setOptions(options); // Set list of options for the question
        questionRepository.save(question);

        //return response
        response.put("message", "Question created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // get all questions in current module
    @Override
    public Page<GetQuestionDTO> getAllModuleQuestionsById(Long subjectId, Long moduleId, Pageable pageable) {

        // validate if subject and module exist (personalize auxiliar method and DTO)
        Optional <?> contextDataValidationResult = validator.validateIfSubjectAndModuleExist(subjectId, moduleId);
        // check if validation failed and return error response
        if (contextDataValidationResult.get() instanceof ResponseEntity<?> response) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject or module not found");
        }
        //after validation get and store data reference
        ContextEntitiesSubjectAndModuleDTO validatedResult = (ContextEntitiesSubjectAndModuleDTO) contextDataValidationResult.get();

        // get module reference
        ContentModule module = validatedResult.module();

        //pagination
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(), // check number of page in the request
                Math.min(pageable.getPageSize(), 60), // limit to 60
                Sort.by(Sort.Direction.DESC, "id") // sort by id crecent

        );

        // get all questions in current module
        Page <Question> questions = questionRepository.findAllByModules_Id(module.getId(), safePageable);

        //shuffle questions order
        List<Question> questionList = new ArrayList<>(questions.getContent());
        Collections.shuffle(questionList);

        List<GetQuestionDTO> dtoList = questionList.stream().map(question -> {

            //shuffle options
            List<QuestionOption> shuffledOptions = new ArrayList<>(question.getOptions());
            Collections.shuffle(shuffledOptions);

            return new GetQuestionDTO(
                    question.getId(),
                    question.getQuestion(),
                    question.getAnswerExplanation(),
                    shuffledOptions.stream()
                            .map(option -> new GetQuestionOptionDTO(
                                    option.getId(),
                                    option.getOption(),
                                    option.isCorrectOption()))
                            .collect(Collectors.toList())
            );
        }).collect(Collectors.toList());

        return new PageImpl<>(dtoList, safePageable, questions.getTotalElements());

    }

    // get any question by id
    @Override
    public ResponseEntity<Map<String, Object>> getQuestionById(Long questionId) {
        // check if question exist
        Optional<Question> targetQuestion = questionRepository.findById(questionId);

        Map<String, Object> response = new HashMap<>();

        // if empty return an error
        if(targetQuestion.isEmpty()) {

            response.put("message", "Question not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Question question = targetQuestion.get();

        // shuffle options
        List<QuestionOption> shuffledOptions = new ArrayList<>(question.getOptions());
        Collections.shuffle(shuffledOptions);

        GetQuestionDTO questionDTO = new GetQuestionDTO(
        question.getId(),
                question.getQuestion(),
                question.getAnswerExplanation(),
                shuffledOptions.stream()
                        .map (
                                option -> new GetQuestionOptionDTO(
                                        option.getId(),
                                        option.getOption(),
                                        option.isCorrectOption()
                                )
                        ).toList()
        );

        response.put("message", "Question found");
        response.put("module", questionDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get question by id in current module
    @Override
    public ResponseEntity<Map<String, Object>> getModuleQuestionById(Long subjectId, Long moduleId, Long questionId) {

        // validate if subject and module exist (personalize auxiliar method and DTO)
        Optional <?> contextDataValidationResult = validator.validateIfSubjectModuleAndQuestionExist(subjectId, moduleId, questionId);
        // check if validation failed and return error response
        if (contextDataValidationResult.get() instanceof ResponseEntity<?> response) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject or module not found");
        }
        //after validation get and store data reference
        ContextEntitiesSubjectModuleAndQuestionDTO validatedResult = (ContextEntitiesSubjectModuleAndQuestionDTO) contextDataValidationResult.get();

        // get module reference
        Question question = validatedResult.question();

        Map<String, Object> response = new HashMap<>();

        //shuffle options

        List<QuestionOption> shuffledOptions = new ArrayList<>(question.getOptions());
        Collections.shuffle(shuffledOptions);

        GetQuestionDTO questionDTO = new GetQuestionDTO(
                question.getId(),
                question.getQuestion(),
                question.getAnswerExplanation(),

                shuffledOptions.stream()
                        .map (option -> new GetQuestionOptionDTO(
                                    option.getId(),
                                    option.getOption(),
                                    option.isCorrectOption()
                                )
                        ).toList()
        );

        response.put("message", "Module found");
        response.put("module", questionDTO);


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // update question by id
    @Override
    public ResponseEntity<Map<String, Object>> updateQuestion(Long questionId, UpdateQuestionDTO updateQuestionDTO) {
        Optional<Question> targetQuestion = questionRepository.findById(questionId);
        Map<String, Object> response = new HashMap<>();

        //check if question exist
        if (targetQuestion.isEmpty()) {
            response.put("message", "Question not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        //update question
        Question updatedQuestion = targetQuestion.get();
        updatedQuestion.setQuestion(updateQuestionDTO.question());
        updatedQuestion.setAnswerExplanation(updateQuestionDTO.answerExplanation());

        // Clear current options before updating so it doesnt loose the reference (orphanRemoval = true)
        updatedQuestion.getOptions().clear();

        // update options
        updateQuestionDTO.options().forEach(dto -> {
            QuestionOption option = new QuestionOption();
            option.setOption(dto.option());
            option.setCorrectOption(dto.correct());
            option.setQuestion(updatedQuestion); // set the question for the option to maintain the reference

            updatedQuestion.getOptions().add(option); // add (update) new options
        });

        // update DTO for returning message
        UpdateQuestionDTO questionDTO = new UpdateQuestionDTO(
                updatedQuestion.getQuestion(),
                updatedQuestion.getAnswerExplanation(),
                updatedQuestion.getOptions().stream()
                        .map(option -> new UpdateQuestionOptionDTO(
                                option.getOption(),
                                option.isCorrectOption()
                        ))
                        .toList()
        );


        questionRepository.save(updatedQuestion);

        response.put("message", "Question Updated Successfully");
        response.put("module", questionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete question by id
    @Override
    public ResponseEntity<Map<String, Object>> deleteQuestion(Long questionId) {

        Optional<Question> targetQuestion = questionRepository.findById(questionId);
        Map<String, Object> response = new HashMap<>();

        //check if question exist
        if (targetQuestion.isEmpty()) {
            response.put("message", "Question not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        questionRepository.delete(targetQuestion.get());

        response.put("message", "Question deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
