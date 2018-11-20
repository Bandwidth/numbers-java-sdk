package com.bandwidth.sdk.numbers;

import com.bandwidth.sdk.numbers.models.AvailableNumberSearchRequest;
import com.bandwidth.sdk.numbers.models.ImmutableSearchResult;
import com.bandwidth.sdk.numbers.models.SearchResult;
import com.bandwidth.sdk.numbers.serde.NumbersSerde;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumbersClientImplTest {

   private NumbersClient numbersClient;

   private AvailableNumberSearchRequest availableNumberSearchRequest = AvailableNumberSearchRequest.builder()
      .state("NC")
      .city("RALEIGH")
      .quantity(5)
      .build();

   private SearchResult searchResult = ImmutableSearchResult.builder()
      .resultCount(1)
      .addTelephoneNumberList("5555555555")
      .build();

   @Mock private AsyncHttpClient asyncHttpClient;
   @Mock private BoundRequestBuilder boundRequestBuilder;
   @Mock private ListenableFuture<Response> listenableFuture;
   @Mock private Response response;

   @Before
   public void onBefore() {
      numbersClient = new NumbersClientImpl("base", "account", asyncHttpClient);
   }

   @Test
   public void searchingForNumbers() {
      when(asyncHttpClient.prepareGet(anyString())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.setQueryParams(anyList())).thenReturn(boundRequestBuilder);
      when(boundRequestBuilder.execute()).thenReturn(listenableFuture);
      when(listenableFuture.toCompletableFuture()).thenReturn(CompletableFuture.completedFuture(response));
      when(response.getResponseBody(StandardCharsets.UTF_8)).thenReturn(NumbersSerde.serialize(searchResult));

      SearchResult searchResult = numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest);

      assertThat(searchResult).isEqualTo(numbersClient.getAvailableTelephoneNumbers(availableNumberSearchRequest));
   }

}