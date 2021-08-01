package com.xylope.springsecurity.controller;

import com.xylope.springsecurity.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/permit-all")
    public Object getTest() {
        return testService.getTest();
    }

    @GetMapping("/auth")
    public Object getTest2() {
        return testService.getTest2();
    }

}