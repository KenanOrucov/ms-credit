package com.example.Ingress_lab.model.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString(exclude = "products")
public class ConveyorResponse {
    private Long conveyorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkDate;
    private List<ProductResponse> products;
}
