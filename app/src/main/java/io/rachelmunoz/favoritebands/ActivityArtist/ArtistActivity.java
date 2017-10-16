package io.rachelmunoz.favoritebands.ActivityArtist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.Api.ApiInterface;
import io.rachelmunoz.favoritebands.Api.ArtistClient;
import io.rachelmunoz.favoritebands.FragmentArtist.RecyclerAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistActivity extends AppCompatActivity {

	private static final String TAG = "ArtistActivity";
	private String mCurentArtistName;
	private ApiInterface mApiArtistInterface;

	private ImageView mArtistImageView;
	private TextView mArtistNameTextView;
	private TextView mEventCountTextView;
	private TextView mTrackerCountTextView;
	private Artist mArtist;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist);

		mArtistImageView = (ImageView) findViewById(R.id.artist_image);
		mArtistNameTextView = (TextView) findViewById(R.id.artist_name);
		mEventCountTextView = (TextView) findViewById(R.id.event_count);
		mTrackerCountTextView = (TextView) findViewById(R.id.tracker_count);

		mCurentArtistName = getIntent().getStringExtra(RecyclerAdapter.ArtistHolder.EXTRA_ARTIST_NAME);

		mApiArtistInterface = ArtistClient.getApiClient().create(ApiInterface.class);
		mApiArtistInterface
			.getArtistDetails(mCurentArtistName)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Artist>(){
					@Override
					public void onCompleted(){

					}

					@Override
					public void onError(Throwable e){

					}

					@Override
					public void onNext(Artist artist){
					 	mArtist = artist;
						updateUI();
					}
				});
	}

	private void updateUI() {
		mArtistNameTextView.setText(mArtist.getName());
		mTrackerCountTextView.setText(String.valueOf(mArtist.getEventCount()));
		mEventCountTextView.setText((String.valueOf(mArtist.getTrackerCount())));

		Glide.with(this)
			.load(mArtist.getImageUrl())
			.apply(RequestOptions.circleCropTransform())
			.into(mArtistImageView);
	}
}
