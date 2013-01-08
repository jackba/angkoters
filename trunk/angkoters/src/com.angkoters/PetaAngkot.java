package com.angkoters;
 
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import android.os.Bundle;
 
public class PetaAngkot extends MapActivity 
{    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpetaangkot);
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}