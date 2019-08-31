package com.community.service.impl;

import com.community.dto.PageDto;
import com.community.dto.QuestionDto;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.User;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageDto selectById(Integer page, Integer size) {
        Integer total = questionMapper.getTotal();
        total = total % size == 0 ? total / size : total / size + 1;
        if (total != 0 && page > total) {
            page = total;
        } else if (page < 1) {
            page = 1;
        }
        PageDto pageDto=new PageDto();
        List<Question> questionList = new ArrayList<>();
        questionList = questionMapper.selectAll((page - 1) * size, size);
        for (Question question : questionList) {
            if (question != null) {
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestion(question);
                questionDto.setUser(user);
                pageDto.getQuestionDtoList().add(questionDto);
            }
        }
        pageDto.setPages(page,total,size);
        return pageDto;
    }

    @Override
    public PageDto selectMyQuestion(Integer page, Integer size, User user) {
        PageDto pageDto=new PageDto();
        Integer total = questionMapper.getTotalById(user.getId());
        total = total % size == 0 ? total / size : total / size + 1;
        if (total != 0 && page > total) {
            page = total;
        } else if (page < 1) {
            page = 1;
        }
        pageDto.setPages(page, total, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        questionList = questionMapper.selectByCreateId((page - 1) * size, size,user.getId());
        for (Question question : questionList) {
            if (question != null) {
                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestion(question);
                questionDto.setUser(user);
                pageDto.getQuestionDtoList().add(questionDto);
            }
        }
        return pageDto;
    }

    @Override
    public QuestionDto selectById(int id) {
        questionMapper.updateView(id);
        Question question=questionMapper.selectById(id);
        User user=userMapper.findById(question.getCreator());
        QuestionDto questionDto=new QuestionDto();
        questionDto.setUser(user);
        questionDto.setQuestion(question);
        return questionDto;
    }

    @Override
    public List<Question> selectQuestionByTag(QuestionDto questionDto) {
        String tag=questionDto.getQuestion().getTag();
        tag=tag.replaceAll(",","|");
        List<Question> questionList=questionMapper.selectByTag(tag,questionDto.getQuestion().getId());
        return questionList;
    }

    @Override
    public void createOrUpdate(Integer id) {
    }
}
