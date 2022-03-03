package com.elastic;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.ibatis.io.Resources;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONConsumer {
	private static JSONConsumer self;
	KafkaConsumer<String, String> consumer;
	private static final String TOPIC = "exam";
	
	private JSONConsumer(String group) throws IOException {
		String resource = "Link.properties";
		Properties properties = new Properties();
		Reader reader = Resources.getResourceAsReader(resource);
		properties.load(reader);
		properties.put("group.id", group);
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList(TOPIC));

	}
	
	public static JSONConsumer getInstace(String group) throws IOException{
		if(self ==null) self = new JSONConsumer(group);
		return self;
	}

	public void getData() {
		JSONParser jsonParser = new JSONParser();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(1000000);
//			System.out.println("message: "+records);
			
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("message: "+record.value());
			}
		}
	}
}
