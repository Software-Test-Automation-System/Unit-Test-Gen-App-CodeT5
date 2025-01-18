package com.example.UnitTest.service;

import com.example.UnitTest.dao.QueryHistoryRepository;
import com.example.UnitTest.entity.QueryHistory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueryHistoryService {

    @Autowired
    private WebClient webClient;

    @Value("${generate.url}")
    String generateUrl;

    @Autowired
    private final QueryHistoryRepository queryRepository;

    public QueryHistoryService(QueryHistoryRepository repository) {
        this.queryRepository = repository;
    }

    public String generateResponse(String input) {
        String output = "";
        ObjectMapper objectMapper = new ObjectMapper();// Replace with actual API endpoint
        try {
            String res = webClient.post()
                    .uri(generateUrl)
                    .header("Content-Type", "application/json")
                    .bodyValue("{\"input_text\": \"generate unit test: " + input + "\"}")
                    .exchangeToMono(response -> {
                        if (response.statusCode().is2xxSuccessful()) {
                            return response.bodyToMono(String.class);
                        } else if (response.statusCode().is4xxClientError()) {
                            return Mono.just("{\"error\": \"Client error: " + response.statusCode() + "\"}");
                        } else if (response.statusCode().is5xxServerError()) {
                            return Mono.just("{\"error\": \"Server error: " + response.statusCode() + "\"}");
                        } else {
                            return Mono.just("{\"error\": \"Unexpected status: " + response.statusCode() + "\"}");
                        }
                    })
                    .block();
            JsonNode rootNode = objectMapper.readTree(res);
            output = rootNode.get("generated_output").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryHistory history = new QueryHistory();
        history.setInput(input);
//        history.setEmail(JwtRequestFilter.getCurrentEmail());
        history.setDateTime(LocalDateTime.now());
//        queryRepository.save(history);
        return output;
    }

    public List<QueryHistory> getAllQueriesByEmail(String email) {
        return queryRepository.findAllByEmail(email);
    }
    public QueryHistory getQueryById(Long id){
        return queryRepository.findById(id).orElseThrow(()-> new RuntimeException("query not found"));
    }
}
