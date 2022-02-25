package com.elastic;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.ibatis.io.Resources;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Util {
	private static Util self;
	private static RestHighLevelClient client;
	
	private Util() throws IOException {
		Properties properties = new Properties();
		Reader reader = Resources.getResourceAsReader("Link.properties");
		properties.load(reader);
		String hostname = properties.getProperty("el.url");
		int port = Integer.parseInt(properties.getProperty("el.port"));
		HttpHost host = new HttpHost(hostname, port);
		RestClientBuilder restClientBuilder = RestClient.builder(host);
		client = new RestHighLevelClient(restClientBuilder);
	}
	
	public static Util getInstace() throws IOException{
		if(self ==null) self = new Util();
		return self;
	}
	
	public ArrayList<JSONObject> getResponse(String index){
		ArrayList<JSONObject> response = new ArrayList<>();
		
		RequestOptions options = RequestOptions.DEFAULT;
		SearchRequest sr = new SearchRequest();
		sr.indices(index);
		
		try {
			SearchResponse res = client.search(sr, options);
			SearchHits searchHits = res.getHits();
			System.out.println(searchHits.getTotalHits());
			
			for (SearchHit hit : searchHits) {
				response.add(new JSONObject(hit.getSourceAsMap()));
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	public IndexResponse create(String index, String type, String jsonBody) {
		IndexResponse response = null;
		IndexRequest indexRequest = new IndexRequest(index).type(type).source(jsonBody, XContentType.JSON);
		
		try {
			response = client.index(indexRequest, RequestOptions.DEFAULT);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
}


