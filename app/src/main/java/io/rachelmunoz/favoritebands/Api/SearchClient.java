package io.rachelmunoz.favoritebands.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class SearchClient {

	public static final String SEARCH_URL = "https://news.bandsintown.com/";

	public static Retrofit sRetrofit = null;

	public static Retrofit getApiClient(){
		if (sRetrofit == null){
			sRetrofit = new Retrofit.Builder().baseUrl(SEARCH_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create()).build();
		}

		return sRetrofit;
	}

}
