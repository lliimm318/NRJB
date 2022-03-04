package com.nrjb.user.controller;

import com.nrjb.user.payload.request.JoinRequest;
import com.nrjb.user.payload.request.SignInRequest;
import com.nrjb.user.payload.response.TokenResponse;
import com.nrjb.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public void join(@RequestBody JoinRequest joinRequest) {
        authService.Join(joinRequest);
    }

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.SignIn(signInRequest);
    }

    @PutMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("refresh-token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }

}
