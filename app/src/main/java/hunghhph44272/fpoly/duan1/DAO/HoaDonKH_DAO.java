package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.HoaDonKH;

public class HoaDonKH_DAO {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public HoaDonKH_DAO(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase=dbHelper.getWritableDatabase();
    }
    public long insert(HoaDonKH history){
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
    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_invoice", "invoice_id = ?", new String[]{String.valueOf(ID)});
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonKH> getByUser(String username) {
        ArrayList<HoaDonKH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_invoice WHERE TEN = ? AND invoice_status LIKE '%Đang chuẩn bị hàng%' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()) {
            HoaDonKH history = new HoaDonKH();
            history.setId_hoadon(cursor.getInt(cursor.getColumnIndex("invoice_id")));
            history.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            history.setPhone(cursor.getInt(cursor.getColumnIndex("cart_phone")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            history.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            history.setSum(cursor.getDouble(cursor.getColumnIndex("invoice_sum")));
            history.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(history);
        }
        cursor.close();
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonKH> SeLectUESeDangCB(String username) {
        ArrayList<HoaDonKH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_invoice WHERE TEN = ? AND invoice_status LIKE '%Đang Giao%' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()) {
            HoaDonKH history = new HoaDonKH();
            history.setId_hoadon(cursor.getInt(cursor.getColumnIndex("invoice_id")));
            history.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            history.setPhone(cursor.getInt(cursor.getColumnIndex("cart_phone")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            history.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            history.setSum(cursor.getDouble(cursor.getColumnIndex("invoice_sum")));
            history.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(history);
        }
        cursor.close();
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonKH> SeLectUESeDangGiao(String username) {
        ArrayList<HoaDonKH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_invoice WHERE TEN = ? AND invoice_status LIKE '%Đã Thanh Toán%' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()) {
            HoaDonKH history = new HoaDonKH();
            history.setId_hoadon(cursor.getInt(cursor.getColumnIndex("invoice_id")));
            history.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            history.setPhone(cursor.getInt(cursor.getColumnIndex("cart_phone")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            history.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            history.setSum(cursor.getDouble(cursor.getColumnIndex("invoice_sum")));
            history.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(history);
        }
        cursor.close();
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonKH> SeLectUESeDaThanhToan(String username) {
        ArrayList<HoaDonKH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_invoice WHERE TEN = ? AND invoice_status LIKE '%Thanh Toán Thành Công%' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()) {
            HoaDonKH history = new HoaDonKH();
            history.setId_hoadon(cursor.getInt(cursor.getColumnIndex("invoice_id")));
            history.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            history.setPhone(cursor.getInt(cursor.getColumnIndex("cart_phone")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            history.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            history.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            history.setSum(cursor.getDouble(cursor.getColumnIndex("invoice_sum")));
            history.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(history);
        }
        cursor.close();
        return list;
    }
}
