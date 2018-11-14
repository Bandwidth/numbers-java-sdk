package com.bandwidth.sdk.numbers.serde;

import com.bandwidth.sdk.numbers.models.orders.DebugUtils;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.catchClientExceptions;


public class NumbersSerde {

   private NumbersSerde() {
      // utility class; no instances
   }

   public static final XmlMapper MAPPER = new XmlMapper();

   static {
      MAPPER.registerModule(new GuavaModule());
   }

   public static <T> T deserialize(String messageBody, Class<T> clazz) {
      return catchClientExceptions(() -> MAPPER.readValue(messageBody, clazz));
   }

   public static <T> String serialize(T objectToMap) {
      return catchClientExceptions(() -> MAPPER.writeValueAsString(objectToMap));
   }
}
