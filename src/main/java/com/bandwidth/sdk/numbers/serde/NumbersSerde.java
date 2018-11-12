package com.bandwidth.sdk.numbers.serde;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import static com.bandwidth.sdk.numbers.exception.ExceptionUtils.catchClientExceptions;


public class NumbersSerde {

   private NumbersSerde() {
      // utility class; no instances
   }

   private static final XmlMapper XML_MAPPER = new XmlMapper();

   static {
      XML_MAPPER.registerModule(new GuavaModule());
   }

   public static <T> T deserialize(String messageBody, Class<T> clazz) {
      //TODO: Remove this debug message
      System.out.println(messageBody);
      return catchClientExceptions(() -> XML_MAPPER.readValue(messageBody, clazz));
   }

   public static <T> String serialize(T objectToMap) {
      return catchClientExceptions(() -> {
         final String s = XML_MAPPER.writeValueAsString(objectToMap);
         //TODO: Remove this debug message
         System.out.println(s);
         return s;
      });
   }
}
