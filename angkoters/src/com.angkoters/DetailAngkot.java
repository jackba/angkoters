package com.angkoters;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

public class DetailAngkot extends Activity{
	
	DatabaseManager dm;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdaftarangkot);
 
		dm = new DatabaseManager(this);
		viewDetailAngkot();
	}
	
	private String ambilParameter(){
		String KodeAngkot;
		Bundle bundle = getIntent().getExtras();
		KodeAngkot = bundle.getString("KodePilihan");
		return KodeAngkot;
	}
	
	private void viewDetailAngkot(){
		Cursor cur = dm.getDataRuteAngkot(ambilParameter());
		
		String isi [] = new String[]{
			dm.ID, dm.KODE_ANGKOT, dm.RUTE_ANGKOT, dm.BIAYA_ANGKOT, dm.JAM_MULAI,
			dm.JAM_SELESAI
		};
		
		int tempat [] = new int[]{
				
		};
	}

}
