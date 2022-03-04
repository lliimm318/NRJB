package com.nrjb.user.service;

import com.nrjb.user.payload.request.JoinRequest;
import com.nrjb.user.payload.request.SignInRequest;
import com.nrjb.user.payload.response.TokenResponse;

public interface AuthService {

    void Join(JoinRequest joinRequest);

    TokenResponse SignIn(SignInRequest signInRequest);

    TokenResponse tokenRefresh(String token);

}
