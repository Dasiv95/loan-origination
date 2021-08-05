package com.svb.loanorigination;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class LoanoriginationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanoriginationApplication.class, args);
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, String> postBookingRequest() {
//		return (requestEvent) -> orderDao.buildOrders()
//				.stream()
//				.filter(order -> order.getName().equals(requestEvent.getQueryStringParameters().get("orderName")))
//				.collect(Collectors.toList());
		//lets send the data to kinesis here
//		AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();
//
//		clientBuilder.setRegion(Regions.US_EAST_1);
//		clientBuilder.setCredentials(credentialsProvider);
//		clientBuilder.setClientConfiguration(config);

		AmazonKinesis clientBuilder = KinesisClient.getKinesisClient();


		PutRecordsRequest putRecordsRequest  = new PutRecordsRequest();
		putRecordsRequest.setStreamName("loan-origination");
		List <PutRecordsRequestEntry> putRecordsRequestEntryList  = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			PutRecordsRequestEntry putRecordsRequestEntry  = new PutRecordsRequestEntry();
			putRecordsRequestEntry.setData(ByteBuffer.wrap(String.valueOf(i).getBytes()));
			putRecordsRequestEntry.setPartitionKey(String.format("partitionKey-%d", i));
			putRecordsRequestEntryList.add(putRecordsRequestEntry);
		}

		putRecordsRequest.setRecords(putRecordsRequestEntryList);
		PutRecordsResult putRecordsResult  = clientBuilder.putRecords(putRecordsRequest);
		System.out.println("Put Result" + putRecordsResult);


		return (requestEvent) -> "Booking request is successful";
	}

}
