package com.academy.cloudstream;

import com.academy.cloudstream.event.ComplianceReport;
import com.academy.cloudstream.event.FraudAlert;
import com.academy.cloudstream.event.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class SpringCloudStreamConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamConsumerApplication.class, args);
	}

	@Bean
	public Consumer<TransactionEvent> receiveTransactionEvent(){
		return transactionEvent -> {
			log.info("TransactionEvent received: {}", transactionEvent);
		};
	}

	@Bean
	public Function<TransactionEvent, ComplianceReport> consumeAndReport(){
		return transactionEvent -> {
			return ComplianceReport.builder()
					.transactionId(transactionEvent.getTransactionId())
					.status("NOT APPROVED")
					.remark("Transaction amount exceeds allowed limit")
					.build();
		};
	}

	@Bean
	public Function<TransactionEvent, FraudAlert> consumeAndReportFraud(){
		return transactionEvent -> {
			return FraudAlert.builder()
					.transactionId(transactionEvent.getTransactionId())
					.type("FRAUD FOUND")
					.details("Transaction amount exceeds allowed limit")
					.build();
		};
	}
}
