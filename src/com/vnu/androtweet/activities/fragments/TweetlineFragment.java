package com.vnu.androtweet.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.vnu.androtweet.R;
import com.vnu.androtweet.adapters.TweetsAdapter;
import com.vnu.androtweet.models.Tweet;

public class TweetlineFragment extends SherlockFragment {
	public static final String TWEET_COUNT = "30";
	ListView lvTweets;
	ArrayList<Tweet> tweets;
	TweetsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_tweetline, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initialConfig();
	}
	
	public void initialConfig() {
		tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		lvTweets.setAdapter(adapter);
	}
	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
}
