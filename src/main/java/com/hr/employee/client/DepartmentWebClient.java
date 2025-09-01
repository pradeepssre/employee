package com.hr.employee.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DepartmentWebClient {

    private final WebClient webClient;

     public Department getDepartmentByName(String name) {
        return webClient.get()
                .uri("/api/departments/name/{name}", name)
                .retrieve()
                .bodyToMono(Department.class)
                .block();// blocks to get the result synchronously
    }
}
