package io.rachelmunoz.favoritebands;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.rachelmunoz.favoritebands.REST.ApiInterface;
import io.rachelmunoz.favoritebands.REST.ArtistClient;
import io.rachelmunoz.favoritebands.REST.RequestResponse;
import io.rachelmunoz.favoritebands.REST.SearchClient;
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
	private RecyclerView mRecyclerView;
	private RecyclerAdapter mRecyclerAdapter;

	private ApiInterface mApiSearchInterface;
	private List<Artist> mArtists = new ArrayList<>();
	private ApiInterface mApiArtistInterface;




	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search, container, false);

		final SearchView searchView = (SearchView) v.findViewById(R.id.search_view); // search View
		mRecyclerView = v.findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		updateUI();

		mApiSearchInterface = SearchClient.getApiClient().create(ApiInterface.class);
		mApiArtistInterface = ArtistClient.getApiClient().create(ApiInterface.class);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
			@Override
			public boolean onQueryTextSubmit(String query) {
//				searchArtist(query);
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

				updateUI();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.d(TAG, "QueryTextChange " + newText);
				return false;
			}
		});




		return v;
	}


	private void updateUI(){
		if (mRecyclerAdapter == null){
			mRecyclerAdapter = new RecyclerAdapter(mArtists);
			mRecyclerView.setAdapter(mRecyclerAdapter);
		} else {
			mRecyclerAdapter.setArtists(mArtists);
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}


	private void searchArtist(String query){
		mApiSearchInterface.getArtists(query)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<RequestResponse>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Log.e(TAG, e.getMessage());
					}

					@Override
					public void onNext(RequestResponse requestResponse) {
//						mArtists = requestResponse.getData();
//						getArtistDetails(mArtists.get(0).getName());
//						updateUI();
					}
				});


//		Call<RequestResponse> call = mApiSearchInterface.getArtists(query);
//		call.enqueue(new Callback<RequestResponse>() {
//			@Override
//			public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {
//				mArtists = response.body().getData();
//				// update RecyclerView with data fetched
//				updateUI();
//			}
//
//			@Override
//			public void onFailure(Call<RequestResponse> call, Throwable t) {
//				Log.e(TAG, "failure", t);
//			}
//		});
	}


	private void getArtistDetails(String newText){
		mApiArtistInterface.getArtistDetails(newText)
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

					}
				});
//		call.enqueue(new Callback<Artist>() {
//			@Override
//			public void onResponse(Call<Artist> call, Response<Artist> response) {
//				Log.d(TAG, "Success!!");
//				Artist artist = response.body();
//				//update viewholder?
//			}
//
//			@Override
//			public void onFailure(Call<Artist> call, Throwable t) {
//				Log.e(TAG, "Failure", t);
//			}
//		});
	}

	private void refreshFragment(){
		FragmentManager fm = getFragmentManager();
		Fragment newFragment = new ArtistFragment();

		fm.beginTransaction().replace(R.id.recycler_view, newFragment).commit();
	}

}



