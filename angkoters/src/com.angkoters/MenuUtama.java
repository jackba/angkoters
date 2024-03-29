package com.angkoters;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle; 
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MenuUtama extends ListActivity {
	
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);		        
		String[] menu = new String[] { "Daftar Angkot", "Pencarian Angkot","Info Angkoters", "Keluar" };	
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
			if (pilihan.equals("Daftar Angkot")) {
				i = new Intent(this, DaftarAngkot.class);
			} else if (pilihan.equals("Pencarian Rute")) {
				//i = new Intent(this, cariAngkot.class);
			} else if (pilihan.equals("Tentang Angkoters")) {
				i = new Intent(this, TentangAngkot.class);
			} else if (pilihan.equals("Keluar")) {
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME);
				exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(exit);				
			} else {
				Toast.makeText(this,"Anda Memilih: " + pilihan + " , Actionnya belum dibuat",Toast.LENGTH_LONG).show();
			}
			startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}