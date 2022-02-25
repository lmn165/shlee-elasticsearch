package exam_elastic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.json.simple.JSONObject;

import com.elastic.Util;

public class TestUtil {
	public static void main(String[] args) throws IOException{
		String index = "kafka-elk";
		String type = "logs";
		ArrayList<JSONObject> data = null;
		
		Util elastic = Util.getInstace();

		// es에 저장된 데이터 select all
		data = elastic.getResponse(index);
		data.forEach(val->System.out.println(val));
		
		// es에 데이터 insert
//		Map<String, Object> map = new HashMap<>();
//		map.put("새로운항목", 2);
//		map.put("test", 2);
//		
//		XContentBuilder xContent = XContentFactory.jsonBuilder().map(map);
//		String jsonBody = Strings.toString(xContent);
//		
//		IndexResponse response = elastic.create(index, type, jsonBody);
//		
//		System.out.println(response);
	}
}
