package com.angkoters;

import java.util.List;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;

public class SettingUpMap extends MapActivity {
	/** Called when the activity is first created. */
	private MapController mc;
	private MapView mapView;
	private GeoPoint gP;
	public PetaRute peta;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		gP = new GeoPoint(0, 0);// set default geopoint (current pos)

		mc = mapView.getController();
		mc.setCenter(gP);
		mc.setZoom(9);

//		mapOverlays = mapView.getOverlays();
//		projection = mapView.getProjection();
//
//		peta = new PetaRute();
//		mapOverlays.add(peta);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
