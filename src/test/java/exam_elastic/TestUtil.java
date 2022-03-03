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
		String index = "java-es";
		String type = "test";
		ArrayList<JSONObject> data = null;
		
		Util elastic = Util.getInstace();

		// es�� ����� ������ select all
		data = elastic.getResponse(index);
		data.forEach(val->System.out.println("data : "+val));
		
		// es�� ������ insert
//		Map<String, Object> map = new HashMap<>();
//		map.put("���ο��׸�", 1);
//		map.put("test", 2);
//		XContentBuilder xContent = XContentFactory.jsonBuilder().map(map);
//		String jsonBody = Strings.toString(xContent);

		//		System.out.printf("jsonBody : %s\ntype : %s", jsonBody, jsonBody.getClass().getName());

//		IndexResponse response = elastic.create(index, type, jsonBody);
//		IndexResponse response = elastic.create(index, type, "{\"test\":5,\"���ο��׸�\":5}");
		
//		System.out.println(response);
	}
}
