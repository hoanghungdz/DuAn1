package hunghhph44272.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Database.DbHelper;

public class DoanhThu_Dao {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;
    ;

    public DoanhThu_Dao(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sql1 ="   SELECT substr(invoice_time,7,10) AS date, SUM(invoice_sum) AS doanhThu" +
                "        FROM tbl_invoice" +
                "        where  invoice_status LIKE '%Thanh Toán Thành Công%' AND date BETWEEN ? AND ?";
        ArrayList<Integer> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql1, new String[]{tuNgay,denNgay});
        while (c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }

}
