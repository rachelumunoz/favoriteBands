package io.rachelmunoz.favoritebands.REST;

/**
 * Created by rachelmunoz on 10/13/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.rachelmunoz.favoritebands.Artist;


public class RequestResponse {

	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("data")
	@Expose
	private List<Artist> data = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Artist> getData() {
		return data;
	}

	public void setData(List<Artist> data) {
		this.data = data;
	}
}