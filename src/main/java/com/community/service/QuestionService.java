package com.community.service;

import com.community.dto.PageDto;
import com.community.dto.QuestionDto;
import com.community.model.Question;
import com.community.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public PageDto selectById(Integer page, Integer size);

    PageDto selectMyQuestion(Integer page, Integer size, User user);

    QuestionDto selectById(int id);

    List<Question> selectQuestionByTag(QuestionDto questionDto);

    void createOrUpdate(Integer id);
}

