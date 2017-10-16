package io.rachelmunoz.favoritebands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.rachelmunoz.favoritebands.database.ArtistBaseHelper;
import io.rachelmunoz.favoritebands.database.ArtistCursorWrapper;
import io.rachelmunoz.favoritebands.database.ArtistDbSchema.ArtistDbTable;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistLab {
	private static ArtistLab mArtistLab;

	private Context mContext;
	private SQLiteDatabase mDatabase;

	public static ArtistLab get(Context context){
		if (mArtistLab == null){
			mArtistLab = new ArtistLab(context);
		}

		return mArtistLab;
	}

	private ArtistLab(Context context){
		mContext = context.getApplicationContext();
		mDatabase = new ArtistBaseHelper(mContext).getWritableDatabase();
	}

	public List<Artist> getArtists() {
		List<Artist> artists = new ArrayList<>();

		ArtistCursorWrapper cursor = queryArtists(null, null);

		try {
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				artists.add(cursor.getArtist());
				cursor.moveToNext();
			}
		} finally {
			cursor.close();
		}

		return artists;
	}

	public void addArtist(Artist artist){
		ContentValues values = getContentValues(artist);

		mDatabase.insert(ArtistDbTable.NAME, null, values);
	}

	public void updateArtist(Artist artist){
		String uuidString = artist.getUuid().toString();
		ContentValues values = getContentValues(artist);

		mDatabase.update(ArtistDbTable.NAME, values,
				ArtistDbTable.Cols.UUID + " = ?",
				new String[]{uuidString});
	}

	public Artist getArtist(UUID uuid){

		ArtistCursorWrapper cursor = queryArtists(
				ArtistDbTable.Cols.UUID + " = ?",
				new String[] { uuid.toString() }
		);

		try {
			if (cursor.getCount() == 0){
				return null;
			}
			cursor.moveToFirst();
			return cursor.getArtist();
		} finally {
			cursor.close();
		}
	}
//
//	whereClause = ImageThoughtTable.Cols.COMPLETE + " = ?";
//	whereArgs =  new String[]{String.valueOf(1)};

	private ArtistCursorWrapper queryArtists(String whereClause, String[] whereArgs){
		Cursor cursor = mDatabase.query(
				ArtistDbTable.NAME,
				null,
				ArtistDbTable.Cols.FAVORITED + " = ?",
				new String[]{String.valueOf(1)},
				null,
				null,
				null

		);

		return new ArtistCursorWrapper(cursor);
	}

	private static ContentValues getContentValues(Artist artist){
		ContentValues values = new ContentValues();
		values.put(ArtistDbTable.Cols.UUID, artist.getUuid().toString());
		values.put(ArtistDbTable.Cols.NAME, artist.getName());
		values.put(ArtistDbTable.Cols.MEDIA_ID, artist.getMediaId());
		values.put(ArtistDbTable.Cols.FAVORITED, artist.isFavorited() ? Integer.toString(1) : Integer.toString(0));
		values.put(ArtistDbTable.Cols.IMAGE_URL, artist.getImageUrl());
		values.put(ArtistDbTable.Cols.EVENT_COUNT, Integer.toString(artist.getEventCount()));
		values.put(ArtistDbTable.Cols.TRACKER_COUNT, Integer.toString(artist.getTrackerCount()));

		return values;
	}
}
