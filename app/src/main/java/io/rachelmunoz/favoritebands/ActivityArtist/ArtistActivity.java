package io.rachelmunoz.favoritebands.ActivityArtist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.ModelLayer.ArtistLab;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.Api.ApiInterface;
import io.rachelmunoz.favoritebands.Api.ArtistClient;
import io.rachelmunoz.favoritebands.FragmentArtistList.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistActivity extends AppCompatActivity {

	private static final String TAG = "ArtistActivity";
	private String mArtistNameFromIntent;
	private ApiInterface mApiArtistInterface;

	private ImageView mArtistImageView;
	private TextView mArtistNameTextView;
	private TextView mEventCountTextView;
	private TextView mTrackerCountTextView;
	private Artist mArtist;
	private ImageView mFavoriteIcon;
	private boolean mArtistFavoritedFromIntent;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist);

		mArtistImageView = (ImageView) findViewById(R.id.artist_image);
		mArtistNameTextView = (TextView) findViewById(R.id.artist_name);
		mEventCountTextView = (TextView) findViewById(R.id.event_count);
		mTrackerCountTextView = (TextView) findViewById(R.id.tracker_count);
		mFavoriteIcon = (ImageView) findViewById(R.id.artist_favorite_icon);

		mArtistNameFromIntent = getIntent().getStringExtra(RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_NAME);
		mArtistFavoritedFromIntent = getIntent().getBooleanExtra(RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_FAVORITED, false);

		mArtist = new Artist();
		mArtist.setFavorited(mArtistFavoritedFromIntent);
		mArtist.setName(mArtistNameFromIntent);

		mApiArtistInterface = ArtistClient.getApiClient().create(ApiInterface.class);

		Call<Artist> call = mApiArtistInterface.getArtistDetailsNonRx(mArtist.getName());
		call.enqueue(new Callback<Artist>() {
			@Override
			public void onResponse(Call<Artist> call, Response<Artist> response) {


				mArtist = response.body();

				mArtist.setFavorited(mArtistFavoritedFromIntent);
				updateUI();
			}

			@Override
			public void onFailure(Call<Artist> call, Throwable t) {

			}
		});

		mFavoriteIcon.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean favorited = mArtist.isFavorited();
				mArtist.setFavorited(!favorited);

				setIcon(mArtist.isFavorited());

				toggleFavoritedInDB(v.getContext(), mArtist);
			}
		});

		updateUI();
	}


	private void setIcon(boolean isFavorited) {
		if (isFavorited) {
			Glide.with(this)
					.load(this.getResources().getDrawable(R.drawable.favorite_true)) // drawable loading from the placeholder
					.apply(new RequestOptions().placeholder(this.getResources().getDrawable(R.drawable.favorite_true)))
					.into(mFavoriteIcon);
		} else {
			mFavoriteIcon.setImageDrawable(this.getResources().getDrawable(R.drawable.favorite_false));
		}
	}

	private void toggleFavoritedInDB(Context context, Artist artist) {
		Artist retrievedArtist = ArtistLab.get(this).getArtistByBitId(artist.getBitId());
		if (retrievedArtist != null){
			retrievedArtist.setFavorited(artist.isFavorited());
			ArtistLab.get(context).updateArtist(retrievedArtist);
		} else {
			artist.addUUID();
			ArtistLab.get(context).addArtist(artist);
		}
	}

	private void updateUI() {
		mArtistNameTextView.setText(mArtist.getName());
		mTrackerCountTextView.setText(String.valueOf(mArtist.getEventCount()));
		mEventCountTextView.setText((String.valueOf(mArtist.getTrackerCount())));

		setIcon(mArtist.isFavorited());

		Glide.with(this)
				.load(mArtist.getImageUrl())
				.apply(RequestOptions.circleCropTransform())
				.into(mArtistImageView);


	}

	@Override
	protected void onPause() {
//		mArtist = null;
//		mArtistNameFromIntent = null;
		super.onPause();
	}
}
