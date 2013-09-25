package com.vnu.androtweet.activities.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.models.Tweet;

public class HomeFragment extends TweetlineFragment {

	public static final String TWEET_COUNT = "30";
	ArrayList<Tweet> tweets;
	RequestParams params;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
	};

	@Override
	public void onResume() {
		super.onResume();
		if (tweets.isEmpty()) {
			getHomeline(null);
		} else {
			getAdapter().addAll(tweets);
		}
	}

	public void getHomeline(RequestParams params) {
		AndroTweet.getRestClient().getHomeTimeline(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						tweets = Tweet.fromJson(jsonTweets);
						getAdapter().addAll(tweets);
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);
						Toast.makeText(getActivity(), "Oops", Toast.LENGTH_LONG)
								.show();
					}
				});
	}

	public void getOldTweets() {
		if (tweets != null && !tweets.isEmpty()) {
			params = new RequestParams();
			String max_id = tweets.get(tweets.size() - 1).getTweetId();
			params.put("max_id", max_id);
			params.put("count", TWEET_COUNT);
			getHomeline(params);
		}
	}

}
