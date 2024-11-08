package kr.hs.sdh.workbook1.controller;

import kr.hs.sdh.workbook1.entity.Login;
import kr.hs.sdh.workbook1.entity.Sign;
import kr.hs.sdh.workbook1.service.LoginService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.beans.ConstructorProperties;

public class LoginRestContoller {

    @RestController
    public class LoginRestController {

        private final LoginService loginService;

        public LoginRestController(LoginService loginService) {
            this.loginService = loginService;
        }

        // User 도는 Account
        @PostMapping("/sing-in")
        private Login signIn(@RequestBody Sign login) {
            return this.loginService.getMember(login.getUserId());
        }

        @DeleteMapping("/delete-user")
        private String delete(@RequestParam final String  id) {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.exchange("http://10.56.148.148:8081/delete-user?userId=" + id, HttpMethod.DELETE, null, String.class).getBody();

            this.loginService.removeHamburger(id);
            return id + "계정이 삭제 되었습니다.";
        }

    }
}
