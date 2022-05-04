package com.aj2814.homeAutomation.services.deviceControllers.common;


import com.aj2814.homeAutomation.services.deviceControllers.tapo.entity.requests.TapoResponse;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
Generic HTTP Client custom for Tapo Devices.
 */
@Component
public class TapoHttpClient {

    private final RestTemplate restTemplate;

    public TapoHttpClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * Generic post request for tapo devices - accepts JSONObject, but body is usually of type that inherits this
     * type. Attaches the created headers and posts to the specified URL
     * @param url - endpoint for post request
     * @param body - body of request
     * @param cookie - cookie if needed
     * @return response from device
     */
    public ResponseEntity<TapoResponse> tapoPost(String url, JSONObject body, String cookie) {
        HttpHeaders headers = createHeaders(cookie);
        HttpEntity<JSONObject> entity = new HttpEntity<>(body, headers);
        return this.restTemplate.postForEntity(url, entity, TapoResponse.class);
    }

    /**
     * Creates headers as application json type. If cookie is "null" then cookies are not set
     * @param cookie - created cookie that authenticates requests if required
     * @return headers
     */
    public HttpHeaders createHeaders(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!cookie.equals("null")) {
            headers.add("Cookie", cookie);
        }
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}