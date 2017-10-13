package io.rachelmunoz.favoritebands;

import java.util.UUID;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class Artist {
	private UUID mUuid;
	private String mMediaId;
	private String mName;
	private String mUrl;
	private String mTrackerCount;
	private String mEventCount;
	private boolean mFavorited;

	public Artist(){
		mUuid = UUID.randomUUID();
		mName = "Test Artist";
		mUrl = "https://vignette.wikia.nocookie.net/acourtofthornsandroses/images/a/a5/Cute_kitty.jpg/revision/latest/scale-to-width-down/540?cb=20170220162435";
	}

	public UUID getUuid() {
		return mUuid;
	}

	public String getMediaId() {
		return mMediaId;
	}

	public void setMediaId(String mediaId) {
		mMediaId = mediaId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

	public String getTrackerCount() {
		return mTrackerCount;
	}

	public void setTrackerCount(String trackerCount) {
		mTrackerCount = trackerCount;
	}

	public String getEventCount() {
		return mEventCount;
	}

	public void setEventCount(String eventCount) {
		mEventCount = eventCount;
	}

	public boolean isFavorited(){
		return mFavorited;
	}

	public void setFavorited(boolean favorited) {
		mFavorited = favorited;
	}
}
