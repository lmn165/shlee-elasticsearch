package exam_elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestElastic {
	public static void main(String[] args) throws IOException {
		System.out.println("starting...");
		String hostname = "localhost";
		int port = 9200;
		String scheme = "http";
		HttpHost host = new HttpHost(hostname, port, scheme);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

		String index = "kafka-elk";
//		String type = "logs";
//		String id = "AX8lZ6NaPixPgVKWHc3e";
//		GetRequest getRequest = new GetRequest(index, type, id);
		RequestOptions options = RequestOptions.DEFAULT;
//		
//		GetResponse response = client.get(getRequest, options);
//		System.out.println(response.getSource().get("message"));

		SearchRequest sr = new SearchRequest();
		sr.indices(index);
		SearchResponse res = client.search(sr, options);
		SearchHits searchHits = res.getHits();
		
		System.out.println(searchHits.getTotalHits());
		
		JSONParser jsonParser = new JSONParser();
		
		try {
			for (SearchHit hit : searchHits) {
				System.out.println(hit.getSourceAsMap().get("message"));
				JSONObject jsonObj= (JSONObject) jsonParser.parse(hit.getSourceAsMap().get("message").toString());
				jsonObj.keySet().forEach(val -> System.out.printf("%s : %s\n", val, jsonObj.get(val)));
				System.out.println("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
		client.close();
	}
}
