package com.vnu.androtweet.activities.fragments;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.vnu.androtweet.models.Tweet;
import com.vnu.androtweet.models.User;

public class ProfileFragment extends SherlockFragment implements AdaptableFragment {
	RequestParams params;
	ImageView ivProfile;
	TextView tvScreenName;
	TextView tvStatus;
	TextView tvTweets;
	TextView tvFollowing;
	TextView tvFollowers;
	private User user;
	View v;
	FragmentManager profManager;
	private UserlineFragment profUserline = null;

	public UserlineFragment getProfUserline() {
		return profUserline;
	}

	public void setProfUserline(UserlineFragment profUserline) {
		this.profUserline = profUserline;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_profile, container, false);
		setUpViews(v);
		if (getUser() == null) {
			getProfileInfo();
		} else {
			setProfileInfo();
		}
		return v;
	}

	public void setUserTimeline() {
		profManager = getChildFragmentManager();
		FragmentTransaction fts = profManager.beginTransaction();
		if (profUserline == null) {
			profUserline = new UserlineFragment();
		}
		fts.replace(R.id.frlayContainer, profUserline);
		fts.commit();
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

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void setProfileInfo() {
		getActivity().getActionBar().setTitle(user.getScreenName());
		tvScreenName.setText(user.getName() + " @" + user.getScreenName());
		tvScreenName.setTag(user.getScreenName());
		tvStatus.setText(user.getDescription());
		tvTweets.setText(user.getNumTweets() + " Tweets");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(),
				ivProfile);
		setUserTimeline();
	}

	private void getProfileInfo() {
		AndroTweet.getRestClient().getUserInfo(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						user = User.fromJson(json);
						setProfileInfo();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);
						Toast.makeText(getActivity(), "Oops", Toast.LENGTH_LONG)
								.show();
					}
				});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void prependTweet(Tweet tweet) {
		TweetlineFragment tf = (TweetlineFragment)getChildFragmentManager().findFragmentById(R.id.frlayContainer);
		tf.prependTweet(tweet);
	}

}
