package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.KhachHang;

public class KhachHang_Dao {
    private SQLiteDatabase db;

    DbHelper dbHelper;

    SharedPreferences sharedPreferences;

    public KhachHang_Dao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        sharedPreferences = context.getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
    }
    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("TEN", obj.getTEN_KH());
        values.put("SDT", obj.getSDT());
        values.put("EMAIL", obj.getEMAIL_KH());
        values.put("MATKHAU", obj.getMAT_KHAU_KH());
        return db.insert("KHACH_HANG", null, values);
    }
    public long updatepass(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("TEN", obj.getTEN_KH());
        values.put("SDT", obj.getSDT());
        values.put("MATKHAU", obj.getMAT_KHAU_KH());
        return db.update("KHACH_HANG", values, "ID_KHACH_HANG=?", new String[]{String.valueOf(obj.getID_KH())});
    }

    public long UpdateThongTinKH(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("TEN", obj.getTEN_KH());
        values.put("SDT", obj.getSDT());
        values.put("EMAIL", obj.getEMAIL_KH());
        if (!db.isOpen()) {
            db = dbHelper.getWritableDatabase();
        }

        return db.update("KHACH_HANG", values, "ID_KHACH_HANG=?", new String[]{String.valueOf(obj.getID_KH())});
    }
    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KHACH_HANG";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KHACH_HANG WHERE ID_KHACH_HANG=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }

    public int checkLogin(String taikhoan, String password) {
        String sql = "SELECT * FROM KHACH_HANG WHERE TEN=? AND MATKHAU=?";
        List<KhachHang> list = getData(sql,taikhoan, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setID_KH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID_KHACH_HANG"))));
            obj.setTEN_KH(cursor.getString(cursor.getColumnIndex("TEN")));
            obj.setSDT(cursor.getString(cursor.getColumnIndex("SDT")));
            obj.setEMAIL_KH(cursor.getString(cursor.getColumnIndex("EMAIL")));
            obj.setMAT_KHAU_KH(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            list.add(obj);
        }
        return list;
    }

    public boolean checkUser(String username, String password) { // kiểm tra tài khoản có trong bảng admin không
        db = dbHelper.getReadableDatabase();
        String[] columns = {"ID_KHACH_HANG"};
        String selection = "TEN" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("KHACH_HANG", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public boolean checkPasswordAndChange(String oldPassword, String newPassword) {
        // Thực hiện kiểm tra mật khẩu cũ và username cũ ở đây
        String username = sharedPreferences.getString("TEN", "");

        if (checkUser(username, oldPassword)) {
            // Mật khẩu cũ đúng
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword); // Cập nhật mật khẩu mới

            // Thực hiện cập nhật mật khẩu mới cho người dùng có tên đăng nhập tương ứng
            db.update("KHACH_HANG", values, "TEN = ?", new String[]{username});
            db.close();
            return true; // Trả về true nếu đổi mật khẩu thành công
        } else {
            // Mật khẩu cũ không đúng
            return false;
        }
    }

    public ArrayList<KhachHang>  getUsersByName(String username) {
        ArrayList<KhachHang> userList = new ArrayList<>();

        String[] columns = {"TEN"};
        String selection = "TEN=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("KHACH_HANG", columns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("TEN"));


            KhachHang khachHang = new KhachHang(name);
            userList.add(khachHang);
        }
        cursor.close();

        return userList;
    }

    @SuppressLint("Range")
    public KhachHang LayThongTinKH(String tendnhap, String mkdnhap) {
        KhachHang khachHang = new KhachHang();
        String query = "SELECT * FROM KHACH_HANG WHERE TEN = ? AND MATKHAU = ?";

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Thực hiện truy vấn bằng cách sử dụng PreparedStatement
        Cursor cursor = db.rawQuery(query, new String[]{tendnhap, mkdnhap});

        // Kiểm tra xem có dữ liệu trả về hay không
        if (cursor != null && cursor.moveToFirst()) {
            // Lấy dữ liệu từ Cursor và thiết lập cho đối tượng KhachHang
            khachHang.setID_KH(cursor.getInt(cursor.getColumnIndex("ID_KHACH_HANG")));
            khachHang.setTEN_KH(cursor.getString(cursor.getColumnIndex("TEN")));
            khachHang.setSDT(cursor.getString(cursor.getColumnIndex("SDT")));
            khachHang.setEMAIL_KH(cursor.getString(cursor.getColumnIndex("EMAIL")));
            khachHang.setMAT_KHAU_KH(cursor.getString(cursor.getColumnIndex("MATKHAU")));

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        // Đóng cơ sở dữ liệu
        db.close();

        return khachHang;
    }


}
