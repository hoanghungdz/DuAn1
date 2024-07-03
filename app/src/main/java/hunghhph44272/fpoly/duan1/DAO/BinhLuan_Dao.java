package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.BinhLuan;

public class BinhLuan_Dao {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public BinhLuan_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public ArrayList<BinhLuan> getByFoodId(String ID) {
        String sql = "SELECT * FROM tbl_comment  where coffee_id = ?";
        return getData(sql, ID);
    }
    @SuppressLint("Range")
    public ArrayList<BinhLuan> getData(String sql, String... SelectAvg){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);
        while (cursor.moveToNext()){
            BinhLuan binhLuan = new BinhLuan();
            binhLuan.setComment_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("comment_id"))));
            binhLuan.setUser_name(cursor.getString(cursor.getColumnIndex("TEN")));
            binhLuan.setComment_content(cursor.getString(cursor.getColumnIndex("comment_content")));
            binhLuan.setFood_id(cursor.getInt(cursor.getColumnIndex("coffee_id")));
            binhLuan.setRating(cursor.getInt(cursor.getColumnIndex("rating")));
            list.add(binhLuan);
        }
        return list;
    }
    public int countCmt(String id){
        String sql="SELECT COUNT(comment_id) FROM tbl_comment WHERE coffee_id =?";
        Cursor c=sqLiteDatabase.rawQuery(sql,new String[]{id});
        int count = 0;
        if (c != null) {
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            c.close();
        }

        return count;
    }
    public float getAVG(String coffeeID){
        String query = "SELECT AVG(rating) FROM tbl_comment WHERE coffee_id=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{coffeeID});
        float averageRating = 0;
        if (cursor.moveToFirst()) {
            averageRating = cursor.getFloat(0);
        }
        cursor.close();
        return averageRating;

    }
}
