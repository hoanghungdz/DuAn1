package hunghhph44272.fpoly.duan1.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hunghhph44272.fpoly.duan1.R;

public class ChiTietDatHangMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dat_hang_main);
        int id_cart = getIntent().getIntExtra("id_cart", 0);
        int phone = getIntent().getIntExtra("phone", 0);
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String time = getIntent().getStringExtra("time");
        String content = getIntent().getStringExtra("content");
        String status = getIntent().getStringExtra("status");
        double sum = getIntent().getDoubleExtra("sum", 0);
        TextView tv_cart = findViewById(R.id.infor_id_cart);
        TextView tv_phone = findViewById(R.id.infor_id_phone);
        TextView tv_name = findViewById(R.id.infor_id_hoten);
        TextView tv_address = findViewById(R.id.infor_id_address);
        TextView tv_Time = findViewById(R.id.infor_id_time);
        TextView tv_content = findViewById(R.id.infor_id_noidung);
        TextView tv_status = findViewById(R.id.info_status);
        TextView tv_sum = findViewById(R.id.infor_id_sum);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_cart.setText(String.valueOf(id_cart));
        tv_phone.setText(String.valueOf(phone));
        tv_name.setText(name);
        tv_address.setText(address);
        tv_Time.setText(time);
        tv_content.setText(content);
        tv_status.setText(status);
        tv_sum.setText(String.valueOf(sum));
    }
}