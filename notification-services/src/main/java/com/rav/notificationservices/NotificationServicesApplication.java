package com.rav.notificationservices;

import com.rav.notificationservices.event.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
public class NotificationServicesApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(NotificationServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NotificationServicesApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		//send out email notification logic to be written
		LOGGER.info("Notification received for placed order- {}", orderPlacedEvent.getOrderNumber());
	}

}
