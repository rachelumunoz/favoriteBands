package io.rachelmunoz.favoritebands.FragmentArtistList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.ActivityArtist.ArtistActivity;
import io.rachelmunoz.favoritebands.ModelLayer.ArtistLab;
import io.rachelmunoz.favoritebands.R;

import static io.rachelmunoz.favoritebands.FragmentArtistList.RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_FAVORITED;
import static io.rachelmunoz.favoritebands.FragmentArtistList.RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_NAME;


/**
 * Created by rachelmunoz on 10/13/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArtistHolder> {

	private final String TAG = "RecyclerAdapter";
	private Callback mCallback;

	private List<Artist> mArtists;

	public  interface Callback {
		void onHeartClick(int position, List<Artist> artists);
	}

	public void setOnHeartClickedListener(Callback listener) {
		mCallback = listener;
	}

	public RecyclerAdapter(List<Artist> artists) {
		super();
		mArtists = artists;
	}

	public void setArtists(List<Artist> artists) {
		mArtists = artists;
	}

	@Override
	public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		return new ArtistHolder(layoutInflater, parent);
	}

	@Override
	public void onBindViewHolder(final ArtistHolder holder, final int position) {

		final Artist artist = mArtists.get(position);

		holder.bindArtist(artist, holder.itemView);

		// click listener on entire row
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				artistDetailIntent(view, artist);
			}
		});

		// click listener on favorite icon
		holder.mFavoriteIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			if (mCallback != null){
				toggleArtistFavorited(artist, position, view);
				mCallback.onHeartClick(position, mArtists);
			}
			}
		});

	}

	@Override
	public int getItemCount() {
		return mArtists.size();
	}

	private void artistDetailIntent(View view, Artist artist) {
		Context context = view.getContext();
		Intent intent = new Intent(context, ArtistActivity.class);

		intent.putExtra(EXTRA_ARTIST_NAME, artist.getName());
		intent.putExtra(EXTRA_ARTIST_FAVORITED, artist.isFavorited());

		context.startActivity(intent);
	}

	protected void toggleArtistFavorited(Artist artist, int position, View view) {
		boolean favorited = artist.isFavorited();
		artist.setFavorited(!favorited);

		toggleFavoritedInDB(view, artist);

		mArtists.set(position, artist);
	}

	private void toggleFavoritedInDB(View view, Artist artist) {
		if (artist.getUuid() == null ){ // our first interaction with this Artist
			artist.addUUID();
			ArtistLab.get(view.getContext()).addArtist(artist);
		} else {
			ArtistLab.get(view.getContext()).updateArtist(artist);
		}
	}

	public class ArtistHolder extends RecyclerView.ViewHolder  {
		public static final String EXTRA_ARTIST_NAME = "artist_name";
		public static final String EXTRA_ARTIST_FAVORITED = "artist_favorited";

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

		public void bindArtist(Artist artist, View v) {
			mArtist = artist;
			mArtistName.setText(mArtist.getName());

			Glide.with(v)
				.load(artist.getImageUrl())
				.apply(RequestOptions
						.circleCropTransform()
						.placeholder(new ColorDrawable(v.getResources().getColor(R.color.colorGrey)))
						)
				.into(mArtistImage);

			setFavoritedIcon(artist, v);
		}

		private void setFavoritedIcon(Artist artist, View v) {
			if (artist.isFavorited()){
				Glide.with(v)
						.load(v.getResources().getDrawable(R.drawable.favorite_true)) // drawable loading from the placeholder
						.apply(new RequestOptions().placeholder(v.getResources().getDrawable(R.drawable.favorite_true)))
						.into(mFavoriteIcon);
			} else {
				mFavoriteIcon.setImageDrawable(v.getResources().getDrawable(R.drawable.favorite_false));
			}
		}
	}




}









