package io.rachelmunoz.favoritebands.ActivityMain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class SectionsPageAdapter extends FragmentStatePagerAdapter {

	private final List<Fragment> mFragmentList = new ArrayList<>();
	private final List<String> mFragmentTitleList = new ArrayList<>();
	private  FragmentManager mFragmentManager;
	private  ArrayList<PagerItem> mPagerItems;

	public SectionsPageAdapter(FragmentManager fm, ArrayList<PagerItem> pagerItems) {
		super(fm);
		mFragmentManager = fm;
		mPagerItems = pagerItems;
	}


	public void addFragment(Fragment fragment, String title){
		mFragmentList.add(fragment);
		mFragmentTitleList.add(title);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mPagerItems.get(position).getTitle();
	}

	@Override
	public Fragment getItem(int position) {
		return mPagerItems.get(position).getFragment();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return  mPagerItems.size();
	}

	public void setPagerItems(ArrayList<PagerItem> pagerItems) {
		if (mPagerItems != null)
			for (int i = 0; i < mPagerItems.size(); i++) {
				mFragmentManager.beginTransaction().remove(mPagerItems.get(i).getFragment()).commit();
			}
		mPagerItems = pagerItems;
	}

}
