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

	public void getHomeTimeline(RequestParams params,
			AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/home_timeline.json");
		client.get(url, params, handler);
	}

	public void updateTweet(RequestParams params,
			AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/update.json");
		client.post(url, params, handler);
	}

	public void getMentions(RequestParams params,
			AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/mentions_timeline.json");
		client.get(url, params, handler);
	}

	public void getRateLimit(RequestParams params,
			AsyncHttpResponseHandler handler) {
		params = new RequestParams();
		params.put("resources", "mentions_line");
		String url = getApiUrl("application/rate_limit_status.json");
		client.get(url, params, handler);
	}

	public void getUserInfo(RequestParams params,
			AsyncHttpResponseHandler handler) {
		String url = getApiUrl("account/verify_credentials.json");
		client.get(url, params, handler);
	}

	public void getBanner(String username, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("screen_name", username);
		String url = getApiUrl("users/profile_banner.json");
		client.get(url, params, handler);
	}

	public void invalidate_token(AsyncHttpResponseHandler handler) {
		String url = getApiUrl("/invalidate_token");
		client.post(url, null, handler);
	}

}