package io.rachelmunoz.favoritebands;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.rachelmunoz.favoritebands.ArtistDetail.ArtistActivity;
import io.rachelmunoz.favoritebands.REST.ApiInterface;
import io.rachelmunoz.favoritebands.REST.ArtistClient;




/**
 * Created by rachelmunoz on 10/13/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArtistHolder> {
	private final String TAG = "RecyclerAdapter";

	private List<Artist> mArtists;

	public RecyclerAdapter(List<Artist> artists) {
		mArtists = artists;
	}

	@Override
	public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

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

	public class ArtistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private ImageView mArtistImage;
		private TextView mArtistName;
		private ImageView mFavoriteIcon;
		private Artist mArtist;

		public ArtistHolder(LayoutInflater inflater, ViewGroup parent){
			super(inflater.inflate(R.layout.list_item_artist, parent, false));
			itemView.setOnClickListener(this);

			mArtistImage = (ImageView) itemView.findViewById(R.id.artist_image);
			mArtistName = (TextView) itemView.findViewById(R.id.artist_name);
			mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favorite_icon);

		}

		public void bind(Artist artist) {
			mArtist = artist;
			mArtistName.setText(mArtist.getName());
		}

		@Override
		public void onClick(View view) {
			Context context = view.getContext();
			Intent intent = new Intent(context, ArtistActivity.class);
			context.startActivity(intent);
		}
	}
}


