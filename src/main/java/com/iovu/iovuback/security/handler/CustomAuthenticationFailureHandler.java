package com.iovu.iovuback.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 로그인 실패 처리 (로그 기록 등)
        System.out.println("소셜 로그인 실패: " + exception.getMessage());

        // 실패 이유를 파라미터로 전달
        String errorMessage = exception.getMessage();
        response.sendRedirect("/loginPage?error=true&message=" +
                URLEncoder.encode(errorMessage, StandardCharsets.UTF_8));
    }
}