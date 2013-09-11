package com.vnu.androtweet.activities;

import org.json.JSONArray;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;

public class ComposeActivity extends Activity {
	public static final int COMPOSE_ACTIVITY_ID = 200;
	public static final int TWEET_SIZE = 140;
	static Button notifCount;
//	static Button btnTweet;

	EditText etCompose;
	int tweetCnt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		tweetCnt = TWEET_SIZE;
		// Show the Up button in the action bar.
		setupActionBar();
		setUpViews();
		registerTweetCounter();
	}

	public void setUpViews() {
		etCompose = (EditText) findViewById(R.id.etCompose);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		
		 View count = menu.findItem(R.id.action_count).getActionView();
//		 View tweet = menu.findItem(R.id.action_tweet).getActionView();
		    notifCount = (Button) count.findViewById(R.id.notif_count);
//		    btnTweet = (Button) tweet.findViewById(R.id.btnTweet);
		    return super.onCreateOptionsMenu(menu);
//		return true;
	}
	

	
	private void registerTweetCounter(){
		etCompose.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable textView) {				
				int len = etCompose.length();
				tweetCnt = TWEET_SIZE - len;
				notifCount.setText(String.valueOf(tweetCnt));
				if (tweetCnt < 0 || tweetCnt == 140){
					notifCount.setTextColor(getResources().getColor(R.color.red));
//					btnTweet.setTextColor(getResources().getColor(R.color.white));
//					btnTweet.setClickable(false);
//					btnTweet.setEnabled(false);
				}
				if (tweetCnt >= 0 && tweetCnt < 140){
					notifCount.setTextColor(getResources().getColor(R.color.white));
//					btnTweet.setTextColor(getResources().getColor(R.color.twitter_logo));
//					btnTweet.setClickable(true);
//					btnTweet.setEnabled(true);
				}
				return;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				return;
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int count, int after) {
				return;
			}
		});		
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, getParentActivityIntent());
			return true;
		case R.id.action_tweet:
			updateTweet();
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
	
	public void updateTweet(){
		String tweetStatus = etCompose.getText().toString();
		RequestParams params = new RequestParams();
		params.put("status", tweetStatus);

		AndroTweet.getRestClient().updateTweet(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray Tweets) {
						Toast.makeText(getApplicationContext(), "Coolio",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFinish() {
						returnToTimeline();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Toast.makeText(getApplicationContext(), "Oops",
								Toast.LENGTH_LONG).show();
					}
				});
	}

	@Override
	public void onBackPressed() {
		returnToTimeline();
		super.onBackPressed();
	}

	public void returnToTimeline() {
		Intent i = new Intent();
		if (getParent() == null) {
			setResult(Activity.RESULT_OK, i);
		} else {
			getParent().setResult(Activity.RESULT_OK, i);
		}
		finish();
	}

}
