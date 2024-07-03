package hunghhph44272.fpoly.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "duAn1.txt";
    private static final int DB_version = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }
    static final String CREATE_TABLE_ADMIN =
            "CREATE TABLE ADMIN(" +
                    "ID_ADMIN INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TEN TEXT NOT NULL, " +
                    "EMAIL TEXT NOT NULL," +
                    "MATKHAU TEXT NOT NULL)";
    static final String CREATE_TABLE_KHACH_HANG =
            "CREATE TABLE KHACH_HANG(" +
                    "ID_KHACH_HANG INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TEN TEXT NOT NULL, " +
                    "SDT TEXT NOT NULL, " +
                    "EMAIL TEXT NOT NULL," +
                    "MATKHAU TEXT NOT NULL)";

//    public static final String TABLE_REQUEST_CREATE = "CREATE TABLE IF NOT EXISTS " +
//            "tbl_request (" +
//            "request_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "request_name TEXT ," +
//            "request_email TEXT ," +
//            "request_phone TEXT " +
//            ")";

    public static final String TABLE_TYPE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_typeCoffee (" +
            "typeCoffee_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "typeCoffee_typeName TEXT NOT NULL" +
            ")";


    public static final String TABLE_COFFEE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_coffee (" +
            "coffee_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "typeCoffee_typeName TEXT REFERENCES tbl_typeCoffee(typeCoffee_typeName)," +
            "coffee_img TEXT NOT NULL, " +
            "coffee_name TEXT NOT NULL, " +
            "coffee_description TEXT NOT NULL, " +
            "coffee_price DOUBLE NOT NULL, " +
            "size TEXT " +
            ")";

    public static final String TABLE_CART_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_cart (" +
            "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "coffee_id INTEGER REFERENCES tbl_coffee(coffee_id), " +
            "TEN TEXT REFERENCES KHACH_HANG(TEN), " +
            "cart_quantity INTEGER NOT NULL, " +
            "cart_sum DOUBLE NOT NULL, " +
            "size TEXT)";

    public static final String TABLE_INVOICE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_invoice (" +
            "invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "TEN TEXT REFERENCES KHACH_HANG(TEN)," +
            "cart_id INTEGER REFERENCES tbl_cart(cart_id), " +
            "typeCoffee_typeName TEXT REFERENCES tbl_typeCoffee(typeCoffee_typeName)," +
            "cart_phone TEXT NOT NULL, " +
            "cart_address TEXT NOT NULL, " +
            "invoice_content TEXT NOT NULL, " +
            "invoice_sum DOUBLE NOT NULL, " +
            "invoice_status TEXT ," +
            "invoice_time TEXT NOT NULL)";
    public static final String TABLE_COMMENT_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_comment (" +
            "comment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "comment_content TEXT ," +
            "TEN TEXT REFERENCES KHACH_HANG(TEN)," +
            "coffee_id INTEGER REFERENCES tbl_coffee(coffee_id)," +
            "rating INTEGER " +
            ")";
//    public static final String TABLE_NOTI_CREATE = "CREATE TABLE IF NOT EXISTS " +
//            "tbl_noti (" +
//            "noti_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "noti_time TEXT NOT NULL, " +
//            "invoice_content TEXT REFERENCES tbl_invoice(invoice_content), " +
//            "invoice_status TEXT  REFERENCES tbl_invoice(invoice_status)," +
//            "user_name TEXT REFERENCES tbl_invoice(user_name))";

    public static final String insert_cmt = "Insert into tbl_comment(comment_content,TEN,coffee_id,rating) values" +
            "('ngon , tuyệt vời','canh','1','4'), ('nhất quán mình','hung','2','5'),('hết nước chấm','bac','1','5'),('xời, tuyệt vời','bao','2','3'),('mlem','vanh','2','5'),('okkkk','minhQuan','2','4')";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ADMIN);
//        sqLiteDatabase.execSQL(TABLE_REQUEST_CREATE);
        sqLiteDatabase.execSQL(CREATE_TABLE_KHACH_HANG);
        sqLiteDatabase.execSQL(TABLE_TYPE_CREATE);
        sqLiteDatabase.execSQL(TABLE_COFFEE_CREATE);
        sqLiteDatabase.execSQL(TABLE_CART_CREATE);
        sqLiteDatabase.execSQL(TABLE_INVOICE_CREATE);
        sqLiteDatabase.execSQL(TABLE_COMMENT_CREATE);
//        sqLiteDatabase.execSQL(TABLE_NOTI_CREATE);

        sqLiteDatabase.execSQL("INSERT INTO ADMIN VALUES (1,'ADMIN','Hoanghung200603@gmail.com','adm123')");

        sqLiteDatabase.execSQL("INSERT INTO KHACH_HANG VALUES (1,'hoanghung','0334432774','Hung@gmail.com','hung123')" +
                ",(2,'vanthanh','0334432775','Thanh@gmail.com','thanh123')" +
                ",(3,'vanduy','0334432776','Duy@gmail.com','duy123')");

        sqLiteDatabase.execSQL("INSERT INTO tbl_typeCoffee VALUES (1,'Cappuccino')");
        sqLiteDatabase.execSQL("INSERT INTO tbl_typeCoffee VALUES (2,'Macchiato')");
        sqLiteDatabase.execSQL("INSERT INTO tbl_typeCoffee VALUES (3,'Latte')");

        sqLiteDatabase.execSQL("INSERT INTO tbl_coffee(coffee_name,typeCoffee_typeName,coffee_img,coffee_description,coffee_price,size) VALUES " +
                "('Cà phê đen','Cappuccino', 'https://tse2.mm.bing.net/th?id=OIP.CuzM_3UkIJYnBCdzISeM2AHaHa&pid=Api&P=0&h=220','có hương vị đậm đà, đắng nhẹ và mạnh mẽ..',35000,'M')," +
                "('Cà phê sữa','Macchiato', 'https://tse1.mm.bing.net/th?id=OIP.ezMOz8pVQ0RuXpZaaEkdjgAAAA&pid=Api&P=0&h=220','Cà phê sữa là sự kết hợp hài hòa giữa cà phê robusta đậm đà và sữa đặc ngọt.',40000,'L')," +
                "('Cà phê phin','Macchiato', 'https://tse3.mm.bing.net/th?id=OIP.a_ek_9azxFe6dR09jItupQHaHa&pid=Api&P=0&h=220','Có hương vị đậm đà và thơm ngon.',40000,'L')," +
                "('Cà phê trứng','Macchiato', 'https://tse3.mm.bing.net/th?id=OIP.UzsPHHCQu80mNigt6povBQHaHE&pid=Api&P=0&h=220','Một loại cà phê đặc biệt của Hà Nội, gồm cà phê đen pha qua phin được phủ lên bởi một lớp kem trứng xốp.',40000,'L')," +
                "('Cà phê bạc xỉu','Macchiato', 'https://tse2.mm.bing.net/th?id=OIP.xYqI-RkLi3UaRXbtypn37wHaHa&pid=Api&P=0&h=220','Một biến thể của cà phê sữa, nhưng được thêm một lượng nhỏ sữa đặc và ít đường.',30000,'S')," +
                "('Cà phê đá','Macchiato', 'https://tse3.mm.bing.net/th?id=OIP.QC_QREHrKgaY3zktm25GewHaHa&pid=Api&P=0&h=220','Cà phê đen pha qua phin sau đó được pha loãng và phục vụ với đá.',40000,'L')," +
                "('Cà phê nâu đá','Macchiato', 'https://tse4.mm.bing.net/th?id=OIP.KHFjqd1UaE5MrlH8rE2RWQHaHa&pid=Api&P=0&h=220','Cà phê đen pha qua phin được pha loãng với sữa đặc và đá.',35000,'M')," +
                "('Cà phê sữa đá','Macchiato', 'https://tse4.mm.bing.net/th?id=OIP.tjs2Kdx1L1N5AJqaFiFMAwHaJ4&pid=Api&P=0&h=220','Cà phê đen pha qua phin được pha loãng với sữa và đá.',30000,'S')," +
                "('Cà phê bạc hà đá','Macchiato', 'https://tse3.mm.bing.net/th?id=OIP.Sf3M-3P08pfEUslPYNN3swHaHa&pid=Api&P=0&h=220','Một biến thể của cà phê sữa đá, được thêm một ít lá bạc hà tươi để tạo hương vị thơm mát.',40000,'L')," +
                "('Cà phê cốt dừa','Macchiato', 'https://tse1.mm.bing.net/th?id=OIP.J4QpGUuLW9oBrA4_XgrDwAHaHa&pid=Api&P=0&h=220','Cà phê đen pha qua phin được pha loãng với nước cốt dừa và đá.',40000,'L')");

        sqLiteDatabase.execSQL(insert_cmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists ADMIN");
        db.execSQL("drop table if exists KHACH_HANG");
        db.execSQL("DROP TABLE IF EXISTS tbl_request");
        db.execSQL("DROP TABLE IF EXISTS tbl_coffee");
        db.execSQL("DROP TABLE IF EXISTS tbl_cart");
        db.execSQL("DROP TABLE IF EXISTS tbl_invoice");

        onCreate(db);
    }
}
