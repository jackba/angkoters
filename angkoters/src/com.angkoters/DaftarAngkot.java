package com.angkoters;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class DaftarAngkot extends Activity {
	ListView lv;
	String[] kodeangkot= {};
	String[] idangkot={};
	 
	DatabaseManager dm;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdaftarangkot);
 
		dm = new DatabaseManager(this);
		dm.isiTabel();
 
		lv = (ListView) findViewById(R.id.listViewAngkot);
		
		ambilData();
	} 
	private void ambilData() {
		// TODO Auto-generated method stub
		final ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
		kodeangkot = new String[data.size()];
		for (int posisi = 0; posisi < data.size(); posisi++) {
 
			ArrayList<Object> baris = data.get(posisi);
			kodeangkot[posisi] = baris.get(1).toString();
 
		}
		
		ArrayAdapter<String> dataKode = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, kodeangkot);
		lv.setAdapter(dataKode);
		lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				
				String angkot = lv.getItemAtPosition(pos).toString();
				
				for(int i = 0; i<data.size();i++){
					if(angkot.equals(kodeangkot[i])){
						pos = i;
						break;
					}
				}
				String valPos = String.valueOf(pos);
				if(kodeangkot[pos].equals(valPos)){
					Toast.makeText(getApplicationContext(),
						      "Click ListItem Number " + valPos, Toast.LENGTH_LONG)
						      .show();
				Intent in = new Intent(DaftarAngkot.this, DetailAngkot.class );
				startActivity(in);
				
				}
			}
		});
		
	}
}


