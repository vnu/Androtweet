package com.vnu.androtweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.vnu.androtweet.AndroTweet;
import com.vnu.androtweet.R;
import com.vnu.androtweet.activities.fragments.HomeFragment;
import com.vnu.androtweet.activities.fragments.MentionsFragment;



//public class HomeActivity extends SherlockFragmentActivity implements
//		TabListener {
public class HomeActivity extends SherlockFragmentActivity implements TabListener{
	Object selectedTab;
	ActionBar actionbar;
	FragmentManager manager;
	
	ViewPager mViewPager;
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setUpNavigationsTab();
	}

	private void setUpNavigationsTab() {
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
		actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setDisplayShowTitleEnabled(true);
		
		 mViewPager = (ViewPager) findViewById(R.id.pager);
	        mViewPager.setAdapter(mAppSectionsPagerAdapter);
	        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	            @Override
	            public void onPageSelected(int position) {
	                // When swiping between different app sections, select the corresponding tab.
	                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
	                // Tab.
	            	Log.e("Position",String.valueOf(position));
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
//		actionbar.selectTab(tabHome);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
//		selectedTab = tab.getTag();
////		manager = getSupportFragmentManager();
////		FragmentTransaction fts = manager.beginTransaction();
////		if (selectedTab == "HomeFragment") {
//			mViewPager.setCurrentItem(0);
////			fts.replace(R.id.frame_container, new HomeFragment());
//		} else if (selectedTab == "MentionsFragment") {
//			mViewPager.setCurrentItem(1);
////			fts.replace(R.id.frame_container, new MentionsFragment());
//		} else if (selectedTab == "HashFragment") {
//			mViewPager.setCurrentItem(2);
////			fts.replace(R.id.frame_container, new HomeFragment());
//		} else {
//			mViewPager.setCurrentItem(3);
////			fts.replace(R.id.frame_container, new MentionsFragment());
//		}
////		fts.commit();
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
	
	 public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

	        public AppSectionsPagerAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int i) {
	        	SherlockFragment fragment;
	            switch (i) {
	                case 0:
	                    // The first section of the app is the most interesting -- it offers
	                    // a launchpad into the other demonstrations in this example application.
	                	Log.e("PositionF","0");
	                	fragment = new HomeFragment();
	                    break;
	                case 1:
	                	Log.e("PositionF","1");
	                	fragment = new MentionsFragment();
	                    break;
	                case 2:
	                	Log.e("PositionF","2");
	                	fragment = new MentionsFragment();
	                    break;
	                case 3:
	                	Log.e("PositionF","3");
	                	fragment = new HomeFragment();
	                    break;

	                default:
	                    // The other sections of the app are dummy placeholders.
	                	fragment = new HomeFragment();
	                    break;
	                  
	            }
	            return fragment;
	        }

	        @Override
	        public int getCount() {
	            return 4;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return "Section " + (position + 1);
	        }
	    }
}
