package hunghhph44272.fpoly.duan1.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hunghhph44272.fpoly.duan1.R;

public class DatHangThanhCong extends AppCompatActivity {
    TextView tv_address;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang_thanh_cong);
        tv_address=findViewById(R.id.tv_confirm_address);
        ivBack=findViewById(R.id.iv_confirm_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        String DataAddress =getIntent().getStringExtra("address");
//        tv_address.setText(DataAddress);
    }

}