<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>네이버 로그아웃 안내</title>
</head>
<body>
    <script>
        function logoutAll() {
            // 네이버 로그아웃 창 새로 열기
            window.open('https://nid.naver.com/nidlogin.logout', '_blank');
            // 현재 창은 홈으로 이동
            window.location.href = "/logoutSuccess";
        }
    </script>
    <button onclick="logoutAll()">✔ 네이버 완전 로그아웃</button>
</body>
</html>