package com.angkoters.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

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
        isiTabel();
        
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
   
   public void closeDB(){
     if(dbHelper != null){
     dbHelper.close();
    }
  }
	
   public String getRuteAngkot(Cursor cur){
		return cur.getString(2);
	}
   
   public void buatTabel(String kodeangkot, String ruteangkot,
			String biayaangkot, String jammulai, String jamselesai) {

		ContentValues isiBaris = new ContentValues();
		isiBaris.put(KODE_ANGKOT, kodeangkot);
		isiBaris.put(RUTE_ANGKOT, ruteangkot);
		isiBaris.put(BIAYA_ANGKOT, biayaangkot);
		isiBaris.put(JAM_MULAI, jammulai);
		isiBaris.put(JAM_SELESAI, jamselesai);
		try {
			 
			// db.delete(NAMA_TABEL, null, null);
			db.insert(NAMA_TABEL, null, isiBaris);
		} catch (Exception e) {

			Toast.makeText(context, "DB Error, " + e.toString(),
					Toast.LENGTH_LONG).show();
		}
		
	}
   
   public Cursor getDataRuteAngkot(String input) throws SQLException {
		Cursor cur = null;
		if (input == null || input.length() == 0) {
			cur = db.query(NAMA_TABEL, new String[] { ID, KODE_ANGKOT,
					RUTE_ANGKOT, BIAYA_ANGKOT, JAM_MULAI, JAM_SELESAI }, null,
					null, null, null, null);

		} else {
			cur = db.query(true, NAMA_TABEL, new String[] { ID, KODE_ANGKOT,
					RUTE_ANGKOT, BIAYA_ANGKOT, JAM_MULAI, JAM_SELESAI },
					// KODE_ANGKOT +"OR"+
					RUTE_ANGKOT + " like '%" + input + "%'", null, null, null,
					null, null);
		}
		if (cur != null) {
			cur.moveToFirst();
		}
		return cur;

	}
   
   public Cursor getDataAngkot() {

		Cursor cur = db.query(NAMA_TABEL, new String[] { ID, KODE_ANGKOT,
				RUTE_ANGKOT, BIAYA_ANGKOT, JAM_MULAI, JAM_SELESAI }, null,
				null, null, null, null);

		if (cur != null) {
			cur.moveToFirst();
		}
		return cur;
	}
   
     public ArrayList<ArrayList<Object>> ambilSemuaBaris(){
     ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
     Cursor cur;
     
    try
    {
      cur = db.query(namaTabel, new String[]{ID, KODE_ANGKOT, RUTE_ANGKOT},null,null,null,null,null);
      cur.moveToFirst();
      if(!cur.isAfterLast()){
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
      
    }
    return dataArray;
  }
  
  public void isiTabel() {

		buatTabel(
				"C",
				"Demak - Blauran - Karangmenjangan"
						+ "Berangkat : Terminal Jl. Sedayu - Demak - Dupak - Ps. Turi - Semarang - Kranggan - Praban - "
						+ "Siola - Gentengkali - Ngemplak - Walikota Moestadjab - Kodya - Ambengan - Kusumabangsa - THR - "
						+ "Ngaglik - Tambaksari - Pacarkeling - Kalasan - Jolotundo - Bronggalan - Tambangboyo - "
						+ "Prof. Dr. Moestopo - RS.Dr. Soetomo - Karangmenjangan - Gubeng Airlangga - Dharmawangsa."
						+ "Kembali : Kedungsroko - Pacarkeling - Residen Sudirman - Ambengan - Ngemplak - Gentengkali - Siola - Praban - Pirngadi -"
						+ "Pawiyatan - Semarang - Ps. Turi - Dupak - Demak.",
				"3000",
				"05.00",
				"20.30");

	}

}
