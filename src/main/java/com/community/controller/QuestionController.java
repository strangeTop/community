package com.community.controller;

import com.community.dto.CommentResponseDto;
import com.community.dto.QuestionDto;
import com.community.model.Question;
import com.community.service.CommentService;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") int id,
                           Model model){
        QuestionDto questionDto= questionService.selectById(id);
        List<CommentResponseDto> commentResponseDtoListQ= commentService.select(id,0);
        List<CommentResponseDto> commentResponseDtoListC= commentService.select(id,1);
        List<Question> questionList=questionService.selectQuestionByTag(questionDto);
        model.addAttribute(questionList);
        model.addAttribute(questionDto);
        model.addAttribute("commentResponseDtoListQ",commentResponseDtoListQ);
        model.addAttribute("commentResponseDtoListC",commentResponseDtoListC);
        return "question";
    }
}
