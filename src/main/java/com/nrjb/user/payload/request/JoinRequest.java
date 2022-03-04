package com.nrjb.user.payload.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JoinRequest {

    @NotNull
    private final String name;

    @NotNull
    private final String id;

    @NotNull
    private final String password;

}
