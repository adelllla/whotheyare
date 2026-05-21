package com.aipersonas.whotheyare.controller;



import com.aipersonas.whotheyare.dto.ChatRequest;
import com.aipersonas.whotheyare.dto.PersonaRequest;
import com.aipersonas.whotheyare.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PersonaController {

    private final GroqService groqService;

    @PostMapping("/generate-persona")
    public String generatePersona(@RequestBody PersonaRequest req) throws Exception {

        String prompt =
                "You are a persona generator.\n" +
                        "Return ONLY valid JSON:\n" +
                        "{ name, age, job, goals, frustrations, budget, buyingBehavior }\n\n" +
                        "Business: " + req.business + "\n" +
                        "Audience: " + req.audience;
        return groqService.callModel(prompt);
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody ChatRequest req) throws Exception {

        String prompt =
                "You are a real human customer persona.\n" +
                        "Answer in 2-4 sentences.\n\n" +
                        "PERSONA:\n" + req.persona.toString() + "\n\n" +
                        "QUESTION:\n" + req.message;

        String response = groqService.callModel(prompt);

        return Map.of("response", response);
    }

}
