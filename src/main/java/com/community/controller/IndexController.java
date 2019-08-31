package com.community.controller;

import com.community.dto.PageDto;
import com.community.mapper.UserMapper;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//主页
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PageDto pageDto;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        //获取登录用户信息
        //获得分页数据
        pageDto = questionService.selectById(page, size);
        model.addAttribute(pageDto);
        return "index";

    }
}
