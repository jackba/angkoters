package com.angkoters.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager{
	private static String namaDB = "AngkotersDB";
  private static String namaTabel = "DataAngkot";
  private static String ID = "_id";
  private static String KODE_ANGKOT = "kodeangkot";
  private static String RUTE_ANGKOT = "ruteangkot";
  private static int BIAYA_ANGKOT;
  private static String JAM_BERANGKAT = "jamberangkat";
  privte static String JAM_PULANG = "jampulang";
	private String namaTabel = "CREATE TABLE " + DataAngkot + "("+
   ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
   KODE_ANGKOT+" TEXT, "+
   RUTE_ANGKOT+" TEXT, "+
   BIAYA_ANGKOT+" INTEGER, "+
   JAM_BERANGKAT+" TEXT," + 
   JAM_PULANG+"TEXT)";

  private Context context;
  private SQLiteDatabase db;
  private DatabaseOpenHelper dbHelper;
   
	public DatabaseManager(Context ctx) {
		this.context = ctx;
    dbHelper = new DataseOpenHelper(context);
    db = dbHelper.getWritableDatabase();
	}
   
  private static class DatabaseOpenHelper extends SQLiteDatabase{
      public void onCreate(SQLiteDatabase db){
        db.execSQL(namaTabel);
        
     }

       @Override
       public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS"+namaTabel);
        onCreate(db);
		
     }
  }	
	
	public Cursor getDataAngkot(){
		return(getReadableDatabase().rawQuery("SELECT * FROM namaTabel",null));
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
   
     public ArrayList<ArrayList<Object>> ambilSemuaBaris(){
     ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
     Cursor cur;
     
    try
    {
      cur = db.query(namaTabel, new String[]{ID, KODE_ANGKOT, RUTE_ANGKOT},null,null,null,null,null);
      if(!cur.moveToFirst()){
        do{
        ArrayList<Object> dataList = ArrayList<Object>();
        dataList.add(cur.getString(0));
        dataList.add(cur.getString(1));
        dataList.add(cur.getString(2));
        
        dataArray.add(dataList);
        } while (cur.moveToNext());
        }
      
    } catch(Exception e){
      e.printStackTrace();
      log.e("Database Error", e.toString());
    }
    return dataArray;
  }

}
