package com.nrjb.map.service;

import com.nrjb.cat.payload.response.SearchResponse;

import java.util.List;

public interface MapService {
    List<SearchResponse> search(String x, String y);
}
