package com.angkoters.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager{
	private static String namaDB = "AngkotersDB";
  private static final String namaTabel = "DataAngkot";
  private static final String ID = "_id";
  private static final String KODE_ANGKOT = "kodeangkot";
  private static final String RUTE_ANGKOT = "ruteangkot";
  private static final String BIAYA_ANGKOT = "biayaangkot";
  private static final String WAKTU_OPERASI = "waktuoperasi";
	private String sql = "CREATE TABLE " + DataAngkot + "("+
   ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
   KODE_ANGKOT+" TEXT, "+
   RUTE_ANGKOT+" TEXT, "+
   BIAYA_ANGKOT+" INTEGER, "+
   WAKTU_OPERASI+" TEXT)";

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
        db.execSQL("DROP TABLE IF EXISTS namaTabel");
        db.execSQL(sql);
     }

       @Override
       public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
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
      cur = db.query(namaTabel, new String[]{ID, KODE_ANGKOT, RUTE_ANGKOT,BIAYA_ANGKOT, WAKTU_OPERASI},null,null,null,null,null);
      if(!cur.moveToFirst()){
        do{
        ArrayList<Object> dataList = ArrayList<Object>();
        dataList.add(cur.getLong(0));
        dataList.add(cur.getLong(1));
        dataList.add(cur.getLong(2));
        dataList.add(cur.getLong(3));
        dataList.add(cur.getLong(4));
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
