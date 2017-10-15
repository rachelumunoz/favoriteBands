package io.rachelmunoz.favoritebands.REST;



import io.rachelmunoz.favoritebands.Artist;
import io.rachelmunoz.favoritebands.REST.RequestResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public interface ApiInterface {

	@GET("/searchArtists")
	Observable<RequestResponse> getArtists(@Query("search") String artistName);


	@GET("/artists/{artist}?app_id=hello")
	Observable<Artist> getArtistDetails(@Path("artist") String artist);
}
