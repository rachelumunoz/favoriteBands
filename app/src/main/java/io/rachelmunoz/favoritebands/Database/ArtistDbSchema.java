package io.rachelmunoz.favoritebands.Database;

import android.net.Uri;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistDbSchema {

	public static final class ArtistDbTable {
		public static final String NAME = "artists";
		public static final Uri URI = Uri.parse("content://user_dictionary/words");

		public static final class Cols {
			public static final String UUID = "uuid";
			public static final String BIT_ID = "bandsintownid";
			public static final String MEDIA_ID = "mediaId";
			public static final String NAME = "name";
			public static final String FAVORITED = "favorited";
			public static final String IMAGE_URL = "imageUrl";
			public static final String EVENT_COUNT = "eventCount";
			public static final String TRACKER_COUNT = "trackerCount";
		}
	}
}
