package com.example.tit.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.etf.proto.SystemEventRequest;
import com.etf.proto.SystemEventResponse;
import com.etf.proto.SystemEventsServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class EventInterceptor implements HandlerInterceptor {



    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        SystemEventsServiceGrpc.SystemEventsServiceBlockingStub stub = SystemEventsServiceGrpc.newBlockingStub(channel);
        SystemEventResponse eventResponse = stub.systemEventLog(
            SystemEventRequest.newBuilder()
                .setTimestamp(LocalDateTime.now().toString())
                .setMicroserviceName("TerminiITretmani")
                .setUser("admin")
                .setActionType(request.getMethod())
                .setResourceName(request.getRequestURI())
                .setResponseType(Integer.toString(response.getStatus()))
                .build()
        );
        System.out.println(eventResponse.getResponse());
        channel.shutdown();
    }
    
}
