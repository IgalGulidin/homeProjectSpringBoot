package com.poll_service.poll_service.client;

import com.poll_service.poll_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081/user")
public interface UserClient {

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void deleteUserAnswers(@PathVariable Long id);
}
