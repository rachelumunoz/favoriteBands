package io.rachelmunoz.favoritebands.ModelLayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class Artist {
	private UUID mUuid;

	@SerializedName("name")
	@Expose
	private String mName;

	@SerializedName("id")
	@Expose
	private String mBITid;

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

	private boolean mFavorited;

//	public Artist(){
//		mUuid = UUID.randomUUID();
//		mName = "Test Artist";
//		mImageUrl = "https://vignette.wikia.nocookie.net/acourtofthornsandroses/images/a/a5/Cute_kitty.jpg/revision/latest/scale-to-width-down/540?cb=20170220162435";
//	}

//	public Artist(String mediaId, String name){
//		mMediaId = mediaId;
//		mName = name;
//	}

	public Artist(){}

//	public Artist(UUID uuid){
//		mUuid = uuid;
//		mName = " TEsting";
//		mFavorited = true;
//		mImageUrl = "https://vignette.wikia.nocookie.net/acourtofthornsandroses/images/a/a5/Cute_kitty.jpg/revision/latest/scale-to-width-down/540?cb=20170220162435";
//	}


	public String getBITid() {
		return mBITid;
	}

	public void setBITid(String BITid) {
		mBITid = BITid;
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
