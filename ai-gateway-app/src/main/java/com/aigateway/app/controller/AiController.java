package com.aigateway.app.controller;

import com.aigateway.lib.api.AiGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiGateway gateway;

    public AiController(AiGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(
            @RequestParam String prompt,
            @RequestParam(required = false) String model) {

        return gateway.chat(prompt, model);
    }
}