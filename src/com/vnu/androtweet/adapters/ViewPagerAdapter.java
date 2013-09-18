/**
 * 
 */
package com.vnu.androtweet.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.vnu.androtweet.activities.fragments.HomeFragment;
import com.vnu.androtweet.activities.fragments.MentionsFragment;
import com.vnu.androtweet.activities.fragments.ProfileFragment;

/**
 * @author vnu
 *
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 * This where the fragment objects are returned based on Position
	 */
	@Override
	public Fragment getItem(int position) {
		SherlockFragment fragment;
        switch (position) {
            case 0:
            	fragment = new HomeFragment();
                break;
            case 1:
            	fragment = new MentionsFragment();
                break;
            case 2:
            	fragment = new MentionsFragment();
                break;
            case 3:
            	fragment = new ProfileFragment();
                break;
            default:
            	fragment = new HomeFragment();
                break;
              
        }
        return fragment;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return 4;
	}
	
	@Override
    public CharSequence getPageTitle(int position) {
        return "Section " + (position + 1);
    }

}
