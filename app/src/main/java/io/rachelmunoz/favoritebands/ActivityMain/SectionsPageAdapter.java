package io.rachelmunoz.favoritebands.ActivityMain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class SectionsPageAdapter extends FragmentStatePagerAdapter {

	private final List<Fragment> mFragmentList = new ArrayList<>();
	private final List<String> mFragmentTitleList = new ArrayList<>();

	public SectionsPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void addFragment(Fragment fragment, String title){
		mFragmentList.add(fragment);
		mFragmentTitleList.add(title);
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return mFragmentTitleList.get(position);
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		return  mFragmentList.size();
	}
}
