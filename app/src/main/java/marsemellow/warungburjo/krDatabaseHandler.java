package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;


public class krDatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "keranjang.db";
    public static final String TABLE_NAME = "keranjang";

    public krDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (_id integer primary key autoincrement, idMakanan text, namaMakanan text, harga text, jumlah text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addKeranjang(Keranjang keranjang){
        Log.d("addKeranjang", keranjang.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idMakanan", keranjang.getIdMakanan());
        values.put("namaMakanan", keranjang.getNamaMakanan());
        values.put("harga", keranjang.getHargaMakanan());
        values.put("jumlah", keranjang.getJumlah());

        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public String hitungTotal(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT SUM(harga * jumlah) as total FROM keranjang;", null);
        int sum = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    sum = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }

        String total = String.valueOf(sum);

        return total;


    }

    public JSONArray isiKeJSONArray(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT idMakanan, jumlah FROM keranjang;", null);
        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i=0;i< totalColumn; i++){
                if(cursor.getColumnName(i) != null)
                {
                    try{
                        if(cursor.getString(i) != null){
                            Log.d("hasilnya", cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        else
                        {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("hasilnya", e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        cursor.close();
        Log.d("hasilnya", resultSet.toString());
        return resultSet;

    }



}
