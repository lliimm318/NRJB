package com.nrjb.cat.payload.response;

import lombok.Builder;

@Builder
public class SearchResponse {

    private final String address;

    private final String x;

    private final String y;

}
