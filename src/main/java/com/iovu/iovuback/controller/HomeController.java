package com.iovu.iovuback.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.IIOException;
import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // → /WEB-INF/views/home.jsp로 forward
    }

    @GetMapping("/logoutBridge")
    public String logoutBridge() {
        return "naverlogout"; // → /WEB-INF/views/naverlogout.jsp 로 forward
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutsuccess"; // /WEB-INF/views/logoutsuccess.jsp
    }


}
