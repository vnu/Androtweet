package com.vnu.androtweet.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet implements Comparable<Tweet>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6138069312309799186L;
	private User user;
	private String body;
	private String tweetId;
	private Date createdAt;
	private boolean isFav;
	private boolean isRetweet;

	public void initialize_tweet(JSONObject jo) {
		try {
			this.setBody(jo.getString("text"));
			this.setTweetId(jo.getString("id_str"));
			this.setCreatedAt(jo.getString("created_at"));
			this.setIsFav(jo.getBoolean("favorited"));
			this.setIsRetweet(jo.getBoolean("retweeted"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(boolean isFav) {
		this.isFav = isFav;
	}

	public boolean getIsRetweet() {
		return isRetweet;
	}

	public void setIsRetweet(boolean isRetweet) {
		this.isRetweet = isRetweet;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setCreatedAt(String createdAt) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		Date date = null;
		try {
			date = sdf.parse(createdAt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.createdAt = date;
	}

	public User getUser() {
		return user;
	}

	public String getBody() {
		return this.body;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public static Tweet fromJson(JSONObject jsonObject) {
		Tweet tweet = new Tweet();
		try {
			tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
			tweet.initialize_tweet(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject tweetJson = null;
			try {
				tweetJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = Tweet.fromJson(tweetJson);
			if (tweet != null) {
				tweets.add(tweet);
			}
		}

		return tweets;
	}

	@Override
	public int compareTo(Tweet another) {
		return (int) (another.getCreatedAt().getTime() - this.getCreatedAt()
				.getTime());
	}
}