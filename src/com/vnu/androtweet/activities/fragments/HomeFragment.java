package com.vnu.androtweet.activities.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.listeners.EndlessScrollListener;
import com.vnu.androtweet.models.Tweet;

public class HomeFragment extends TweetlineFragment {
	
	public static final String TWEET_COUNT = "30";
	ListView lvTweets;
//	ArrayList<Tweet> tweets;
//	TweetsAdapter tadapter;
	RequestParams params;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		getHomeline(null);
//		setUpInfiniteScroll();
	};
	
	@Override
	public void onResume() {
		super.onResume();
		getHomeline(null);
	}
	
	public void getHomeline(RequestParams params) {
		AndroTweet.getRestClient().getHomeTimeline(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
//						adapter.addAll(Tweet.fromJson(jsonTweets));
						getAdapter().addAll(Tweet.fromJson(jsonTweets));
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);

						Toast.makeText(getActivity(), "Oops",
								Toast.LENGTH_LONG).show();
					}
				});
	}
	
	public void getOldTweets() {
		if (tweets != null && !tweets.isEmpty()) {
			params = new RequestParams();
			String max_id = tweets.get(tweets.size() - 1).getIdStr();
			params.put("max_id", max_id);
			params.put("count", TWEET_COUNT);
			getHomeline(params);

		}
	}
	
	public void setUpInfiniteScroll() {
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void loadMore(int page, int totalItemsCount) {
				// whatever code is needed to append new items to your
				// AdapterView
				getOldTweets();
			}
		});
	}


}
