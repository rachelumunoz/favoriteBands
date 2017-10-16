package io.rachelmunoz.favoritebands.LaunchActivity;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.rachelmunoz.favoritebands.Artist;
import io.rachelmunoz.favoritebands.ArtistFragment;
import io.rachelmunoz.favoritebands.R;
import io.rachelmunoz.favoritebands.REST.ApiInterface;
import io.rachelmunoz.favoritebands.REST.ArtistClient;
import io.rachelmunoz.favoritebands.REST.RequestResponse;
import io.rachelmunoz.favoritebands.REST.SearchClient;
import io.rachelmunoz.favoritebands.RecyclerAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by rachelmunoz on 10/12/17.
 */

public class SearchFragment extends Fragment {
	private static final String TAG = "SearchFragment";
	private static final String CURRENT_QUERY = "currentQuery";
	private RecyclerView mRecyclerView;
	private RecyclerAdapter mRecyclerAdapter;

	private ApiInterface mApiSearchInterface;
	private List<Artist> mArtists = new ArrayList<>();
	private ApiInterface mApiArtistInterface;
	private String mCurrentQuery;


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(CURRENT_QUERY, mCurrentQuery);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		if (savedInstanceState != null){
			mCurrentQuery = savedInstanceState.getString(CURRENT_QUERY);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		searchArtist(mCurrentQuery);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search, container, false);

		final SearchView searchView = (SearchView) v.findViewById(R.id.search_view); // search View
		mRecyclerView = v.findViewById(R.id.artist_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		updateUI();

		mApiSearchInterface = SearchClient.getApiClient().create(ApiInterface.class);
		mApiArtistInterface = ArtistClient.getApiClient().create(ApiInterface.class);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
			@Override
			public boolean onQueryTextSubmit(String query) {
				mCurrentQuery = query;
				refreshFragment();
				searchArtist(query);
				updateUI();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
		return v;
	}

	private void searchArtist(String query) {
		mApiSearchInterface.getArtists(query)
				.flatMap(new Func1<RequestResponse, Observable<Artist>>() {
					@Override
					public Observable<Artist> call(RequestResponse requestResponse) {
						return Observable.from(requestResponse.getData());
					}
				})
				.flatMap(new Func1<Artist, Observable<Artist>>() {
					@Override
					public Observable<Artist> call(Artist artist) {
						return mApiArtistInterface.getArtistDetails(artist.getName());
					}
				})
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Artist>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(Artist artist) {
						mArtists.add(artist);
						updateUI();
					}
				});
	}

	private void updateUI(){
		if (mRecyclerAdapter == null){
			setupAdapter();
		} else {
			mRecyclerAdapter.setArtists(mArtists);
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}

	private void refreshFragment(){
		if (mRecyclerAdapter == null){
			setupAdapter();
		} else {
			mArtists = new ArrayList<>(); // need to clear mArtists
			mRecyclerAdapter.setArtists(mArtists);
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}

	private void setupAdapter() {
		mRecyclerAdapter = new RecyclerAdapter(mArtists);
		mRecyclerView.setAdapter(mRecyclerAdapter);
	}



//	@Override
//	public void onItemClick(View view, int position) {
//		mArtists.get(position).setFavorited(!mArtists.get(position).isFavorited());
//
//		mRecyclerAdapter.notifyItemChanged(position);
//
//		Drawable icon = ContextCompat.getDrawable(view.getContext(), R.drawable.favorite_false);
//		icon.setColorFilter(new
//				PorterDuffColorFilter(view.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));
//
//
//	}
}



