package hunghhph44272.fpoly.duan1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import hunghhph44272.fpoly.duan1.Activity.MainActivity;
import hunghhph44272.fpoly.duan1.DAO.Admin_Dao;
import hunghhph44272.fpoly.duan1.DAO.KhachHang_Dao;

public class DangNhap extends AppCompatActivity {
    TextInputEditText edtTaiKhoanDN, edtMatKhauDN;
    Button btnDangNhap;
    TextView txtchuyenDKi;
    KhachHang_Dao khachHangDao;

    CheckBox luuMKCheckBox;
    Admin_Dao adminDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTaiKhoanDN = findViewById(R.id.edtTaiKhoanDN);
        edtMatKhauDN = findViewById(R.id.edtMatKhauDN);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtchuyenDKi = findViewById(R.id.txtchuyenDKi);
        khachHangDao = new KhachHang_Dao(this);
        adminDao = new Admin_Dao(this);

        luuMKCheckBox = findViewById(R.id.LuuMK); //

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
        String saveTk = sharedPreferences.getString("TEN","");
        String saveMk = sharedPreferences.getString("MATKHAU","");
        if (!saveTk.isEmpty()&&!saveMk.isEmpty()){
            edtTaiKhoanDN.setText(saveTk);
            edtMatKhauDN.setText(saveMk);
            luuMKCheckBox.setChecked(true);
        }

        txtchuyenDKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk_DN = edtTaiKhoanDN.getText().toString();
                String mk_DN = edtMatKhauDN.getText().toString();


                if (tk_DN.isEmpty() || mk_DN.isEmpty()) {
                    Toast.makeText(DangNhap.this, "thiếu TK hoặc MK", Toast.LENGTH_SHORT).show();
                } else {
                    if (adminDao.checkLogin(tk_DN, mk_DN) == 1) {
                        XuLyDangNhap(tk_DN,mk_DN);
                        Intent intent = new Intent(DangNhap.this, Admin_MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(DangNhap.this, "Đăng nhập Tk admin ", Toast.LENGTH_SHORT).show();
                    } else if (khachHangDao.checkLogin(tk_DN, mk_DN) == 1) {
                        XuLyDangNhap(tk_DN,mk_DN);
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(DangNhap.this, "Đăng nhập TK Khach hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DangNhap.this, "Sai Tk hoặc Mk", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    private void XuLyDangNhap(String user , String pass){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (luuMKCheckBox.isChecked()){
            editor.putString("TEN",user);
            editor.putString("MATKHAU",pass);
            editor.apply();

        }else {
            editor.remove("TEN");
            editor.remove("MATKHAU");
            editor.apply();
        }
    }




}