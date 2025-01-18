package com.example.UnitTest.controller;

import com.example.UnitTest.entity.QueryHistory;
import com.example.UnitTest.service.QueryHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unitTest")
public class QueryHistoryController {
    private final QueryHistoryService service;
    public QueryHistoryController(QueryHistoryService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> generateResponse(@RequestParam("input") String input) {
        try{
            Map<String, Object> response = new HashMap<>();

            response.put("generated output", service.generateResponse(input));
            return ResponseEntity.status(200).body(response);
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public List<QueryHistory> getAllQueries(@RequestParam("email") String email) {
        return service.getAllQueriesByEmail(email);
    }
    @GetMapping("/history/id")
    public QueryHistory getQueryById(@RequestParam("id") Long id) {
        return service.getQueryById(id);
    }

}
