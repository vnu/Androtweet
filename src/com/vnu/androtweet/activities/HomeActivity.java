package com.vnu.androtweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;
import com.vnu.androtweet.activities.fragments.HomeFragment;
import com.vnu.androtweet.activities.fragments.MentionsFragment;
import com.vnu.androtweet.activities.fragments.ProfileFragment;

public class HomeActivity extends SherlockFragmentActivity implements
		TabListener {
	Object selectedTab;
	ActionBar actionbar;
	FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setUpNavigationsTab();
	}

	private void setUpNavigationsTab() {
		actionbar = getSherlock().getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setDisplayShowTitleEnabled(true);

		Tab tabHome = actionbar.newTab().setTag("HomeFragment")
				.setIcon(R.drawable.ic_home).setTabListener(this);
		Tab tabMentions = actionbar.newTab().setTag("MentionsFragment")
				.setIcon(R.drawable.ic_mentions).setTabListener(this);
		Tab tabHash = actionbar.newTab().setTag("HashFragment")
				.setIcon(R.drawable.ic_hashtag).setTabListener(this);
		Tab tabProfile = actionbar.newTab().setTag("ProfileFragment")
				.setIcon(R.drawable.ic_profile).setTabListener(this);
		actionbar.addTab(tabHome);
		actionbar.addTab(tabMentions);
		actionbar.addTab(tabHash);
		actionbar.addTab(tabProfile);
		actionbar.selectTab(tabHome);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		selectedTab = tab.getTag();
		manager = getSupportFragmentManager();
		FragmentTransaction fts = manager.beginTransaction();
		if (selectedTab == "HomeFragment") {
			fts.replace(R.id.frame_container, new HomeFragment());
		} else if (selectedTab == "MentionsFragment") {
			fts.replace(R.id.frame_container, new MentionsFragment());
		} else if (selectedTab == "HashFragment") {
			fts.replace(R.id.frame_container, new HomeFragment());
		} else {
			fts.replace(R.id.frame_container, new ProfileFragment());
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSherlock().getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void onComposeView(MenuItem v) {
		Intent intent = new Intent(this, ComposeActivity.class);
		startActivityForResult(intent, ComposeActivity.COMPOSE_ACTIVITY_ID);
	}

	public void onLogout(MenuItem v) {
		AndroTweet.getRestClient().clearAccessToken();
		Intent logout = new Intent(this, LoginActivity.class);
		logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(logout);
		finish();
	}
}
