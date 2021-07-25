/*package com.codez;

import com.amazonaws.services.lambda.invoke.
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

// Handler value: example.Handler
public class LambdaHandler implements RequestHandler<Map<String,String>, String>{

    @Override
    public String handleRequest(Map<String,String> event, Context context)
    {
        Map<String, String> reversed = new HashMap<>();
        for (Map.Entry<String, String> entry: event.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }
        String response = "200 OK";
        // log execution details
        // process event
        return reversed.keySet().toString();
    }
}*/