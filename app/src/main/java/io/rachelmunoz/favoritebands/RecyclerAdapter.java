package io.rachelmunoz.favoritebands;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.rachelmunoz.favoritebands.ArtistDetail.ArtistActivity;
import io.rachelmunoz.favoritebands.REST.ApiInterface;
import io.rachelmunoz.favoritebands.REST.ArtistClient;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


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
		ArtistHolder holder = new ArtistHolder(layoutInflater, parent);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(final ArtistHolder holder, final int position) {

		final Artist artist = mArtists.get(position);

		if (artist == null) return;
		holder.bind(artist);


//		if (artist.isFavorited()){
//			Drawable icon = ContextCompat.getDrawable(holder.mFavoriteIcon.getContext(), R.drawable.favorite_false);
//			icon.setColorFilter(new
//					PorterDuffColorFilter(holder.mFavoriteIcon.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
//
////			holder.mFavoriteIcon.setImageDrawable(icon);
//		}
//
//
		holder.mFavoriteIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Artist artist = mArtists.get(position);
				boolean favorited = artist.isFavorited();
				artist.setFavorited(!favorited);
				boolean result = artist.isFavorited();

				Toast.makeText(view.getContext(), artist.getName()+ " is favorited: " + result, Toast.LENGTH_SHORT).show();


				if( artist.isFavorited() == true){
					artist.addUUID();
					ArtistLab.get(view.getContext().getApplicationContext()).addArtist(artist);
				} else {
					ArtistLab.get(view.getContext().getApplicationContext()).updateArtist(artist);
				}




//				Drawable icon = ContextCompat.getDrawable(holder.mFavoriteIcon.getContext(), R.drawable.favorite_false);
//				icon.setColorFilter(new
//						PorterDuffColorFilter(holder.mFavoriteIcon.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
//
//				holder.mFavoriteIcon.setImageDrawable(icon);
			}
		});

	}

//	public void changeImage(int position) {
//		mArtists.get(position).setFavorited(!mArtists.get(position).isFavorited());
//
//
//	}

	@Override
	public int getItemCount() {
		return mArtists.size();
	}

	public void setArtists(List<Artist> artists) {
		mArtists = artists;
	}

	public class ArtistHolder extends RecyclerView.ViewHolder  {
		public static final String EXTRA_ARTIST_NAME = "artist_name";
		private ImageView mArtistImage;
		private TextView mArtistName;
		private ImageView mFavoriteIcon;
		private Artist mArtist;

		public ArtistHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_artist, parent, false));

			mArtistImage = (ImageView) itemView.findViewById(R.id.artist_image);
			mArtistName = (TextView) itemView.findViewById(R.id.artist_name);

			mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favorite_icon);
		}

		public void bind(Artist artist) {
			mArtist = artist;
			mArtistName.setText(mArtist.getName());
		}


	}
}










//	private void updateFavorite(View view, int pos){
//
//		ImageView favoriteIcon = (ImageView) view;
//
//		if (view.getVerticalScrollbarPosition() == pos){

//			Drawable icon = ContextCompat.getDrawable(view.getContext(), R.drawable.favorite_false);
//			icon.setColorFilter(new
//					PorterDuffColorFilter(view.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
//
//			favoriteIcon.setImageDrawable(icon);
//		}
//
//	}



//		@Override
//		public void onClick(View view) {
//			if (view.getId() == mFavoriteIcon.getId()){
//				Drawable icon = ContextCompat.getDrawable(view.getContext(), R.drawable.favorite_false);
//				icon.setColorFilter(new
//						PorterDuffColorFilter(view.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
//
//				// replace current ArtistHolder's icon with the new colored one
//				mFavoriteIcon.setImageDrawable(icon);
//			} else {
//				Toast.makeText(view.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//			}
//		}




//		.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View view) {
//				// new colored icon
//				Drawable icon = ContextCompat.getDrawable(view.getContext(), R.drawable.favorite_false);
//				icon.setColorFilter(new
//						PorterDuffColorFilter(view.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
//
//				// replace current ArtistHolder's icon with the new colored one
//				mFavoriteIcon.setImageDrawable(icon);
//
//			}
//		});

//		@Override
//		public void onClick(View view) {
////			if (view == mFavoriteIcon){
////				Drawable icon = ContextCompat.getDrawable(view.getContext(), R.drawable.favorite_false);
////				icon.setColorFilter(new
////					PorterDuffColorFilter(view.getContext().getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP));
////
////				mFavoriteIcon.setImageDrawable(icon);
////			} else {
//				Context context = view.getContext();
//				Intent intent = new Intent(context, ArtistActivity.class);
//				intent.putExtra(EXTRA_ARTIST_NAME, mArtist.getName());
//				context.startActivity(intent);
////			}

