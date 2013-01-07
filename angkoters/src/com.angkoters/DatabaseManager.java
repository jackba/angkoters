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
  private static String JAM_MULAI = "jambselesai";
  private static String JAM_SELESAI = "jamselesai";
	
  private static final String NAMA_DB = "AngkotersBaru";
	private static final String NAMA_TB = "DataAngkotBaru";
	private static final int VERSI_DB = 1;

  private Context context;
  private SQLiteDatabase db;
  private DatabaseOpenHelper dbHelper;
   
  private static final String DATABASE_CREATE =
			  "CREATE TABLE if not exists " + NAMA_TB + "( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
					  KODE_ANGKOT+" TEXT," +
					  RUTE_ANGKOT+" TEXT, "+
					  BIAYA_ANGKOT+" TEXT, "+
					  JAM_MULAI+" TEXT, "+
					  JAM_SELESAI+" TEXT);";
  
	public DatabaseManager(Context ctx) {
		this.context = ctx;
	}
   
  private static class DatabaseOpenHelper extends SQLiteDatabase{
      public void onCreate(SQLiteDatabase db){
        db.execSQL(namaTabel);
        
     }

       @Override
       public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
       // db.execSQL("DROP TABLE IF EXISTS"+namaTabel);
       // onCreate(db);
		
     }
  }	
  
  public DatabaseManager readDB() throws SQLException {
		dbHelper = new DBOpenHelper(context,  NAMA_DB, null, VERSI_DB);
		db = dbHelper.getReadableDatabase();
		return this;
	}
   
   public DatabaseManager writeDB() throws SQLException {
		dbHelper = new DBOpenHelper(context, NAMA_DB, null, VERSI_DB );
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public Cursor getDataAngkot(){
		return(getReadableDatabase().rawQuery("SELECT * FROM namaTabel",null));
	}
	
	 public static getIdAngkot(){
		return ID;
	}
	
   public static getKodeAngkot(){
		return KODE_ANGKOT;
	}
   
   public void closeDB(){
     dbHelper.close();
    }
  }
	
   public static getRuteAngkot(){
		return RUTE_ANGKOT;
	}
   
   public static getBiayaAngkot(){
		return BIAYA_ANGKOT;
	}
   
   public static getJamMulai(){
		return JAM_MULAI;
	}
   
   
   public static getJamSelesai(){
		return JAM_SELESAI;
	}
   
   public long setDataAngkot(String kodeangkot, String ruteangkot,
			String biayaangkot, String jammulai, String jamselesai) {

		ContentValues isiBaris = new ContentValues();
		isiBaris.put(KODE_ANGKOT, kodeangkot);
		isiBaris.put(RUTE_ANGKOT, ruteangkot);
		isiBaris.put(BIAYA_ANGKOT, biayaangkot);
		isiBaris.put(JAM_MULAI, jammulai);
		isiBaris.put(JAM_SELESAI, jamselesai);
		
    return db.insert(NAMA_TB, null, initialValues);
		
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
      
   public Cursor getALLDataAngkot() {
		Cursor cursorALL = db.query(NAMA_TABEL, new String[] { ID, KODE_ANGKOT,
				RUTE_ANGKOT, BIAYA_ANGKOT, JAM_MULAI, JAM_SELESAI }, null,
				null, null, null, null);
		}
		return cursorALL;
	}
   
    public int deleteAllDataAngkot() {
		int a = db.delete(NAMA_TB, null, null);
		return a;

	}
  public void insertDataAngkot() {

		setDataAngkot(
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
