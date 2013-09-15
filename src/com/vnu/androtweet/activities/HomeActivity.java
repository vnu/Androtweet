package com.vnu.androtweet.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.vnu.androtweet.R;

public class HomeActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.home, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.action_compose:
//			tweets.clear();
//			Intent intent = new Intent(this, ComposeActivity.class);
//			startActivityForResult(intent, ComposeActivity.COMPOSE_ACTIVITY_ID);
//			break;
//		case R.id.action_logout:
//			AndroTweet.getRestClient().clearAccessToken();
//			Intent logout = new Intent(this, LoginActivity.class);
//			logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//					| Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivity(logout);
//			finish();
//		default:
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
