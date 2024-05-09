package com.ttknpdev.myportfolio.service;

import com.ttknpdev.myportfolio.repository.LineNotifyRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class LineNotifyService implements LineNotifyRepo {
    @Value(value = "${line.notify.url}")
    private String url;
    @Value(value = "${line.notify.token}")
    private String token;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    // No HttpMessageConverter for java.util.LinkedHashMap,Map,... and content type "application/x-www-form-urlencoded"
    private HttpEntity<MultiValueMap<String, Object>> request;
    public LineNotifyService() {
        restTemplate = new RestTemplate();
    }
    @Override
    public LinkedHashMap<String, Object> sendLineNotifyMessage() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "some people watch your portfolio");
        map.put("stickerPackageId", 11538);
        map.put("stickerId", 51626514);
        // return callLineNotifyByMultiValueMap(map);
        return callLineNotifyByMultiValueMap(map);
    }

    private LinkedHashMap<String, Object> callLineNotifyByMultiValueMap(HashMap<String, Object> map) throws Exception {
        headers = new HttpHeaders(); // **** have to create new object when use method Always

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for application/ x-www-form-urlencoded. Or You can set like

        headers.add("Authorization", "Bearer " + token);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>(); // need to use MultiValueMap ,LinkedMultiValueMap because can not use Map,HasMap
        multiValueMap.add("message", map.get("message"));
        multiValueMap.add("stickerPackageId", map.get("stickerPackageId"));
        multiValueMap.add("stickerId", map.get("stickerId"));
        request = new HttpEntity<>(multiValueMap, headers);

        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(url, HttpMethod.POST, request, LinkedHashMap.class);

        return response.getBody();
    }

}
