package io.rachelmunoz.favoritebands.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;

import static io.rachelmunoz.favoritebands.Database.ArtistDbSchema.*;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistCursorWrapper extends CursorWrapper {

	public ArtistCursorWrapper(Cursor cursor) {
		super(cursor);
	}

	public Artist getArtist(){
		String uuid           =      getString(getColumnIndex(ArtistDbTable.Cols.UUID));
		String bitID          =      getString(getColumnIndex(ArtistDbTable.Cols.BIT_ID));
		String name           =      getString(getColumnIndex(ArtistDbTable.Cols.NAME));
		String mediaId        =      getString(getColumnIndex(ArtistDbTable.Cols.MEDIA_ID));
		String imageUrl       =      getString(getColumnIndex(ArtistDbTable.Cols.IMAGE_URL));
		int eventCount        =      getInt(getColumnIndex(ArtistDbTable.Cols.EVENT_COUNT));
		int trackerCount      =      getInt(getColumnIndex(ArtistDbTable.Cols.TRACKER_COUNT));
		int favorited         =      getInt(getColumnIndex(ArtistDbTable.Cols.FAVORITED));

		Artist artist = new Artist();
		artist.setUuid(UUID.fromString(uuid));
		artist.setBitId(bitID);
		artist.setName(name);
		artist.setMediaId(mediaId);
		artist.setImageUrl(imageUrl);
		artist.setEventCount(eventCount);
		artist.setTrackerCount(trackerCount);
		artist.setFavorited(favorited == 1);

		return artist;
	}
}
