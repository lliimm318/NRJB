package com.nrjb.cat.payload.request;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class registCatRequest {

    @NotNull
    private String name;



}
