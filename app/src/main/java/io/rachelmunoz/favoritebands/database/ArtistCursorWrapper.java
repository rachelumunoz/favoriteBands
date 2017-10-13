package io.rachelmunoz.favoritebands.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import io.rachelmunoz.favoritebands.Artist;

import static io.rachelmunoz.favoritebands.database.ArtistDbSchema.*;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistCursorWrapper extends CursorWrapper {

	public ArtistCursorWrapper(Cursor cursor) {
		super(cursor);
	}

	public Artist getArtist(){
		String uuid           = getString(getColumnIndex(ArtistDbTable.Cols.UUID));
		String name           = getString(getColumnIndex(ArtistDbTable.Cols.NAME));
		String mediaId        = getString(getColumnIndex(ArtistDbTable.Cols.MEDIA_ID));
		String imageUrl       = getString(getColumnIndex(ArtistDbTable.Cols.IMAGE_URL));
		String eventCount     = getString(getColumnIndex(ArtistDbTable.Cols.EVENT_COUNT));
		String trackerCount   = getString(getColumnIndex(ArtistDbTable.Cols.TRACKER_COUNT));
		int favorited         = getInt(getColumnIndex(ArtistDbTable.Cols.FAVORITED));

		return null;
	}
}
