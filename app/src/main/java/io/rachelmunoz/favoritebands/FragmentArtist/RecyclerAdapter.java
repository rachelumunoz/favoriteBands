package io.rachelmunoz.favoritebands.FragmentArtist;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.ActivityArtist.ArtistActivity;
import io.rachelmunoz.favoritebands.ModelLayer.ArtistLab;
import io.rachelmunoz.favoritebands.R;

import static io.rachelmunoz.favoritebands.FragmentArtist.RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_NAME;


/**
 * Created by rachelmunoz on 10/13/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArtistHolder> {
	private final String TAG = "RecyclerAdapter";

	private List<Artist> mArtists;
	private RecyclerAdapter mRecyclerAdapter;

	public RecyclerAdapter(List<Artist> artists) {
		mArtists = artists;
		mRecyclerAdapter = this;
	}

	@Override
	public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		return new ArtistHolder(layoutInflater, parent);
	}

	@Override
	public void onBindViewHolder(final ArtistHolder holder, final int position) {

		final Artist artist = mArtists.get(position);

		if (artist == null) return;

		holder.bind(artist, holder.itemView);

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
				toggleArtistFavorited(artist, position);
				toggleFavoritedInDB(view, artist);
//				Toast.makeText(view.getContext(), artist.getName()+ " is favorited: " + artist.isFavorited(), Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void artistDetailIntent(View view, Artist artist) {
		Context context = view.getContext();
		Intent intent = new Intent(context, ArtistActivity.class);
		intent.putExtra(EXTRA_ARTIST_NAME, artist.getName());
		context.startActivity(intent);
	}

	private void toggleArtistFavorited(Artist artist, int position) {
		boolean favorited = artist.isFavorited();
		artist.setFavorited(!favorited);
		mRecyclerAdapter.notifyItemChanged(position);
		mArtists.set(position, artist);
	}
	// interface Callbacks
		// in searchfragment - remove from list

	private void toggleFavoritedInDB(View view, Artist artist) {
		if( artist.isFavorited() == true && artist.getUuid() == null){
			// add UUID to Pojo for out DB
			artist.addUUID();
			// add to DB
			ArtistLab.get(view.getContext().getApplicationContext()).addArtist(artist);
		} else {
			// already in DB, toggle favorited field
			ArtistLab.get(view.getContext().getApplicationContext()).updateArtist(artist);
		}
	}

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

		public void bind(Artist artist, View v) {
			mArtist = artist;
			mArtistName.setText(mArtist.getName() + artist.getEventCount());

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









