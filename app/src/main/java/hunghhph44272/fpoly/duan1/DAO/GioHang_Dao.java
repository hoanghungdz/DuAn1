package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.Gio_Hang;

public class GioHang_Dao {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public GioHang_Dao(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase= dbHelper.getWritableDatabase();
    }

    public ArrayList<Gio_Hang> getAllData(){
        String sql = "SELECT * From tbl_cart";
        return getData(sql);
    }
    public long insert(Gio_Hang gio_hang) {
        ContentValues values = new ContentValues();
        values.put("coffee_id", gio_hang.getIdCoffee());
        values.put("cart_quantity", gio_hang.getQuanti());
        values.put("cart_sum", gio_hang.getSum());
        values.put("TEN", gio_hang.getUsername());
        values.put("size",gio_hang.getSize());
        return sqLiteDatabase.insert("tbl_cart", null, values);
    }
    @SuppressLint("Range")
    public ArrayList<Gio_Hang> getData(String sql, String... SelectAvg) {
        ArrayList<Gio_Hang> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);

        while (cursor.moveToNext()) {
            Gio_Hang gio_hang = new Gio_Hang();
            gio_hang.setIdCart(cursor.getInt(cursor.getColumnIndex("cart_id")));
            gio_hang.setIdCoffee(cursor.getInt(cursor.getColumnIndex("coffee_id")));
            gio_hang.setQuanti(cursor.getInt(cursor.getColumnIndex("cart_quantity")));
            gio_hang.setSum(cursor.getDouble(cursor.getColumnIndex("cart_sum")));
            gio_hang.setUsername(cursor.getString(cursor.getColumnIndex("TEN")));
            gio_hang.setSize(cursor.getString(cursor.getColumnIndex("size")));
            list.add(gio_hang);
        }

        cursor.close();
        return list;
    }
    public boolean FoodExists(int coffeeId, String username) {
        String query = "SELECT * FROM tbl_cart WHERE coffee_id = ? AND TEN = ?";
        String[] selectionArgs = {String.valueOf(coffeeId), username};
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public ArrayList<Gio_Hang> getByUser(String user) {
        String sql = "SELECT * FROM tbl_cart  where TEN = ?";
        return getData(sql, user);
    }
    public long updateSum(Gio_Hang gio_hang){
        ContentValues values = new ContentValues();
        values.put("coffee_id", gio_hang.getIdCoffee());
        values.put("cart_quantity", gio_hang.getQuanti());
        values.put("cart_sum", gio_hang.getSum());
        values.put("TEN",gio_hang.getUsername());
        values.put("size",gio_hang.getSize());
        return sqLiteDatabase.update("tbl_cart", values, "cart_id = ?", new String[]{String.valueOf(gio_hang.getIdCart())});
    }
    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_cart", "cart_id = ?", new String[]{String.valueOf(ID)});
    }

    public void DeleteCart( String username){
        String query = "DELETE FROM tbl_cart WHERE TEN = ?";
        String[] selectionArgs = { username};
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
    }
}
