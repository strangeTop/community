package com.community.service;

import com.community.dto.CommentCreateDto;
import com.community.dto.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void insert(CommentCreateDto commentCreateDto, Integer creator);

    List<CommentResponseDto> select(int id, int i);
}
