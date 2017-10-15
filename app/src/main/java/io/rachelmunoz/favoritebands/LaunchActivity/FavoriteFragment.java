package io.rachelmunoz.favoritebands.LaunchActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rachelmunoz.favoritebands.Artist;
import io.rachelmunoz.favoritebands.ArtistLab;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.RecyclerAdapter;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class FavoriteFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private RecyclerAdapter mAdapter;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorites, container, false);

		mRecyclerView = v.findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUI();
		return v;
	}


	public void updateUI(){
		ArtistLab artistLab = ArtistLab.get(getActivity());
		List<Artist> artists = artistLab.getArtists();

		if (mAdapter == null){
			mAdapter = new RecyclerAdapter(artists);
			mRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.setArtists(artists); // updates crimes incase it is different
			mAdapter.notifyDataSetChanged();
		}
	}
}
