package io.rachelmunoz.favoritebands.ActivityMain;

import android.support.v4.app.Fragment;

/**
 * Created by rachelmunoz on 10/17/17.
 */

public class PagerItem {
	private String mTitle;
	private Fragment mFragment;

	public PagerItem(String title, Fragment fragment) {
		mTitle = title;
		mFragment = fragment;
	}
	public String getTitle() {
		return mTitle;
	}
	public Fragment getFragment() {
		return mFragment;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public void setFragment(Fragment fragment) {
		mFragment = fragment;
	}

}