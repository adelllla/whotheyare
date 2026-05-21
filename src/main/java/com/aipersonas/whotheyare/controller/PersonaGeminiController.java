package com.aipersonas.whotheyare.controller;

import com.aipersonas.whotheyare.dto.ChatRequest;
import com.aipersonas.whotheyare.dto.PersonaRequest;
import com.aipersonas.whotheyare.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PersonaGeminiController {

    private final GeminiService geminiService;

    @PostMapping("/generate-personas")
    public String generatePersona(@RequestBody PersonaRequest req) throws Exception {

        String prompt =
                "Create a realistic customer persona:\n" +
                        "Business: " + req.business + "\n" +
                        "Audience: " + req.audience + "\n" +
                        "Return: name, age, goals, frustrations, budget, behavior.";

        return geminiService.callGemini(prompt);
    }

    @PostMapping("/chats")
    public String chat(@RequestBody ChatRequest req) throws Exception {

        String prompt =
                "You are this persona:\n" +
                        req.persona + "\n\n" +
                        "User: " + req.message + "\n" +
                        "Reply as the persona realistically.";

        return geminiService.callGemini(prompt);
    }
}