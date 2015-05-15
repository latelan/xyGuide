package com.late.xyindoor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 信息采集Fragment的界面
 * 
 * 
 * @author abel
 */
public class BuildFragment extends Fragment {
	
	private View buildLayout;
	private ImageView indoorImage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	    
	}
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (buildLayout == null) {
			buildLayout = inflater.inflate(R.layout.fragment_build, null);
		    indoorImage = (ImageView) buildLayout.findViewById(R.id.indoor_build_map);
		    indoorImage.setImageResource(R.drawable.indoor_dongqu);
		    
		}else {
		      if (buildLayout.getParent() != null) {
		        ((ViewGroup) buildLayout.getParent()).removeView(buildLayout);
		      }
		    }
		return buildLayout;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
