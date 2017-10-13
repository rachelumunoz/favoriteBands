package io.rachelmunoz.favoritebands;

/**
 * Created by rachelmunoz on 10/12/17.
 */

public class Artist {
	private String mName;
	private String mUrl;
	private String mTrackerCount;
	private String mEventCount;

	public Artist(){
		mName = "Test Artist";
		mUrl = "https://vignette.wikia.nocookie.net/acourtofthornsandroses/images/a/a5/Cute_kitty.jpg/revision/latest/scale-to-width-down/540?cb=20170220162435";
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
}
