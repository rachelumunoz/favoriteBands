package io.rachelmunoz.favoritebands.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachelmunoz on 10/14/17.
 */

public class ArtistClient {

	public static final String BASE_URL = "https://rest.bandsintown.com/artists/";

	public static Retrofit sRetrofit = null;

	public static Retrofit getApiClient(){
		if (sRetrofit == null){
			sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create()).build();
		}

		return sRetrofit;
	}

}
