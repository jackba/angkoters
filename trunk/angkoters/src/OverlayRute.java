package com.angkoters;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

class PetaRute extends Overlay{
	public List mapOverlays;
	public Projection projection;
	public PetaRute(){
	 
	}
	 
public void drawMap(Canvas canvas, MapView mapv, boolean shadow){
	 super.draw(canvas, mapv, shadow);
	 //seting tampilan rute angkot
	 Paint mPaint = new Paint();
	 mPaint.setDither(true);
	 mPaint.setColor(Color.RED);
	 mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	 mPaint.setStrokeJoin(Paint.Join.ROUND);
	 mPaint.setStrokeCap(Paint.Cap.ROUND);
	 mPaint.setStrokeWidth(4);
	 
	 
	 GeoPoint gP1 = new GeoPoint(1,1);//Set data geopoint lokasi1
	 GeoPoint gP2 = new GeoPoint(0,0);//set data geopoint lokasi2 /tujuan1
	 
	 GeoPoint gP4 = new GeoPoint(0, 0);//set data geopoint lokasi2 /tujuan1
	 GeoPoint gP3 = new GeoPoint(0,0);//set data geopoint lokasi3 /tujuan2
	 
	Point p1 = new Point();
	 Point p2 = new Point();
	 Path path1 = new Path();
	 
	 Point p3 = new Point();
	 Point p4 = new Point();
	 Path path2 = new Path();
	 projection.toPixels(gP2, p3);
	 projection.toPixels(gP1, p4);
	 
	path1.moveTo(p4.x, p4.y);//lokasi1
	 path1.lineTo(p3.x,p3.y);//path gambar garis ke lokasi2
	 
	 projection.toPixels(gP3, p1);
	 projection.toPixels(gP4, p2);
	 
	path2.moveTo(p2.x, p2.y);//lokasi2
	 path2.lineTo(p1.x,p1.y);//path gambar garis ke lokasi3
	 
	 canvas.drawPath(path1, mPaint);//gambar garis ke lokasi2
	 canvas.drawPath(path2, mPaint);//gambar garis ke lokasi3
	 
	 }
	 
	}


