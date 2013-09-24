package com.vnu.androtweet.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.loopj.android.http.RequestParams;
import com.vnu.androtweet.R;
import com.vnu.androtweet.activities.fragments.ProfileFragment;
import com.vnu.androtweet.models.User;

public class UserProfileActivity extends SherlockFragmentActivity {
	RequestParams params;
	User user = null;
	ProfileFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		String userJ = getIntent().getStringExtra("user");
		fragment = new ProfileFragment();
		if (userJ != "" || !userJ.isEmpty()) {
			try {
				JSONObject jsonObject = new JSONObject(userJ);
				user = User.fromJson(jsonObject);
				getSupportActionBar().setTitle(user.getScreenName());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void onResume() {
		super.onResume();
		FragmentManager mgr = getSupportFragmentManager();
		FragmentTransaction ft = mgr.beginTransaction();
		if(fragment.getUser() != user){
			fragment.setUser(user);
			fragment.setProfUserline(null);
		}
		ft.replace(R.id.frame_profile, fragment);
		ft.commit();
	}
	
	public void onProfileClick(View v){
		Intent i = new Intent(this, UserProfileActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		User u = (User) v.getTag();
		i.putExtra("user", u.getJSONString());
		startActivity(i);
	}

}
