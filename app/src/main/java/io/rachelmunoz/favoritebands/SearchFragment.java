package io.rachelmunoz.favoritebands;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


/**
 * Created by rachelmunoz on 10/12/17.
 */

public class SearchFragment extends Fragment {
	private static final String TAG = "SearchFragment";
	private RecyclerView mRecyclerView;
	private RecyclerAdapter mRecyclerAdapter;

	private ApiInterface mApiInterface;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search, container, false);

		final SearchView searchView = (SearchView) v.findViewById(R.id.search_view); // search View
		mRecyclerView = v.findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.d(TAG, "QueryTextChange " + newText);
				return false;
			}
		});



		updateUI();
		return v;
	}


	private void updateUI(){
		List<Artist> artists = new ArrayList<>();
		artists.add(new Artist());
		artists.add(new Artist());
		artists.add(new Artist());


		if (mRecyclerAdapter == null){
			mRecyclerAdapter = new RecyclerAdapter(artists);
			mRecyclerView.setAdapter(mRecyclerAdapter);
		} else {
			mRecyclerAdapter.setArtists(artists); // updates incase it is different
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}
}



