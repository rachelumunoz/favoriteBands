package io.rachelmunoz.favoritebands;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class FavoriteFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private ArtistAdapter mAdapter;



	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorites, container, false);

		mRecyclerView = v.findViewById(R.id.favorites_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUI();
		return v;
	}

	private class ArtistHolder extends RecyclerView.ViewHolder {
		private ImageView mArtistImage;
		private TextView mArtistName;
		private ImageView mFavoriteIcon;
		private Artist mArtist;

		public ArtistHolder(LayoutInflater inflater, ViewGroup parent){
			super(inflater.inflate(R.layout.list_item_artist, parent, false));

			mArtistImage = (ImageView) itemView.findViewById(R.id.artist_image);
			mArtistName = (TextView) itemView.findViewById(R.id.artist_name);
			mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favorite_icon);
		}

		public void bind(Artist artist) {
			mArtist = artist;
			mArtistName.setText(artist.getName());
		}
	}

	private class ArtistAdapter extends RecyclerView.Adapter<ArtistHolder> {
		private List<Artist> mArtists;

		public ArtistAdapter(List<Artist> artists) {
			mArtists = artists;
		}

		@Override
		public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			return new ArtistHolder(layoutInflater, parent);
		}

		@Override
		public void onBindViewHolder(ArtistHolder holder, int position) {
			Artist artist = mArtists.get(position);
			holder.bind(artist);
		}

		@Override
		public int getItemCount() {
			return mArtists.size();
		}

		public void setArtists(List<Artist> artists) {
			mArtists = artists;
		}
	}

	public void updateUI(){
		List<Artist> artists = new ArrayList<>();
		artists.add(new Artist());
		artists.add(new Artist());
		artists.add(new Artist());
		artists.add(new Artist());

		if (mAdapter == null){
			mAdapter = new ArtistAdapter(artists);
			mRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.setArtists(artists); // updates crimes incase it is different
			mAdapter.notifyDataSetChanged();
		}
	}
}
