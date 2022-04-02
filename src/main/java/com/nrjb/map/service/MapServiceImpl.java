package com.nrjb.map.service;

import com.nrjb.cat.payload.response.SearchResponse;
import com.nrjb.cat.payload.response.ShelterResponse;
import com.nrjb.map.VisionApi;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    @Value("${kakao.rest.api.key}")
    private String authorizationKey;

    private VisionApi connection = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/local/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(VisionApi.class);

    @Override
    public List<SearchResponse> search(String x, String y) {

        List<SearchResponse> resultResponses = new ArrayList<>();

        return null;
    }

    @SneakyThrows
    private Response<ShelterResponse> sendMapResponse(String x, String y) {
        return connection
                .visionApi(authorizationKey, x, y, 2000)
                .execute();

    }

}
