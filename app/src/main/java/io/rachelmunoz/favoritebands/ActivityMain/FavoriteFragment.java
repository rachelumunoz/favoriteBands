package io.rachelmunoz.favoritebands.ActivityMain;

import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rachelmunoz.favoritebands.Database.ArtistDbSchema;
import io.rachelmunoz.favoritebands.Database.ArtistDbSchema.ArtistDbTable;
import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.ModelLayer.ArtistLab;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.FragmentArtistList.RecyclerAdapter;


/**
 * Created by rachelmunoz on 10/12/17.
 */

public class FavoriteFragment extends Fragment{

	private static final String TAG = "FavoriteFragment";

	private RecyclerView mRecyclerView;
	private RecyclerAdapter mAdapter;
	private ContentObserver mContentObserver;

	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorites, container, false);

		mRecyclerView = v.findViewById(R.id.artist_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

		mContentObserver = new ContentObserver(new Handler()) {
			@Override
			public void onChange(boolean selfChange) {
				super.onChange(selfChange);
				updateUI();
			}
		};

		getActivity().getContentResolver().registerContentObserver(ArtistDbTable.URI, false, mContentObserver);

		updateUI();
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		getActivity().getContentResolver().unregisterContentObserver(mContentObserver);
	}

	private void updateUI(){
		ArtistLab artistLab = ArtistLab.get(getActivity());
		List<Artist> artists = artistLab.getFavoritedArtists();

		if (mAdapter == null){
			setupAdapter(artists);
			mRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.setArtists(artists);
			mAdapter.notifyDataSetChanged();
		}
	}

	private void setupAdapter(List<Artist> artists) {
		mAdapter = new RecyclerAdapter(artists);
		mAdapter.setOnHeartClickedListener(new RecyclerAdapter.Callback() {
			@Override
			public void onHeartClick(int position, List<Artist> artists) {
				artists.remove(position);
				mAdapter.notifyItemRemoved(position);

			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}

