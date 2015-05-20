package com.late.xyindoor;

import com.late.xyindoor.indoormap.IndoorMapView;
import com.late.xyindoor.indoormap.MarkerView;
import com.late.xyindoor.util.HttpRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 信息采集Fragment的界面
 * 
 * 
 * @author abel
 */
public class BuildFragment extends Fragment implements OnTouchListener {

	private View buildLayout;
	// private ImageView indoorImage;
	private IndoorMapView indoorImage;
	private MarkerView markerView;
	private Button btnStartSniffe;

	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (buildLayout == null) {
			buildLayout = inflater.inflate(R.layout.fragment_build, null);
			indoorImage = (IndoorMapView) buildLayout
					.findViewById(R.id.indoor_build_map);
			markerView = (MarkerView) buildLayout.findViewById(R.id.marker);
			btnStartSniffe = (Button) buildLayout
					.findViewById(R.id.start_sniffe);
			btnStartSniffe.setOnClickListener(btnStartSniffeClickListener);

			indoorImage.setImageResource(R.drawable.indoor_dongqu);
			indoorImage.addMarker(markerView);

			text = (TextView) buildLayout.findViewById(R.id.location);
			text.setText(String.valueOf(indoorImage.getX())
					+ indoorImage.getY());
		} else {
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

	private OnClickListener btnStartSniffeClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// final TextView text = (TextView) buildLayout
			// .findViewById(R.id.location);
			text.setText(String.valueOf(markerView.getInMapX()) + ", "
					+ String.valueOf(markerView.getInMapY()) + "  image:");

			text.setText(String.valueOf(indoorImage.getWidth()) + ", "
					+ indoorImage.getHeight() + ", xy: "
					+ markerView.getInMapX() + ", " + markerView.getInMapY()
					+ ", touch: " + markerView.getX() + ", "
					+ markerView.getY());

			

			
			// text.setText(String.valueOf(markerView) + ", "
			// + String.valueOf(markerView.getInMapY()) + "  image:"
			// + indoorImage.getImageStartX() + ",  "
			// + indoorImage.getImageStartY());

			// 进入采集信息页面

			Intent intent = new Intent(getActivity(), SniffeActivity.class);
			intent.putExtra("buildingId", "building_east_1");
			intent.putExtra("floor", "F1");
			intent.putExtra("x", markerView.getInMapX());
			intent.putExtra("y", markerView.getInMapY());

			startActivity(intent);
		}
	};

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		// return false;
		// final TextView text = (TextView) buildLayout
		// .findViewById(R.id.location);

		// text.setText(String.valueOf(event.getX()) + ",  " + event.getY());

		return true;
	}
}
