package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.LoaiCoffee;

public class LoaiCoffeeDAO {
   
    DbHelper dbHelper;
    private static SQLiteDatabase sqLite;

    public LoaiCoffeeDAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public static ArrayList<LoaiCoffee> getDataLoaiCoffee(String sql, String... SelectAvg) {
        ArrayList<LoaiCoffee> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_typeCoffee", SelectAvg);
        while (cursor.moveToNext()) {
            LoaiCoffee type = new LoaiCoffee();
            type.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeCoffee_id"))));
            type.setTenLoai(cursor.getString(cursor.getColumnIndex("typeCoffee_typeName")));
            list.add(type);
        }
        return list;
    }

    public static ArrayList<LoaiCoffee> getAllData() {
        String sql = "SELECT * FROM tbl_typeCoffee";
        return getDataLoaiCoffee(sql);
    }

    public static ArrayList<String> name() {
        String name = "SELECT typeCoffee_typeName FROM tbl_typeCoffee";
        return getName(name);
    }


    public static ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("typeCoffee_typeName"));
            lst.add(name);
        }
        return lst;

    }
    public long insert(LoaiCoffee lc){
        ContentValues values= new ContentValues();
        values.put("typeCoffee_typeName",lc.getTenLoai());
        return sqLite.insert("tbl_typeCoffee",null,values);
    }
    public int update(LoaiCoffee lc){
        ContentValues values=new ContentValues();
        values.put("typeCoffee_typeName",lc.getTenLoai());
        return sqLite.update("tbl_typeCoffee",values,"typeCoffee_id=?",new String[]{String.valueOf(lc.getMaLoai())});
    }
    public int delete(String id){
        return sqLite.delete("tbl_typeCoffee","typeCoffee_id=?",new String[]{id});
    }
    public ArrayList<LoaiCoffee> getAll(){
        String sql = "Select * from tbl_typeCoffee";
        return getDataLoaiCoffee(sql);
    }
}
