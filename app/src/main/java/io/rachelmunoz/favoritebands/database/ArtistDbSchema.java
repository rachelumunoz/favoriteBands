package io.rachelmunoz.favoritebands.database;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistDbSchema {

	public static final class ArtistDbTable {
		public static final String NAME = "artists";

		public static final class Cols {
			public static final String UUID = "uuid";
			public static final String NAME = "name";
			public static final String MEDIA_ID = "mediaId";
			public static final String SOLVED = "solved";
			public static final String URL = "url";
			public static final String EVENT_COUNT = "eventCount";
			public static final String TRACKER_COUNT = "trackerCount";
		}
	}
}
