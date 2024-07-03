package hunghhph44272.fpoly.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.LichSuDonHang;

public class LichSuDonHangDAO {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public LichSuDonHangDAO(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase=dbHelper.getWritableDatabase();
    }
    public long insert(LichSuDonHang history){
        ContentValues values = new ContentValues();
        values.put("cart_id", history.getId_cart());
        values.put("TEN", history.getName());
        values.put("cart_address", history.getAddress());
        values.put("cart_phone", history.getPhone());
        values.put("invoice_time", history.getTime());
        values.put("invoice_content", history.getContten());
        values.put("invoice_sum", history.getSum());
        values.put("invoice_status", history.getStatus());

        return sqLiteDatabase.insert("tbl_invoice", null, values);
    }
}
