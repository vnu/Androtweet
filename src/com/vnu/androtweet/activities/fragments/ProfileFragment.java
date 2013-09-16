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

public class ProfileFragment extends SherlockFragment{
	RequestParams params;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getProfileInfo();
	}
	
	public void setProfileInfo(User user){
		Log.e("I will come here",user.getName());
		ImageView ivProfile = (ImageView) getActivity().findViewById(R.id.ivProfile);
//		ImageView ivProfileHeader = (ImageView) getActivity().findViewById(R.id.ivProfileHeader);
		TextView tvScreenName = (TextView) getActivity().findViewById(R.id.tvScreenName);
		TextView tvStatus = (TextView) getActivity().findViewById(R.id.tvStatus);
		TextView tvTweets = (TextView) getActivity().findViewById(R.id.tvTweets);
		TextView tvFollowing = (TextView) getActivity().findViewById(R.id.tvFollowing);
		TextView tvFollowers = (TextView) getActivity().findViewById(R.id.tvFollowers);
		tvScreenName.setText(user.getName()+" @"+user.getScreenName());
		tvStatus.setText(user.getDescription());
		tvTweets.setText(user.getNumTweets()+" Tweets");
		tvFollowing.setText(user.getFriendsCount()+" Following");
		tvFollowers.setText(user.getFollowersCount()+" Followers");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfile);
//		ImageLoader.getInstance().displayImage(user.getProfileBackgroundImageUrl(), ivProfileHeader);
	}

	private void getProfileInfo() {
		AndroTweet.getRestClient().getUserInfo(params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User user = User.fromJson(json);
						Log.e("User",json.toString());
						setProfileInfo(user);
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);
						Toast.makeText(getActivity(), "Oops",
								Toast.LENGTH_LONG).show();
					}
				});
	}

}
