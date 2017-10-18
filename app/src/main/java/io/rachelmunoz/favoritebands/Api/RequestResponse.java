package io.rachelmunoz.favoritebands.Api;

/**
 * Created by rachelmunoz on 10/13/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.rachelmunoz.favoritebands.ModelLayer.Artist;


public class RequestResponse {

	// should add a check for status ok
	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("data")
	@Expose
	private List<Artist> data = null;

	public List<Artist> getData() {
		return data;
	}

	public void setData(List<Artist> data) {
		this.data = data;
	}
}