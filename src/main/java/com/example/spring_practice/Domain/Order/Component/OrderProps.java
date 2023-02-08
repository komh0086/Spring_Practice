package com.example.spring_practice.Domain.Order.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taco.orders")//이 어노테이션을 통해 application.yml 파일을 통해 구성속정과 접두어를 지정할수 있다.
@Data
public class OrderProps {
    @Min(value = 5, message = "must be between 5 and 25")
    @Max(value = 25, message = "must be between 5 and 25")
    private int pageSize = 20;
}
