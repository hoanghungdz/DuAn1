package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.Admin;

public class Admin_Dao {
    private SQLiteDatabase db;

    public Admin_Dao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long updatepass(Admin obj) {
        ContentValues values = new ContentValues();
        values.put("TEN", obj.getTEN_AD());
        values.put("MATKHAU", obj.getMAT_KHAU_AD());
        return db.update("ADMIN", values, "ID_ADMIN=?", new String[]{String.valueOf(obj.getID_ADMIN())});
    }

    public List<Admin> getAll() {
        String sql = "SELECT * FROM ADMIN";
        return getData(sql);
    }

    public Admin getID(String id) {
        String sql = "SELECT * FROM ADMIN WHERE ID_ADMIN=?";
        List<Admin> list = getData(sql, id);
        return list.get(0);
    }

    public int checkLogin(String taikhoan, String password) {
        String sql = "SELECT * FROM ADMIN WHERE TEN=? AND MATKHAU=?";
        List<Admin> list = getData(sql, taikhoan, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<Admin> getData(String sql, String... selectionArgs) {
        List<Admin> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Admin obj = new Admin();
            obj.setID_ADMIN(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID_ADMIN"))));
            obj.setTEN_AD(cursor.getString(cursor.getColumnIndex("TEN")));
            obj.setEMAIL_AD(cursor.getString(cursor.getColumnIndex("EMAIL")));
            obj.setMAT_KHAU_AD(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            list.add(obj);
        }
        return list;
    }
}
