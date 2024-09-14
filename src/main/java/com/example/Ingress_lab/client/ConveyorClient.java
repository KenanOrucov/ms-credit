package com.example.Ingress_lab.client;

import com.example.Ingress_lab.model.client.ConveyorRequest;
import com.example.Ingress_lab.model.client.ConveyorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name= "conveyor", url = "${conveyor.client.url}")
public interface ConveyorClient {

    @PostMapping()
    List<ConveyorResponse> getConveyorOffer(@RequestBody ConveyorRequest conveyorRequest);

}
