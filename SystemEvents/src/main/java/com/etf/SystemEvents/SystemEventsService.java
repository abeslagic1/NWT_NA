package com.etf.SystemEvents;

import java.util.List;

import com.etf.proto.SystemEventsServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etf.proto.SystemEventRequest;
import com.etf.proto.SystemEventResponse;
import com.etf.proto.SystemEventsServiceGrpc.SystemEventsServiceImplBase;

import io.grpc.stub.StreamObserver;

@Service
public class SystemEventsService extends SystemEventsServiceGrpc.SystemEventsServiceImplBase {

    @Autowired
    private final SystemEventsRepository systemEventsRepository;

    @Autowired
    public SystemEventsService(ApplicationContext applicationContext) {
        this.systemEventsRepository = applicationContext.getBean(SystemEventsRepository.class);
    }

    @Override
    public void systemEventLog(SystemEventRequest request, StreamObserver<SystemEventResponse> responseObserver) {
        SystemEvents systemEvents = new SystemEvents(
            request.getTimestamp(),
            request.getMicroserviceName(),
            request.getUser(),
            request.getActionType(),
            request.getResourceName(),
            request.getResponseType()
        );

        systemEventsRepository.save(systemEvents);

        System.out.println("\nEvent details: ");
        System.out.println("ID: " + systemEvents.getId());
        System.out.println("Timestamp: " + systemEvents.getTimeStamp());
        System.out.println("Microservice: " + systemEvents.getMicroserviceName());
        System.out.println("Action: " + systemEvents.getAction());
        System.out.println("User: " + systemEvents.getUser());
        System.out.println("Resource: " + systemEvents.getResource());
        System.out.println("Response type: " + systemEvents.getResponseType());
        
        SystemEventResponse response = SystemEventResponse.newBuilder().setResponse("Event successfully recorded!").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
    }

    public ResponseEntity<List<SystemEvents>> getAllEvents() {
        List<SystemEvents> events = systemEventsRepository.findAll();
        return (!events.isEmpty()) ? ResponseEntity.ok(events) : ResponseEntity.notFound().build();
    }
}
