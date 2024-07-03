package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;
import hunghhph44272.fpoly.duan1.Model.CoffeeAD;

public class CoffeeAD_DAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public CoffeeAD_DAO(Context contex) {
        dbHelper = new DbHelper(contex);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<CoffeeAD> getAllData() {
        String sql = "SELECT * FROM tbl_coffee";
        return getData(sql);
    }
    @SuppressLint("Range")
    public ArrayList<CoffeeAD> getData(String sql, String... SelectAvg) {
        ArrayList<CoffeeAD> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_coffee", SelectAvg);
        while (cursor.moveToNext()) {
            CoffeeAD food = new CoffeeAD();
            food.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_id"))));
            food.setType(cursor.getString(cursor.getColumnIndex("typeCoffee_typeName")));
            food.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
            food.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
            food.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
            food.setPrice(cursor.getInt(cursor.getColumnIndex("coffee_price")));
            food.setSize(cursor.getString(cursor.getColumnIndex("size")));
            list.add(food);
        }
        return list;
    }
    public long insert(CoffeeAD coffeeAD) {
        ContentValues values = new ContentValues();
        values.put("typeCoffee_typeName", coffeeAD.getType());
        values.put("coffee_img", coffeeAD.getImg());
        values.put("coffee_name", coffeeAD.getName());
        values.put("coffee_description", coffeeAD.getDes());
        values.put("coffee_price", coffeeAD.getPrice());
        values.put("size",coffeeAD.getSize());
        return sqLiteDatabase.insert("tbl_coffee", null, values);
    }

    private ArrayList<String> getSuggestions(String query) {
        SQLiteDatabase sqLite = dbHelper.getReadableDatabase();
        ArrayList<String> suggestions = new ArrayList<>();

        String[] projection = { "coffee_name" };
        String selection = "coffee_name LIKE ?";
        String[] selectionArgs = new String[] { "%" + query + "%" };

        Cursor cursor = sqLite.query(
                true, // distinct
                "tbl_coffee",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                "10" // Giới hạn số lượng gợi ý
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String suggestion = cursor.getString(Integer.parseInt(String.valueOf(cursor.getColumnIndex("coffee_name"))));
                suggestions.add(suggestion);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        // In gợi ý tìm kiếm ra logcat
        for (String suggestion : suggestions) {
            Log.d("SearchSuggestions", suggestion);
        }

        return suggestions;
    }
    @SuppressLint("Range")
        public ArrayList<CoffeeAD> search(String ten) {
            SQLiteDatabase sqLite = dbHelper.getReadableDatabase();
            ArrayList<CoffeeAD> list = new ArrayList<>();

            String[] projection = {
                    "coffee_id",
                    "coffee_img",
                    "typeCoffee_typeName",
                    "coffee_name",
                    "coffee_description",
                    "coffee_price",
                    "size"
            };

            String selection = "coffee_name LIKE ?";
            String[] selectionArgs = new String[] { "%" + ten + "%" };

            Cursor cursor = sqLite.query(
                    "tbl_coffee",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    CoffeeAD coffee = new CoffeeAD();
                    coffee.setId(cursor.getInt(cursor.getColumnIndex("coffee_id")));
                    coffee.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
                    coffee.setType(cursor.getString(cursor.getColumnIndex("typeCoffee_typeName")));
                    coffee.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
                    coffee.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
                    coffee.setPrice(cursor.getInt(cursor.getColumnIndex("coffee_price")));
                    coffee.setSize(cursor.getString(cursor.getColumnIndex("size")));
                    list.add(coffee);
                } while (cursor.moveToNext());
            }

            if (cursor != null) {
                cursor.close();
            }

            // Đề xuất tìm kiếm
            ArrayList<String> suggestions = getSuggestions(ten);
            // Thực hiện hành động khi có gợi ý tìm kiếm

            return list;
        }
    public CoffeeAD getById(int id) {
        Cursor cursor = sqLiteDatabase.query("tbl_coffee", null, "coffee_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()) {
            return new CoffeeAD(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5),cursor.getString(6));
        } else {
            return null;
        }
    }

    public long update(CoffeeAD ad) {
        ContentValues values = new ContentValues();
        values.put("typeCoffee_typeName", ad.getType());
        values.put("coffee_img", ad.getImg());
        values.put("coffee_name", ad.getName());
        values.put("coffee_description", ad.getDes());
        values.put("coffee_price", ad.getPrice());
        values.put("size",ad.getSize());
        return sqLiteDatabase.update("tbl_coffee", values, "coffee_id = ?", new String[]{String.valueOf(ad.getId())});
    }
    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_coffee", "coffee_id = ?", new String[]{String.valueOf(ID)});
    }
}
