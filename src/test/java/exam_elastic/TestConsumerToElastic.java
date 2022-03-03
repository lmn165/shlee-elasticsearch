package exam_elastic;

import java.io.Reader;
import java.util.Collections;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.elasticsearch.action.index.IndexResponse;

import com.elastic.Util;

public class TestConsumerToElastic {
	private static final String TOPIC = "exam";

	public static void main(String[] args) {
		String resource = "Link.properties";
		Properties properties = new Properties();
		KafkaConsumer<String, String> consumer = null;
		
		try {
			System.out.println("now starting...");
			Util elastic = Util.getInstace();
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
			properties.put("group.id", "group01");
			
			consumer = new KafkaConsumer<>(properties);
			consumer.subscribe(Collections.singletonList(TOPIC));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(1000000);
				for (ConsumerRecord<String, String> record : records) {
					IndexResponse response = elastic.create("java-es", "test", record.value());
					System.out.println(response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
