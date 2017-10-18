package io.rachelmunoz.favoritebands.FragmentArtist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rachelmunoz.favoritebands.R;

/**
 * Created by rachelmunoz on 10/13/17.
 */

public class ArtistFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_artist, container, false);
	}


}
