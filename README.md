# Bandwidth Numbers Java SDK

Java SDK for Bandwidth's number management.

## Dependency

### Maven
```xml
<dependency>
    <groupId>com.bandwidth.sdk</groupId>
    <artifactId>numbers</artifactId>
    <version>(put desired version here)</version>
</dependency>
```

### Gradle
```
compile 'com.bandwidth.sdk:numbers:(put desired version here)'
```


## Quick Start

### Construct the client
```java
NumbersClientImpl numbersClient = NumbersClientImpl.builder()
   .account("1")
   .username("username")
   .password("password")
   .build();

// Optionally an AsyncHttpClientConfig may be provided to fine tune settings
NumbersClientImpl numbersClient = NumbersClientImpl.builder()
   .account("1")
   .username("username")
   .password("password")
   .config(
       new DefaultAsyncHttpClientConfig.Builder()
      .setRequestTimeout(60_000)
      .build())
   .build();
```

### Search for available telephone numbers
```java
AvailableNumberSearchRequest availableNumberSearchRequest = AvailableNumberSearchRequest.builder()
   .state("NC")
   .city("CARY")
   .enableTNDetail(false)
   .quantity(10)
   .build();

SearchResult searchResult = numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest);
```

### Place an order for telephone numbers
```java
// Each order type has a separate implementation
ExistingTelephoneNumberOrderType existingTelephoneNumberOrderType = ExistingTelephoneNumberOrderType.builder()
   .addTelephoneNumberList("8042994451")
   .build();

// Wrap the order type in an Order wrapper and choose site id and peer for numbers to associated with
Order order = Order.builder()
   .siteId("1")
   .peerId("500539")
   .existingTelephoneNumberOrderType(existingTelephoneNumberOrderType)
   .build();

OrderResponse orderResponse = numbersClient.orderTelephoneNumbers(order);
```

### Important Links
* [Bandwidth Dashboard](https://dashboard.bandwidth.com/portal/report/#login:)
* [Bandwidth Application](https://app.bandwidth.com/login)
* [Bandwidth Developer Homepage](https://dev.bandwidth.com/)



```java
public class MyAwesomeBandwidthNumbersApp {
    
    public static void main(String[] args) {
       
          try (NumbersClientImpl numbersClient = NumbersClientImpl.builder()
             .account("1")
             .username("username")
             .password("password")
             .build()) {
    
             AvailableNumberSearchRequest availableNumberSearchRequest = AvailableNumberSearchRequest.builder()
                .state("NC")
                .city("CARY")
                .enableTNDetail(false)
                .quantity(10)
                .build();
    
             SearchResult searchResult = numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest);
    
             // putting it all together, place an order for numbers returned from a number search
             ExistingTelephoneNumberOrderType existingTelephoneNumberOrderType =
                ExistingTelephoneNumberOrderType.builder()
                   .addAllTelephoneNumberList(searchResult.getTelephoneNumberList())
                   .build();
    
             Order order = Order.builder()
                .siteId("1")
                .peerId("500539")
                .existingTelephoneNumberOrderType(existingTelephoneNumberOrderType)
                .build();
    
             OrderResponse orderResponse = numbersClient.orderTelephoneNumbers(order);
          }
       }
}
```