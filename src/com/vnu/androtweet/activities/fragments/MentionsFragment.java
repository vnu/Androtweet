/**
 * 
 */
package com.vnu.androtweet.activities.fragments;

import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.models.Tweet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * @author vnu
 * 
 */
public class MentionsFragment extends TweetlineFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getMentionsline(null);
	}

	@Override
	public void onResume() {
		super.onResume();
//		if (tweets.isEmpty()) {
			getMentionsline(null);
//		} else {
//			getAdapter().addAll(tweets);
//		}
//		getMentionsline(null);
	}

	public void getMentionsline(RequestParams params) {
		AndroTweet.getRestClient().getMentions(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						// adapter.addAll(Tweet.fromJson(jsonTweets));
						Log.e("Tweets",jsonTweets.toString());
						getAdapter().addAll(Tweet.fromJson(jsonTweets));
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
