package com.angkoters;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DetailAngkot extends Activity{
	
	DatabaseManager dm;
	SimpleCursorAdapter adapterCur;
   
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdetailangkot);
 
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
		
		int tempat [] = new int[]{R.id.textView1, R.id.textView2, R.id.textView3,
				R.id.textView4, R.id.textView5
				
		};
     adapterCur = new SimpleCursorAdapter(this, R.layout.viewdetailangkot2, cur, isi, tempat);
     ListView list = (ListView) findViewById(R.id.listViewDetailAngkot);
     list.setAdapter(adapterCur);
	}

}
