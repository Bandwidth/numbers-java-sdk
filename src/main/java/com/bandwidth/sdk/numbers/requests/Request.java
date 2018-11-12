package com.bandwidth.sdk.numbers.requests;

import org.asynchttpclient.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {

   private Map<String, String> queryParams = new HashMap<>();

   public void addQueryParam(String name, Object value) {
      queryParams.put(name, String.valueOf(value));
   }

   public List<Param> toParams() {
      return queryParams.entrySet().stream()
         .map(entry -> new Param(entry.getKey(), entry.getValue()))
         .collect(Collectors.toList());
   }
}
