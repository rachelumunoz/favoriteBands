package io.rachelmunoz.favoritebands.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static io.rachelmunoz.favoritebands.Database.ArtistDbSchema.*;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistBaseHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	public static final String DATABASE_NAME = "artistBase.db";

	public ArtistBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL("create table " + ArtistDbTable.NAME + "(" +
			" _id integer primary key autoincrement, " +
				ArtistDbTable.Cols.UUID + ", " +
				ArtistDbTable.Cols.BIT_ID + ", " +
				ArtistDbTable.Cols.MEDIA_ID + ", " +
				ArtistDbTable.Cols.NAME + ", " +
				ArtistDbTable.Cols.FAVORITED + ", " +
				ArtistDbTable.Cols.IMAGE_URL + ", " +
				ArtistDbTable.Cols.EVENT_COUNT + ", " +
				ArtistDbTable.Cols.TRACKER_COUNT +
				")"

		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
