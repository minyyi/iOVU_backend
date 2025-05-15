package com.iovu.iovuback.controller;

import com.iovu.iovuback.domain.User;
import com.iovu.iovuback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "<a href=\"/oauth2/authorization/kakao\">Kakao</a>";
    }

    @GetMapping("/login/success")
    @ResponseBody
    public String success(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return "인증 정보가 없습니다.";
        }

        Map<String, Object> attributes = oauth2User.getAttributes();
        return "로그인 성공!<br><pre>" + attributes.toString() + "</pre>";
    }
}
