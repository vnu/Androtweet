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
		View v = inflater.inflate(R.layout.fragment_tweetline, container, false);
		lvTweets = (ListView) v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(adapter);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialConfig();
	}
	
	public void initialConfig() {
		tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getActivity(), tweets);
	}
	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
}
