package hunghhph44272.fpoly.duan1.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import hunghhph44272.fpoly.duan1.DAO.KhachHang_Dao;
import hunghhph44272.fpoly.duan1.DangNhap;
import hunghhph44272.fpoly.duan1.Model.KhachHang;
import hunghhph44272.fpoly.duan1.R;

public class ThongTinKhachHang extends AppCompatActivity {

    TextView txtusername,txtphone,txtemail;

    ImageView imgAvatar;

    KhachHang_Dao khachHangDao;

    SharedPreferences sharedPreferences;

    String usedId;

    Button btn_suaCanhan,btn_quaylaiCaNhan;

    KhachHang khachHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        txtusername = findViewById(R.id.txt_username);
        txtphone = findViewById(R.id.txt_phone);
        txtemail = findViewById(R.id.txt_email);
        btn_suaCanhan = findViewById(R.id.btn_suaCanhan);
        btn_quaylaiCaNhan = findViewById(R.id.btn_quaylaiCanhan);
        imgAvatar = findViewById(R.id.img_avatar);
        khachHangDao = new KhachHang_Dao(this);
        sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String tendnhap = sharedPreferences.getString("TEN","");
        String mkdnhap = sharedPreferences.getString("MATKHAU","");
        usedId = sharedPreferences.getString("USER_ID","");
        KhachHang khachHang = khachHangDao.LayThongTinKH(tendnhap,mkdnhap);

        txtusername.setText("Họ Tên : " + khachHang.getTEN_KH());
        txtphone.setText("Số điện thoại: "+ khachHang.getSDT());
        txtemail.setText("Email : " + khachHang.getEMAIL_KH());


        String avatarPath = sharedPreferences.getString("AVATAR_PATH","");
        if (!avatarPath.isEmpty()){
            Glide.with(this).load(Uri.parse(avatarPath)).into(imgAvatar);
        }

        btn_suaCanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDialog(khachHang);

            }
        });
        btn_quaylaiCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void changeAvatar(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            if (selectedImage != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AVATAR_PATH", selectedImage.toString());
                editor.apply();
                Toast.makeText(this, "Thêm ảnh thành công !!!", Toast.LENGTH_SHORT).show();
                Glide.with(this).load(selectedImage).into(imgAvatar);
            } else {
                Toast.makeText(this, "Không thể nào lấy ảnh, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void switchAccount(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("AVATAR_PATH");
        editor.apply();

        Intent intent = new Intent(this, DangNhap.class);
        startActivity(intent);
        finish();
    }

    private void updateDialog(KhachHang khachHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thongtinkh, null);
        builder.setView(view);
        final Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenKH, edtSoDienThoai, edtEmail;
        Button btnLuuThongTin, btnHuy;

//        edtTenKH = view.findViewById(R.id.edtTenKH);
        edtSoDienThoai = view.findViewById(R.id.edtSoDienThoai);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnLuuThongTin = view.findViewById(R.id.btnLuuThongTin);
        btnHuy = view.findViewById(R.id.btnHuy);

//        edtTenKH.setText(khachHang.getTEN_KH());
        edtSoDienThoai.setText(khachHang.getSDT());
        edtEmail.setText(khachHang.getEMAIL_KH());

        btnLuuThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String tenkh = edtTenKH.getText().toString().trim();
                String emailkh = edtEmail.getText().toString().trim();
                String sdtkh = edtSoDienThoai.getText().toString().trim();

                if (sdtkh.isEmpty() || emailkh.isEmpty()) {
                    Toast.makeText(ThongTinKhachHang.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cập nhật thông tin khách hàng
//                khachHang.setTEN_KH(tenkh);
                khachHang.setEMAIL_KH(emailkh);
                khachHang.setSDT(sdtkh);

                if (khachHangDao.UpdateThongTinKH(khachHang) > 0) {
                    dialog.dismiss();
                    Toast.makeText(ThongTinKhachHang.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                    // Cập nhật các TextView hiển thị thông tin khách hàng
//                    txtusername.setText(tenkh);
                    txtphone.setText(sdtkh);
                    txtemail.setText(emailkh);
                    
                } else {
                    Toast.makeText(ThongTinKhachHang.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}