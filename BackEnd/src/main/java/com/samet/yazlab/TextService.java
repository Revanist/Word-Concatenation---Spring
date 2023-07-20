package com.samet.yazlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@ComponentScan("com.samet.yazlab")
public class TextService {
    @Autowired
    private TextRepository textRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Text> findAllText() {
        return textRepository.findAll();
    }

    public Text getLastText() {
        Criteria criteria = new Criteria();
        Sort sort = Sort.by(Sort.Direction.DESC, "_id");
        Query query = new Query(criteria).with(sort).limit(1);
        List<Text> texts = mongoTemplate.find(query, Text.class);
        return texts.isEmpty() ? null : texts.get(texts.size() - 1);

    }



}
