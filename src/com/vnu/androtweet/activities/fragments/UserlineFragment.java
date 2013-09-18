/**
 * 
 */
package com.vnu.androtweet.activities.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.models.Tweet;

/**
 * @author vnu
 * 
 */
@SuppressLint("NewApi")
public class UserlineFragment extends TweetlineFragment {

	ArrayList<Tweet> tweets;
	RequestParams params = null;
	String screen_name = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// v = getActivity().findViewById(R.id.tvScreenName);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void getOldTweets() {
		if (tweets != null && !tweets.isEmpty()) {
			params = new RequestParams();
			String max_id = tweets.get(tweets.size() - 1).getIdStr();
			params.put("max_id", max_id);
			params.put("count", TWEET_COUNT);
			getUserline(params);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
		if (getActivity().getLocalClassName().equalsIgnoreCase(
				"activities.UserProfileActivity")) {
			params = new RequestParams();
			screen_name = (String) getActivity().getActionBar().getTitle();
			params.put("screen_name", screen_name);
		}
		if (tweets.isEmpty()) {
			getUserline(params);
		} else {
			getAdapter().addAll(tweets);
		}
	}

	public void getUserline(RequestParams params) {
		AndroTweet.getRestClient().getUserTimeline(params,
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

}
