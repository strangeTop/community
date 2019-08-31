package com.community.service.impl;

import com.community.dto.CommentCreateDto;
import com.community.dto.CommentResponseDto;
import com.community.mapper.CommentMapper;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Comment;
import com.community.model.Question;
import com.community.model.User;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(CommentCreateDto commentCreateDto, Integer creator) {
        Comment comment =new Comment();
        comment.setType(commentCreateDto.getType());
        comment.setCreator(creator);
        comment.setCommentTo(commentCreateDto.getCommentTo());
        comment.setCommentText(commentCreateDto.getCommentText());
        comment.setGmtCreate(System.currentTimeMillis());
        commentMapper.insert(comment);
        if(comment.getType()==0){
            questionMapper.updateComment(commentCreateDto.getCommentTo());
        }
        if(comment.getType()==1){
            commentMapper.updateComment(commentCreateDto.getCommentTo());
        }
    }

    @Override
    public List<CommentResponseDto> select(int id, int i) {
        //根据id获取问题
        //根据id获取相应id的评论集合
        //根据评论人id获得user
        List<CommentResponseDto> commentResponseDtoList=new ArrayList<>();
        Question question=questionMapper.selectById(id);
        List<Comment> commentList=commentMapper.selectByCommentToQ(id);
        for(Comment comment:commentList){
            if(i==0){
                User user=userMapper.findById(comment.getCreator());
                CommentResponseDto commentResponseDto=new CommentResponseDto();
                commentResponseDto.setUser(user);
                commentResponseDto.setComment(comment);
                commentResponseDtoList.add(commentResponseDto);
            }
            if(i==1){
                List<Comment> commentListC=commentMapper.selectByCommentToC(comment.getId());
                for(Comment commentC:commentListC){
                    User user=userMapper.findById(commentC.getCreator());
                    CommentResponseDto commentResponseDto=new CommentResponseDto();
                    commentResponseDto.setUser(user);
                    commentResponseDto.setComment(commentC);
                    commentResponseDtoList.add(commentResponseDto);
                }
            }
        }
        return commentResponseDtoList;
    }
}
