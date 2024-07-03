package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.HoaDon;

public class HoaDon_Dao {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;


    public HoaDon_Dao(Context context) {
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }

    public long update(HoaDon i) {
        ContentValues values = new ContentValues();
        values.put("invoice_status", i.getStatus());
        return sqLite.update("tbl_invoice", values, "invoice_id = ?", new String[]{String.valueOf(i.getId_hoadon())});
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDaDatHang() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đang Chuẩn Bị Hàng%'  ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();
                i.setId_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDangchuanbi() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đang Giao%'  ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();
                i.setId_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDangGiao() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đã Thanh Toán%'  ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();
                i.setId_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDaThanhToan() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Thanh Toán Thành Công%'  ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();
                i.setId_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            } while (cursor.moveToNext());
        }
        return list;
    }

}
