package io.rachelmunoz.favoritebands.ActivityMain;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.rachelmunoz.favoritebands.Api.ApiInterface;
import io.rachelmunoz.favoritebands.Api.ArtistClient;
import io.rachelmunoz.favoritebands.Api.RequestResponse;
import io.rachelmunoz.favoritebands.Api.SearchClient;
import io.rachelmunoz.favoritebands.FragmentArtistList.RecyclerAdapter;
import io.rachelmunoz.favoritebands.ModelLayer.Artist;
import io.rachelmunoz.favoritebands.R;
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
	public static final String KEY_QUERY = "currentQuery";

	private RecyclerView mRecyclerView;
	private RecyclerAdapter mRecyclerAdapter;

	private List<Artist> mArtists = new ArrayList<>();

	private String mCurrentQuery;
	private ApiInterface mApiArtistInterface;
	private ApiInterface mApiSearchInterface;


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_QUERY, mCurrentQuery);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		if (savedInstanceState != null){
			mCurrentQuery = savedInstanceState.getString(KEY_QUERY);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		// not in use at the moment because of bug with ViewPager swiping
//		searchArtist(mCurrentQuery);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search, container, false);

		SearchView searchView = (SearchView) v.findViewById(R.id.search_view);

		mRecyclerView = v.findViewById(R.id.artist_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

		mApiSearchInterface = SearchClient.getApiClient().create(ApiInterface.class);
		mApiArtistInterface = ArtistClient.getApiClient().create(ApiInterface.class);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
			@Override
			public boolean onQueryTextSubmit(String query) {
				mCurrentQuery = query;
				refreshFragment();
				searchArtist(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});

		updateUI();
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
			// use diff util?
			mArtists.clear();
			mRecyclerAdapter.setArtists(mArtists);
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}

	private void setupAdapter() {
		mRecyclerAdapter = new RecyclerAdapter(mArtists);
		mRecyclerAdapter.setOnHeartClickedListener(new RecyclerAdapter.Callback() {
			@Override
			public void onHeartClick(int position, List<Artist> artists) {
				mRecyclerAdapter.notifyItemChanged(position);
			}
		});
		mRecyclerView.setAdapter(mRecyclerAdapter);
	}
}



