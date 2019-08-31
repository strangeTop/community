package com.community.controller;

import com.community.dto.AccessTokenDTO;
import com.community.dto.GithubUser;
import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.provider.GithubProvider;
import com.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
//GitHub登录
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Value("${github.Client_id}")
    String Client_id;

    @Value("${github.Redirect_uri}")
    String Redirect_uri;

    @Value("${github.Client_secret}")
    String Client_secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(Client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功,写入数据库
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            userService.addOrUpdate(user);
            //request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}
