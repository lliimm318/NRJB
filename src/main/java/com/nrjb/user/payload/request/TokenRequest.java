package com.nrjb.user.payload.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenRequest {

    private final String refreshToken;

}
