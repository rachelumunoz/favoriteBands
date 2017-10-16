package io.rachelmunoz.favoritebands.Api;



import io.rachelmunoz.favoritebands.ModelLayer.Artist;
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

//	@GET("/thumb/{mediaId}.jpeg")
//	Observable<PhotoResponse> getImage(@Path("mediaId") String mediaId);
}
