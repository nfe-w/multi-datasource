package com.nfe.multidatasource.controller;

import com.nfe.multidatasource.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author nfe-w
 * @date 2021-12-18 13:51
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * execute
     */
    @GetMapping("/execute")
    public void execute() {
        testService.execute();
    }
}
