package io.rachelmunoz.favoritebands.ActivityMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.ModelLayer.ArtistLab;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.FragmentArtist.RecyclerAdapter;


/**
 * Created by rachelmunoz on 10/12/17.
 */

public class FavoriteFragment extends Fragment {

	private static final String TAG = "FavoriteFragment";
	private RecyclerView mRecyclerView;
	private RecyclerAdapter mAdapter;




	@Override
	public void onResume() {
		super.onResume();
//		updateUI();
//		mAdapter.notifyDataSetChanged();
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorites, container, false);

		mRecyclerView = v.findViewById(R.id.artist_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

		updateUI();

		return v;
	}


	public void updateUI(){
		ArtistLab artistLab = ArtistLab.get(getActivity());
		List<Artist> artists = artistLab.getArtists();

		if (mAdapter == null){
			mAdapter = new RecyclerAdapter(artists);
			mAdapter.setOnHeartClickedListener(new RecyclerAdapter.Callback() {
				@Override
				public void onHeartClick(int position, Artist artist, List<Artist> artists) {
					Log.d(TAG, "HEart clicked at " + String.valueOf(position));
					// remove from the recycler view
					artists.remove(position);
					mAdapter.notifyItemRemoved(position);

				}
			});
			mRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.setArtists(artists); // updates crimes incase it is different
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}

