package com.bus.controller;

import com.bus.dao.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 40651
 */
@Controller
@RequestMapping("/testGit")
@Slf4j
public class TestController {
    @Autowired
    TestMapper testMapper;
    @RequestMapping("/test")
    public String queryGitTest(HttpServletRequest request){
        log.info(request.getRequestURI());
        return testMapper.queryTestDemo().toString();
    }
}
