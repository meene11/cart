package zerobase.cart.controller;

import zerobase.cart.dto.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.cart.service.UserService;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입등록
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) throws Exception {

        // 회원가입 api
        var result = this.userService.register(request);
        return ResponseEntity.ok(result);
    }

    // 로그인 api
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
        log.info("user login -> " + request.getUserId());

        var userId = this.userService.authenticate(request);


        return ResponseEntity.ok(userId);
    }
}
