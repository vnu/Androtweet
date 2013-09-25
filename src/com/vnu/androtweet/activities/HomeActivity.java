package com.vnu.androtweet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;
import com.vnu.androtweet.activities.fragments.AdaptableFragment;
import com.vnu.androtweet.adapters.ViewPagerAdapter;
import com.vnu.androtweet.models.Tweet;
import com.vnu.androtweet.models.User;

public class HomeActivity extends SherlockFragmentActivity implements
		TabListener {
	Object selectedTab;
	ActionBar actionbar;
	FragmentManager manager;
	Tab sTab;

	ViewPager mViewPager;
	ViewPagerAdapter mAppSectionsPagerAdapter;
	AdaptableFragment adapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setUpNavigationsTab();
	}

	private void setUpNavigationsTab() {
		mAppSectionsPagerAdapter = new ViewPagerAdapter(
				getSupportFragmentManager());
		actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setDisplayShowTitleEnabled(true);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionbar.setSelectedNavigationItem(position);
					}
				});

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
		// actionbar.selectTab(tabHome);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		adapFragment = (AdaptableFragment) mAppSectionsPagerAdapter.getItem(tab
				.getPosition());
		sTab = tab;
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent resultData) {
		if (requestCode == ComposeActivity.COMPOSE_ACTIVITY_ID) {
			if (resultCode == Activity.RESULT_OK) {
				Tweet tweet = (Tweet) resultData.getSerializableExtra("tweet");
				if(((String)sTab.getTag()).equals("HomeFragment")){
					adapFragment.prependTweet(tweet);
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.home, menu);
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

	public void onProfileClick(View v) {
		Intent i = new Intent(this, UserProfileActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		User u = (User) v.getTag();
		i.putExtra("user", u.getJsonStr());
		startActivity(i);
	}
}
