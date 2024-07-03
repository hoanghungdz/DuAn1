package hunghhph44272.fpoly.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        ImageView ivlogo = findViewById(R.id.ivlogo);

        Glide.with(this).load(R.mipmap.tenorgif).into(ivlogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChao.this,DangNhap.class));
            }
        },3000);
    }
}