package hunghhph44272.fpoly.duan1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Adapter.BinhLuan_Adapter;
import hunghhph44272.fpoly.duan1.DAO.BinhLuan_Dao;
import hunghhph44272.fpoly.duan1.DAO.GioHang_Dao;
import hunghhph44272.fpoly.duan1.Model.BinhLuan;
import hunghhph44272.fpoly.duan1.Model.Coffee;
import hunghhph44272.fpoly.duan1.Model.Gio_Hang;

public class ChiTietCoffee extends AppCompatActivity {
    Button btn_add;
    RecyclerView recyclerView;
    TextView count_cmt;
    BinhLuan_Adapter binhLuan_adapter;
    BinhLuan_Dao binhLuan_dao;
    ArrayList<BinhLuan> list;
    Coffee coffee;
    TextView tv_avgRating;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_coffee);
        int id_food=getIntent().getIntExtra("coffeeId", 0);
        String dataImage = getIntent().getStringExtra("coffeeImg");
        String dataName = getIntent().getStringExtra("coffeeName");
        String dataContent = getIntent().getStringExtra("coffeeDes");
        int dataPrice = getIntent().getIntExtra("coffeePrice", 0);
        String size = getIntent().getStringExtra("coffeeSize");
        String type = getIntent().getStringExtra("coffeeType");

        tv_avgRating=findViewById(R.id.AVG_rating);
        ImageButton btn_back = findViewById(R.id.btn_infor_food_back);
        btn_add=findViewById(R.id.btn_add_cart);
        recyclerView=findViewById(R.id.recy_comment);
        count_cmt=findViewById(R.id.tv_count_comment);
        binhLuan_dao=new BinhLuan_Dao(getApplicationContext());
        list=binhLuan_dao.getByFoodId(String.valueOf(id_food));
        binhLuan_adapter=new BinhLuan_Adapter(ChiTietCoffee.this,list);
        recyclerView.setAdapter(binhLuan_adapter);
        count_cmt.setText("("+binhLuan_dao.countCmt(String.valueOf(id_food))+")");
        float rating_avg=binhLuan_dao.getAVG(String.valueOf(id_food));
        tv_avgRating.setText(String.format("%.1f",rating_avg)+"/5");

        ImageView iv_image = findViewById(R.id.iv_infor_food_img);
        TextView tv_name = findViewById(R.id.tv_infor_food_name);
        TextView tv_content = findViewById(R.id.tv_infor_food_content);
        TextView tv_price = findViewById(R.id.tv_infor_food_price);
        TextView tv_size = findViewById(R.id.tv_infor_food_size);
        TextView tv_type = findViewById(R.id.tv_infor_food_type);



        Picasso.get().load(dataImage).into(iv_image);
        tv_name.setText(dataName);
        tv_content.setText(dataContent);
        tv_price.setText(String.valueOf(dataPrice)+" VND");
        tv_size.setText("Size: "+size);
        tv_type.setText("Loại: "+type);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang_Dao gioHang_dao = new GioHang_Dao(getApplication());

                // Tạo đối tượng mới để thêm vào giỏ hàng
                Gio_Hang gio_hang = new Gio_Hang();

                // Thiết lập thông tin cho mục hàng từ đối tượng MonAn được truyền vào
                gio_hang.setIdCoffee(id_food);
                gio_hang.setQuanti(1); // Đặt số lượng là 1 (có thể thay đổi nếu cần)
                gio_hang.setSum(dataPrice); // Đặt tổng giá tiền (có thể cần chỉnh sửa logic tính toán)

                // Lấy thông tin người dùng đang đăng nhập từ SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String usernameLogged = sharedPreferences.getString("USERNAME", "");

                // Đặt tên người dùng cho mục hàng
                gio_hang.setUsername(usernameLogged);

                // Kiểm tra xem món ăn đã tồn tại trong giỏ hàng của người dùng hay chưa
                if (!gioHang_dao.FoodExists(gio_hang.getIdCoffee(), gio_hang.getUsername())) {
                    // Nếu món ăn chưa tồn tại trong giỏ hàng, thêm nó vào
                    if (gioHang_dao.insert(gio_hang) > 0) {
                        // Thông báo thành công và có thể thực hiện các hành động khác sau khi thêm vào giỏ hàng
                        Toast.makeText(ChiTietCoffee.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                        // Ví dụ: có thể đóng activity hoặc cập nhật giao diện nếu cần
                    } else {
                        // Thông báo thất bại nếu không thể thêm vào cơ sở dữ liệu
                        Toast.makeText(ChiTietCoffee.this, "Không thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Thông báo nếu món ăn đã được chọn trước đó
                    Toast.makeText(ChiTietCoffee.this, "Món ăn đã được chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}