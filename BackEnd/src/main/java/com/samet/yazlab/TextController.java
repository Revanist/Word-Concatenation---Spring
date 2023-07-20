package com.samet.yazlab;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/text")
@CrossOrigin(origins = "*")
public class TextController {
    @Autowired
    private TextService textService;

    @Autowired
    private TextRepository textRepository;

    long processTime;

    @GetMapping
    public ResponseEntity<Text> getText() {
        return new ResponseEntity<Text>(textService.getLastText(), HttpStatus.OK);
    }


    @PostMapping()
    public String addText(@RequestBody Text text){
        long start1 = System.nanoTime();
        TextAlgorithm textAlgorithm = new TextAlgorithm(text);
        textAlgorithm.extractSentences();
        String result = textAlgorithm.finalControl();
        long end1 = System.nanoTime();
        processTime = end1 - start1;
        text.setResult(result);
        text.setProcessTime(processTime);
        textRepository.save(text);
        return result;
    }


}
