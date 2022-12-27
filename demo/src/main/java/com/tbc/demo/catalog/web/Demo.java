package com.tbc.demo.catalog.web;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.tbc.demo.catalog.asynchronization.model.User;
import com.tbc.demo.catalog.web.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/8/23 13:46
 */
@Slf4j
@RequestMapping("demo2")
@Controller
public class Demo {

    @Value("${spring.application.name}")
    private String test;

    @Autowired
    private DemoService demoService;


    @RequestMapping("demo2")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        demoService.save("INSERT INTO `test`.`tab_tree_test`(`x`, `y`, `z`, `name`) VALUES (1, 1, 1, '1')");
        demoService.update("INSERT INTO `test`.`tab_tree_test`(`x`, `y`, `z`, `name`) VALUES (2, 2, 2, '2')");
        return "123";
    }

    @RequestMapping("demo")
    @ResponseBody
    public String test1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return test;
    }

}