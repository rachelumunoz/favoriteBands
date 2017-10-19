package io.rachelmunoz.favoritebands.ActivityMain;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.rachelmunoz.favoritebands.R;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	private ViewPager mViewPager;
	private SectionsPageAdapter mAdapter;

	private ArrayList<PagerItem> mPagerItems = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mViewPager = (ViewPager) findViewById(R.id.tab_fragment_container);
		setupViewPager(mViewPager);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				// Refreshes Fragments to display newest data
				ArrayList<PagerItem> pagerItems = new ArrayList<>();

				pagerItems.add(new PagerItem(getString(R.string.search_tab), new SearchFragment()));
				pagerItems.add(new PagerItem(getString(R.string.favorite_tab), new FavoriteFragment()));

				mAdapter.setPagerItems(pagerItems);
				mAdapter.notifyDataSetChanged();
			}
			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}


	private void setupViewPager(final ViewPager viewPager){
		ArrayList<PagerItem> pagerItems = new ArrayList<>();

		pagerItems.add(new PagerItem(getString(R.string.search_tab), new SearchFragment()));
		pagerItems.add(new PagerItem(getString(R.string.favorite_tab), new FavoriteFragment()));

		mAdapter = new SectionsPageAdapter(getSupportFragmentManager(), pagerItems);
		mAdapter.setPagerItems(pagerItems);

		viewPager.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
	}




}
