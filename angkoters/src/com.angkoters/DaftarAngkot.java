package com.angkoters;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;


public class DaftarAngkot extends Activity {
	ListView lv;  
	private DatabaseManager dm;
  private SimpleCursorAdapter adapter;
	private ListView listview;
	private ProgressDialog loadDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdaftarangkot);   
  	  processingDB();
		ambilData();
	} 
  
  private void processingDB(){
  try {
			
		loadDB= new ProgressDialog(this);
    	loadDB.setMessage("Silahkan Tunggu Sebentar");
		dm = new DatabaseManager(this);
		dm.writeDB();
		dm.deleteAllDataAngkot();
		dm.insertDataAngkot();
		dm.close();
		dm = new DatabaseManager(this);
		dm.readDB();
		
		} catch (Exception e){
			Toast.makeText(this, "Database gagal diproses", 4000).show();
		}
  
  }
  
	private void ambilData() {
		// TODO Auto-generated method stub
		Cursor cursor = dm.getAllDataAngkot();
		startManagingCursor(cursor);
		String[] columns = new String[] {
				DatabaseManager.KODE_ANGKOT, // --> R.id.kode
				DatabaseManager.RUTE_ANGKOT,//DatabaseManager.RUTE_ANGKOT, // --> R.id.rute
				DatabaseManager.BIAYA_ANGKOT,//DatabaseManager.BIAYA_ANGKOT, // --> R.id.biaya
				DatabaseManager.JAM_MULAI,//DatabaseManager.JAM_MULAI, // --> R.id.mulai
				DatabaseManager.JAM_SELESAI//DatabaseManager.JAM_SELESAI // --> R.id.sampai
		};
		int[] to = new int[] {
				R.id.kode,
				R.id.rute,
				R.id.biaya,
				R.id.mulai,
				R.id.sampai
		};

		adapter = new SimpleCursorAdapter(this, R.layout.viewrowangkot,
				cursor, columns, to);

		listview = (ListView) findViewById(R.id.listViewAngkot);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String selectedKode = cursor.getString(cursor.getColumnIndexOrThrow("kodeangkot"));
				String selectedRute = cursor.getString(cursor.getColumnIndexOrThrow("ruteangkot"));
				//Toast.makeText(getApplicationContext(), selectedRute,Toast.LENGTH_LONG).show();
				
				AlertDialog.Builder build = new AlertDialog.Builder(DaftarAngkot.this);
				build.setTitle(selectedKode);
				build.setMessage(selectedRute);
				build.setPositiveButton("Kembali", null);
				build.show();
			}
		});
      
      EditText myFilter = (EditText) findViewById(R.id.myFilter);
		myFilter.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s.toString());
			}
		});
				
}


