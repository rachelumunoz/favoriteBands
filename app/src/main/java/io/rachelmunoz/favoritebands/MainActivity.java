package io.rachelmunoz.favoritebands;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ViewPager) findViewById(R.id.container);
		setupViewPager(mViewPager);

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
	}



	private void setupViewPager(ViewPager viewPager){
		SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
		adapter.addFragment(new SearchFragment(), getString(R.string.search_tab));
		adapter.addFragment(new FavoriteFragment(), getString(R.string.favorite_tab));

		viewPager.setAdapter(adapter);
	}
}
