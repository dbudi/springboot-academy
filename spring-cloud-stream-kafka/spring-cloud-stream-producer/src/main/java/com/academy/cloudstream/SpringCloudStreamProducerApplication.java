package com.academy.cloudstream;

import com.academy.cloudstream.event.TransactionEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringCloudStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamProducerApplication.class, args);
	}

	@Bean
	public Supplier<TransactionEvent> sendTransactionEvent(){
		return this::getTransactionEvent;
	}

	public TransactionEvent getTransactionEvent(){
		return TransactionEvent.builder()
				.transactionId(RandomStringUtils.randomAlphanumeric(8) + RandomUtils.nextInt())
				.amount(RandomUtils.nextDouble())
				.senderId(RandomStringUtils.randomAlphanumeric(8))
				.receiverId(RandomStringUtils.randomAlphanumeric(8))
				.type("TRANSFER")
				.currency("IDR")
				.dateTime(LocalDateTime.now())
				.build();
	}
}
