package com.vyira.quizserver.ws;

import com.vyira.quizserver.models.Question;
import com.vyira.quizserver.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    QuizService service;

    @RequestMapping(path = "/quiz/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getQuestion(@PathVariable(name = "id") String id) {
        Question question = service.getQuestion(id);
        if (ObjectUtils.isEmpty(question)) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(question, HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addQuestion(@RequestBody Question question) {
        service.saveQuestion(question);
        return new ResponseEntity<>("SAVED", HttpStatus.CREATED);
    }
}
