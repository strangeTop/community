package com.community.controller;

import com.community.dto.TagDto;
import com.community.service.QuestionService;
import com.community.tag.Tag;
import com.community.mapper.QuestionMapper;
import com.community.model.Question;
import com.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish")
    public String getPublish(Model model) {
        Tag tags=new Tag();
        List<TagDto> tagDtoList=tags.getList();
        model.addAttribute(tagDtoList);
        return "publish";
    }

    @PostMapping("/publish")
    public String postPublish(Integer id,String title, String description, String tag, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("id",id);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if ("".equals(title)) {
            return "publish";
        }
        if ("".equals(description)) {
            return "publish";
        }
        if ("".equals(tag)) {
            return "publish";
        }
        if(id==null||questionMapper.selectById(id)==null){
            Question question =new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            return "redirect:/";
        }else {
            questionMapper.updateQuestion(id,title,description,tag);
            return "redirect:question/"+id;
        }
    }

    @GetMapping("/publish/{id}")
    public String editPublish(@PathVariable(name = "id")Integer id,
                              HttpServletRequest request,
                              Model model){
        //判断user
        //查问题
        //显示
        User user=(User) request.getSession().getAttribute("user");
        Question question=questionMapper.selectById(id);
        if(question==null){
            return "redirect:/profile/questions";
        }
        if(user==null||user.getId()!=question.getCreator()){
            return "redirect:/profile/questions";
        }
        model.addAttribute("id",question.getId());
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        return "publish";
    }
}
