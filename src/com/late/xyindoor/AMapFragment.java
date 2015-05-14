package com.late.xyindoor;


import java.util.ArrayList;
import java.util.List;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMapTouchListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapFragment;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.GroundOverlay;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.late.xyindoor.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * xy校园地图Fragment的界面
 * 
 * @author abel
 */
public class AMapFragment extends Fragment implements OnMapLoadedListener,
			OnMarkerClickListener,OnInfoWindowClickListener{
	
	private MapView mapView;
	private AMap aMap;
	private View mapLayout;
	private GroundOverlay groundoverlay;
	private List<Marker> markerList = new ArrayList<Marker>();
	private Marker marker_dongqu;
	private MarkerOptions markerOption;
	
	// 学校范围
	private LatLng latlng_leftup = new LatLng(34.157711, 108.898178);
	private LatLng latlng_leftdown = new LatLng(34.148642, 108.897829);
	private LatLng latlng_rightup = new LatLng(34.157321, 108.910055);
	private LatLng latlng_rightdown = new LatLng(34.148664, 108.908853);
	private LatLng latlng_center = new LatLng(108.903703,34.153521);
	
	private LatLng latlng_depart_1 = new LatLng(34.154897, 108.900495);   // 1号楼
	private LatLng latlng_depart_2 = new LatLng(34.15424, 108.900077);   // 2号楼
	private LatLng latlng_depart_3 = new LatLng(34.153641, 108.89975);    // 3好楼
	private LatLng latlng_depart_library = new LatLng(34.153414, 108.901144); // 图书馆
	private LatLng latlng_depart_meiguang = new LatLng(34.15206, 108.898505); // 美食广场
	private LatLng latlng_depart_xuriyuan = new LatLng(34.150147, 108.900227);// 旭日苑 
	private LatLng latlng_depart_dongqu = new LatLng(34.155936, 108.907072);  // 东区教学楼
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mapLayout == null) {
		      mapLayout = inflater.inflate(R.layout.fraqment_map, null);
		      mapView = (MapView) mapLayout.findViewById(R.id.map);
		      mapView.onCreate(savedInstanceState);
		      if (aMap == null) {
		        aMap = mapView.getMap();
		        
		        addMarkersToMap();
		        initMapListener();
		      }
		    }else {
		      if (mapLayout.getParent() != null) {
		        ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
		      }
		    }
		return mapLayout;
	}
	
	/**
	 * 设置监听器
	 */
	private void initMapListener() {
		aMap.setOnMapLoadedListener(this);
		aMap.setOnMarkerClickListener(this);
		aMap.setOnInfoWindowClickListener(this);
	}
	
	/**
	 * 设置Markers
	 */
	public void addMarkersToMap() {
		markerOption = new MarkerOptions();
		markerOption.draggable(false);
		markerOption.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		markerOption.anchor(0.5f, 0.5f);
		
		markerOption.position(latlng_depart_1);
		markerOption.title("一号教学楼");
		markerList.add(aMap.addMarker(markerOption));
		
		markerOption.position(latlng_depart_2);
		markerOption.title("二号教学楼");
		markerList.add(aMap.addMarker(markerOption));
		
		markerOption.position(latlng_depart_3);
		markerOption.title("三号教学楼");
		markerList.add(aMap.addMarker(markerOption));
		
		markerOption.position(latlng_depart_dongqu);
		markerOption.title("东区教学楼");
		marker_dongqu = aMap.addMarker(markerOption);
		markerList.add(marker_dongqu);
		
		markerOption.position(latlng_depart_library);
		markerOption.title("西区图书馆");
		markerList.add(aMap.addMarker(markerOption));
		
		markerOption.position(latlng_depart_meiguang);
		markerOption.title("美食广场");
		markerList.add(aMap.addMarker(markerOption));
		
		markerOption.position(latlng_depart_xuriyuan);
		markerOption.title("旭日苑餐厅");
		markerList.add(aMap.addMarker(markerOption));
	}
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	  }

	  @Override
	  public void onResume() {
	    super.onResume();
	    mapView.onResume();
	  }

	  /**
	   * 方法必须重写
	   * map的生命周期方法
	   */
	  @Override
	  public void onPause() {
	    super.onPause();
	    mapView.onPause();
	  }

	  /**
	   * 方法必须重写
	   * map的生命周期方法
	   */
	  @Override
	  public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    mapView.onSaveInstanceState(outState);
	  }

	  /**
	   * 方法必须重写
	   * map的生命周期方法
	   */
	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    mapView.onDestroy();
	  }

	@Override
	public void onMapLoaded() {
		// TODO Auto-generated method stub
	
		LatLngBounds.Builder aBuilder = new LatLngBounds.Builder();
		for(int i=0; i<markerList.size(); i++){
			aBuilder.include(markerList.get(i).getPosition());
		}
		
		LatLngBounds bounds = aBuilder.build();
		aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
//		float mZoom = aMap.getCameraPosition().zoom;
//		
//		Toast.makeText(getActivity(), String.valueOf(mZoom), Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public void onInfoWindowClick(final Marker marker) {
		// TODO Auto-generated method stub
		if (marker.equals(marker_dongqu)) {
			if (aMap != null) {
				// 进入室内定位界面
				Bundle data = new Bundle();
				data.putString("indoor", "indoor_dongqu");
				Intent intent = new Intent(getActivity(), IndoorActivity.class);
				intent.putExtras(data);
				
				startActivity(intent);
				
//				Toast.makeText(getActivity(), "Click", Toast.LENGTH_LONG).show();
			}
		}
	}
}
