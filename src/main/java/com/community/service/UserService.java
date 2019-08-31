package com.community.service;

import com.community.dto.QuestionDto;
import com.community.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface UserService {
    void addOrUpdate(User user);

}
