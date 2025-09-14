package com.example.demo.web;

import com.example.demo.service.ClickService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Controller
public class ClickController {

    private final ClickService service;

    public ClickController(ClickService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
        model.addAttribute("logs", service.list());
        return "logs";
    }

    // 기존 동기 처리
    @PostMapping("/api/click")
    @ResponseBody
    public String click(@RequestParam String label) {
        service.click(label);
        return "OK";
    }

    // 비동기 처리: 즉시 응답, 백그라운드에서 insert
    @PostMapping("/api/click-async")
    @ResponseBody
    public String clickAsync(@RequestParam String label) {
        service.clickAsync(label);
        return "QUEUED";
    }
}
