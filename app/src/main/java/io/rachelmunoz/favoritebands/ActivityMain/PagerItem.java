package io.rachelmunoz.favoritebands.ActivityMain;

import android.support.v4.app.Fragment;

/**
 * Created by rachelmunoz on 10/17/17.
 */

public class PagerItem {
	private String mTitle;
	private Fragment mFragment;


	public PagerItem(String mTitle, Fragment mFragment) {
		this.mTitle = mTitle;
		this.mFragment = mFragment;
	}
	public String getTitle() {
		return mTitle;
	}
	public Fragment getFragment() {
		return mFragment;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public void setFragment(Fragment mFragment) {
		this.mFragment = mFragment;
	}

}