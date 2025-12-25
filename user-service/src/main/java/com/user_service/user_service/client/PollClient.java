package com.user_service.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "poll-service", url = "http://localhost:8082/poll")
public interface PollClient {

    @DeleteMapping("/answers/user/{userId}")
    void deleteAnswersByUser(@PathVariable Long userId);
}