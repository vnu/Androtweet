package com.vnu.androtweet;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1"; 
	public static final String REST_CONSUMER_KEY = "A4OZh8ic0IgSwaovVE1w"; 
	public static final String REST_CONSUMER_SECRET = "4dAMsCpvhY2N8xCbE1vPFjBF7TPcO54qTlkZvwEI8zQ"; 
	public static final String REST_CALLBACK_URL = "oauth://androtweet"; 
	
	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public void getHomeTimeline(RequestParams params, AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/home_timeline.json");
		client.get(url, params, handler);
	}
	
	public void updateTweet(RequestParams params, AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/update.json");
		client.post(url, params, handler);
	}
	
	public void invalidate_token(AsyncHttpResponseHandler handler){
		String url = getApiUrl("/invalidate_token");
		client.post(url,null,handler);
	}

	/*
	 * 1. Define the endpoint URL with getApiUrl and pass a relative path to the
	 * endpoint i.e getApiUrl("statuses/home_timeline.json"); 2. Define the
	 * parameters to pass to the request (query or body) i.e RequestParams
	 * params = new RequestParams("foo", "bar"); 3. Define the request method
	 * and make a call to the client i.e client.get(apiUrl, params, handler);
	 * i.e client.post(apiUrl, params, handler);
	 */
}