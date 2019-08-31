package com.community.controller;

import com.community.dto.PageDto;
import com.community.dto.QuestionDto;
import com.community.mapper.QuestionMapper;
import com.community.model.Question;
import com.community.model.User;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    private PageDto pageDto;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("name", "我的帖子");
            model.addAttribute("path", "questions");
            pageDto = questionService.selectMyQuestion(page, size,user);
            model.addAttribute(pageDto);
            return "profile";
        }
        if ("messages".equals(action)) {
            model.addAttribute("name", "我的消息");
            model.addAttribute("path", "messages");
            return "profile";
        }
        return "redirect:/";
    }
}
