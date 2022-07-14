package com.jobRecommendation.job.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobRecommendation.job.entity.Item;
import com.jobRecommendation.job.entity.Item;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class MuseClient {
    private static final String URL_TEMPLATE = "https://remotive.com/api/remote-jobs?search=%s&limit=3";
    private static final String DEFAULT_KEYWORD = "developer";

    private static void extractKeywords(List<Item> items) {
        MonkeyLearnClient monkeyLearnClient = new MonkeyLearnClient();
        List<String> descriptions = new ArrayList<>();
        for (Item item : items) {
            String description = item.getTitle();
            descriptions.add(description);
        }

        List<Set<String>> keywordList = monkeyLearnClient.extract(descriptions);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setKeywords(keywordList.get(i));
        }
    }

    public List<Item> search(String keyword) {
        if (keyword == null) {
            keyword = DEFAULT_KEYWORD;
        }

        // “hello world” => “hello%20world”
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format(URL_TEMPLATE, keyword);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        // Create a custom response handler
        ResponseHandler<List<Item>> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return Collections.emptyList();
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return Collections.emptyList();
            }
            String retSrc = EntityUtils.toString(entity);
            // parsing JSON
            JSONObject result = new JSONObject(retSrc);
            JSONArray rsltList = result.getJSONArray("jobs");
            ObjectMapper mapper = new ObjectMapper();
            String s = rsltList.toString();
            List<Item> items = Arrays.asList(mapper.readValue(s, Item[].class));
            extractKeywords(items);
            return items;
        };

        try {
            return httpclient.execute(new HttpGet(url), responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

