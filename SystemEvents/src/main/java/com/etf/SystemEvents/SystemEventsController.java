package com.etf.SystemEvents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.etf.proto.EventRequest;
// import com.etf.proto.EventResponse;

// import io.grpc.stub.StreamObserver;
// import net.devh.boot.grpc.server.service.GrpcService;

//import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "events")
public class SystemEventsController {

    @Autowired
    SystemEventsService systemEventsService;

    @GetMapping
    public ResponseEntity<List<SystemEvents>> getAllEvents()
    {
        return systemEventsService.getAllEvents();
    }
   
}
