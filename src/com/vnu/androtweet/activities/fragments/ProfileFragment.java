package com.vnu.androtweet.activities.fragments;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;
import com.vnu.androtweet.models.User;

public class ProfileFragment extends SherlockFragment {
	RequestParams params;
	ImageView ivProfile;
	TextView tvScreenName;
	TextView tvStatus;
	TextView tvTweets;
	TextView tvFollowing;
	TextView tvFollowers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_profile, container, false);
		getProfileInfo();
		setUpViews(v);
		return v;
	}

	public void setUpViews(View v) {
		ivProfile = (ImageView) v.findViewById(R.id.ivProfile);
		tvScreenName = (TextView) v.findViewById(R.id.tvScreenName);
		tvStatus = (TextView) v.findViewById(R.id.tvStatus);
		tvTweets = (TextView) v.findViewById(R.id.tvTweets);
		tvFollowing = (TextView) v.findViewById(R.id.tvFollowing);
		tvFollowers = (TextView) v.findViewById(R.id.tvFollowers);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void setProfileInfo(User user) {
		tvScreenName.setText(user.getName() + " @" + user.getScreenName());
		tvStatus.setText(user.getDescription());
		tvTweets.setText(user.getNumTweets() + " Tweets");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(),
				ivProfile);
	}

	private void getProfileInfo() {
		AndroTweet.getRestClient().getUserInfo(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User user = User.fromJson(json);
						setProfileInfo(user);
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
