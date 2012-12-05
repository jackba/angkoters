package com.angkoters.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbManager extends SQLiteOpenHelper{
	private static String namaDB = "angkotersDB";
	private String sql = "CREATE TABLE daftarRuteTB(idRute INTEGER PRIMARY KEY AUTOINCREMENT , kodeAngkot TEXT, ruteAngkot TEXT)";
	private String query = "SELECT idRute, kodeAngkot, ruteAngkot";
	public dbManager(Context context) {
		super(context, namaDB , null,1);
		// TODO Auto-generated constructor stub
	}
	
	public void onCreate(SQLiteDatabase db){
    db.execSQL("DROP TABLE IF EXISTS daftarRuteTB");
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public Cursor getDataAngkot(){
		return(getReadableDatabase().rawQuery(query,null));
	}
	
	public String getIdAngkot(Cursor cur){
		return cur.getString(0);
	}
	
	public String getKodeAngkot(Cursor cur){
		return cur.getString(1);
	}
	
	public String getRuteAngkot(Cursor cur){
		return cur.getString(2);
	}

}
