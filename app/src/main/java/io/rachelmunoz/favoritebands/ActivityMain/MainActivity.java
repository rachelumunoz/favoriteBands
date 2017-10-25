package io.rachelmunoz.favoritebands.ActivityMain;

import android.nfc.Tag;
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

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mViewPager = (ViewPager) findViewById(R.id.tab_fragment_container);
		setupViewPage(mViewPager);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

	}

	private void setupViewPage(ViewPager viewPager){
		SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

		adapter.addFragment(new SearchFragment(), getString(R.string.search_tab));
		adapter.addFragment(new FavoriteFragment(), getString(R.string.favorite_tab));

		viewPager.setAdapter(adapter);
	}


}
