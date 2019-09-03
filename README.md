# Bandwidth Numbers Java SDK

Java SDK for Bandwidth's number management API.

## Release Notes

| Version | Notes |
|:---|:---|
| 0.6.0  | Fixed `quantity` and `tollFreeWildCardPattern` for search requests |

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

All objects in the SDK follow the Builder pattern for easy construction. To search for and order numbers, construct the 
relevant request and pass it to the appropriate client method.

### Construct the client
Instances of the NumbersClient must be closed, typically on application shutdown to avoid resource leaks. Use the 
provided close method. The client also implements the AutoClosable interface for convenient use in try-with-resources 
blocks.

The client builder exposes a configuration method that allows access to the underlying AsyncHttpClient configuration
should you want to configure it. See the [documentation](https://github.com/AsyncHttpClient/async-http-client) for that 
project to see what can be configured.
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
   .tollFreeWildCardPattern("8**")
   .build();

SearchResult searchResult = numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest);
```

### Place an order for telephone numbers
Placing an order is a synchronous operation that will submit an order request to bandwidth and poll until the order has 
completed behind the scenes. One invocation may result in several API calls in the background before control returns to 
the calling thread.
```java
// Each order type has a separate implementation
ExistingTelephoneNumberOrderType existingTelephoneNumberOrderType = ExistingTelephoneNumberOrderType.builder()
   .addTelephoneNumberList("8042994451")
   .build();

// Wrap the order type in an Order wrapper and choose site id and peer for numbers to be associated with
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
