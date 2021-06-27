package com.vyira.quizserver.services;

import com.vyira.quizserver.models.Question;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {
    MongoOperations mongoOperations;

    public Question getQuestion(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        List<Question> questionList = mongoOperations.find(query, Question.class);
        return questionList.get(0);
    }

    public ResponseEntity<String> saveQuestion(Question question) {
        try {
            Question savedQuestion = mongoOperations.save(question);
            if (ObjectUtils.isEmpty(savedQuestion.getId())) {
                return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
