package com.community.controller;

import com.community.dto.CommentCreateDto;
import com.community.model.User;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@RequestBody CommentCreateDto commentCreateDto,
                          HttpServletRequest request){
        HashMap<Object,Object> hashMap=new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            hashMap.put("type","fail");
            hashMap.put("msg","请登录");
            return hashMap;
        }
        Integer creator=user.getId();
        commentService.insert(commentCreateDto,creator);
        hashMap.put("type","success");
        return hashMap;
    }
}
