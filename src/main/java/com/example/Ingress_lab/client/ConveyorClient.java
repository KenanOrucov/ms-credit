package com.example.Ingress_lab.client;

import com.example.Ingress_lab.model.client.ConveyorRequestDto;
import com.example.Ingress_lab.model.client.ConveyorResponseDto;
import com.example.Ingress_lab.model.client.UpdateProductStatusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name= "conveyor", url = "${conveyor.client.url}")
public interface ConveyorClient {

    @PostMapping()
    List<ConveyorResponseDto> getConveyorOffer(@RequestBody ConveyorRequestDto conveyorRequestDto);

    @PutMapping
    void updateProductStatus(@RequestParam Long conveyorId, @RequestBody UpdateProductStatusDto updateProductStatusDto);

}
