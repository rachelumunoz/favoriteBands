package io.rachelmunoz.favoritebands;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistLab {
	private static ArtistLab mArtistLab;
	private List<Artist> mArtists;

	public static ArtistLab get(Context context){
		if (mArtistLab == null){
			mArtistLab = new ArtistLab(context);
		}

		return mArtistLab;
	}

	private ArtistLab(Context context){
		mArtists = new ArrayList<>();

		for (int i = 0; i < 100; i++){
			Artist artist = new Artist();
			artist.setName("Artist #" + i);
			mArtists.add(artist);
		}
	}

	public List<Artist> getArtists() {
		return mArtists;
	}
}
