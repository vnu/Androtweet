package com.vnu.androtweet.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 766746845263863121L;

	private String Name;
	private String userId;
	private String screenName;
	private String profileImgUrl;
	private String profileBgUrl;
	private String description;
	private int tweets;
	private int followers;
	private int friends;

	public void initialize_user(JSONObject jo) {
		try {
			this.setName(jo.getString("name"));
			this.setUserId(String.valueOf(jo.getLong("id")));
			this.setScreenName(jo.getString("screen_name"));
			this.setProfileImgUrl(jo.getString("profile_image_url"));
			this.setProfileBgUrl(jo.getString("profile_background_image_url"));
			this.setDescription(jo.getString("description"));
			this.setTweets(jo.getInt("statuses_count"));
			this.setFollowers(jo.getInt("followers_count"));
			this.setFriends(jo.getInt("friends_count"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	public String getProfileBgUrl() {
		return profileBgUrl;
	}

	public void setProfileBgUrl(String profileBgUrl) {
		this.profileBgUrl = profileBgUrl;
	}

	public int getTweets() {
		return tweets;
	}

	public void setTweets(int tweets) {
		this.tweets = tweets;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFriends() {
		return friends;
	}

	public void setFriends(int friends) {
		this.friends = friends;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static User fromJson(JSONObject json) {
		User u = new User();
		try {
			u.initialize_user(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;

	}

}