package com.vnu.androtweet.activities;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;
import com.vnu.androtweet.adapters.TweetsAdapter;
import com.vnu.androtweet.listeners.EndlessScrollListener;
import com.vnu.androtweet.models.Tweet;

public class HomeActivity extends Activity {
	public static final String TWEET_COUNT = "30";
	ListView lvTweets;
	ArrayList<Tweet> tweets;
	TweetsAdapter adapter;
	RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initialConfig();
		setUpInfiniteScroll();

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

	public void getOldTweets() {
		if (tweets != null && !tweets.isEmpty()) {
			params = new RequestParams();
			String max_id = tweets.get(tweets.size() - 1).getIdStr();
			params.put("max_id", max_id);
			params.put("count", TWEET_COUNT);
			getHomeline(params);

		}
	}

	public void initialConfig() {
		lvTweets = (ListView) findViewById(R.id.lvTweets);
		tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getBaseContext(), tweets);
		lvTweets.setAdapter(adapter);
	}

	public void getHomeline(RequestParams params) {
		AndroTweet.getRestClient().getHomeTimeline(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						tweets.addAll(Tweet.fromJson(jsonTweets));
						Collections.sort(tweets);
						adapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);

						Toast.makeText(getApplicationContext(), "Oops",
								Toast.LENGTH_LONG).show();
					}
				});
	}

	@Override
	protected void onResume() {
		getHomeline(null);
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_compose:
			tweets.clear();
			Intent intent = new Intent(this, ComposeActivity.class);
			startActivityForResult(intent, ComposeActivity.COMPOSE_ACTIVITY_ID);
			break;
		case R.id.action_logout:
			AndroTweet.getRestClient().clearAccessToken();
			Intent logout = new Intent(this, LoginActivity.class);
			logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(logout);
			finish();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
