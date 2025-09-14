package com.example.demo.service;

import com.example.demo.mapper.ClickLogMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class ClickService {
    private final ClickLogMapper mapper;

    public ClickService(ClickLogMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional
    public void click(String label) {
        mapper.insert(label);
    }

    @Async("clickExecutor")
    @Transactional
    public CompletableFuture<Void> clickAsync(String label) {
        mapper.insert(label);
        return CompletableFuture.completedFuture(null);
    }
}
