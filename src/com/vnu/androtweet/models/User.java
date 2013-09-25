package com.vnu.androtweet.models;

import java.io.Serializable;

import org.json.JSONObject;

public class User extends BaseModel implements Serializable {
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
	private String jsonStr;

	public void initialize_user(BaseModel bm) {
		this.setName(bm.getString("name"));
		this.setUserId(String.valueOf(bm.getLong("id")));
		this.setScreenName(bm.getString("screen_name"));
		this.setProfileImgUrl(bm.getString("profile_image_url"));
		this.setProfileBgUrl(bm.getString("profile_background_image_url"));
		this.setDescription(bm.getString("description"));
		this.setTweets(bm.getInt("statuses_count"));
		this.setFollowers(bm.getInt("followers_count"));
		this.setFriends(bm.getInt("friends_count"));
		this.setJsonStr(bm.getJSONString());
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
			BaseModel bm = new BaseModel();
			bm.jsonObject = json;
//			u.jsonObject = json;
			u.initialize_user(bm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;

	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

}