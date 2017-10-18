package io.rachelmunoz.favoritebands.ModelLayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class Artist {
	@SerializedName("name")
	@Expose
	private String mName;

	@SerializedName("id")
	@Expose
	private String mBitId;

	@SerializedName("media_id")
	@Expose
	private String mMediaId;

	@SerializedName("image_url")
	@Expose
	private String mImageUrl;

	@SerializedName("tracker_count")
	@Expose
	private int mTrackerCount;

	@SerializedName("upcoming_event_count")
	@Expose
	private int mEventCount;

	private boolean mFavorited = false;
	private UUID mUuid;


	public Artist(){}

	public String getBitId() {
		return mBitId;
	}

	public void setBitId(String bitId) {
		mBitId = bitId;
	}

	public void setUuid(UUID uuid) {
		mUuid = uuid;
	}

	public void addUUID(){
		mUuid = UUID.randomUUID();
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

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		mImageUrl = imageUrl;
	}

	public int getTrackerCount() {
		return mTrackerCount;
	}

	public void setTrackerCount(int trackerCount) {
		mTrackerCount = trackerCount;
	}

	public int getEventCount() {
		return mEventCount;
	}

	public void setEventCount(int eventCount) {
		mEventCount = eventCount;
	}

	public boolean isFavorited(){
		return mFavorited;
	}

	public void setFavorited(boolean favorited) {
		mFavorited = favorited;
	}
}
