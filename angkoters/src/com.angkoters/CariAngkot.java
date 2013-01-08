
package com.angkoters;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle; 
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class CariAngkot extends ListActivity {
	
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);		        
		String[] menu = new String[] { "Mulyosari-Sukolilo", "Mulyosari-Keputih", "Mulyosari-Gebang","Sukolilo-Keputih","Keputih-Gebang"};	
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));
		getListView().setBackgroundResource(R.drawable.bg);
	}

	@Override
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Object o = this.getListAdapter().getItem(position);
		String pilihan = o.toString();
		tampilkanPilihan(pilihan);
	}
	
	protected void tampilkanPilihan(String pilihan) {
		try {
			Intent i = null;
			if (pilihan.equals("Mulyosari-Sukolilo")) {
				i = new Intent(this, PetaAngkot.class);
			} else if (pilihan.equals("Mulyosari-Keputih")) {
				i = new Intent(this, PetaAngkot.class);
			} else if (pilihan.equals("Mulyosari-Gebang")) {
				i = new Intent(this, PetaAngkot.class);		
			} else if (pilihan.equals("Sukolilo-Keputih")) {
				i = new Intent(this, PetaAngkot.class);		
			} else if (pilihan.equals("Keputih-Gebang")) {
				i = new Intent(this, PetaAngkot.class);		
			}  else {
				Toast.makeText(this,"Anda Memilih: " + pilihan + " , Actionnya belum dibuat",Toast.LENGTH_LONG).show();
			}
			startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}