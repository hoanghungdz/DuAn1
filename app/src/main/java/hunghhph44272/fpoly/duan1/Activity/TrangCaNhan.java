package hunghhph44272.fpoly.duan1.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hunghhph44272.fpoly.duan1.DangNhap;
import hunghhph44272.fpoly.duan1.LichSuDonHang_KH.LichSuDonHang;
import hunghhph44272.fpoly.duan1.R;

public class TrangCaNhan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);

        // Tìm LinearLayout cho nút đăng xuất
        LinearLayout lnDangXuat = findViewById(R.id.ln_dangxuat);
        LinearLayout lnThayDoiMatKhau = findViewById(R.id.ln_thaydoimatkhau);
        LinearLayout lnLienHeAdmin = findViewById(R.id.ln_lienhe);
        LinearLayout lnThongTinKh = findViewById(R.id.ln_thongtincanhan);
        LinearLayout lnLSDonHang = findViewById(R.id.ln_lich_su_don_hang);

        ImageView btnBack = findViewById(R.id.backBtn);

        // Thiết lập sự kiện lắng nghe cho nút đăng xuất
        lnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại xác nhận trước khi đăng xuất
                showLogoutConfirmationDialog();
            }
        });
        lnThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(TrangCaNhan.this,ThayDoiMatKhauKH.class);
               startActivity(intent);
            }
        });
        lnLienHeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan.this,LienHeAdmin.class);
                startActivity(intent);
            }
        });
        lnThongTinKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangCaNhan.this,ThongTinKhachHang.class);
                startActivity(intent);
            }
        });
        lnLSDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan.this, LichSuDonHang.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    // Phương thức hiển thị hộp thoại xác nhận đăng xuất
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");

        // Nút xác nhận
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Gọi hàm xử lý đăng xuất
                performLogout();
            }
        });

        // Nút hủy bỏ
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng hộp thoại nếu người dùng chọn hủy
                dialog.dismiss();
            }
        });

        // Hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Hàm xử lý đăng xuất
    private void performLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ISLOGIN",false);
        editor.apply();
        // Thực hiện các bước đăng xuất ở đây
        // Ví dụ: Chuyển hướng đến màn hình đăng nhập
        Intent intent = new Intent(TrangCaNhan.this, DangNhap.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

        // Kết thúc Activity hiện tại (nếu muốn)
        finish();

        // Hiển thị thông báo đăng xuất thành công
        showToast("Bạn đã đăng xuất thành công!");
    }

    // Phương thức hiển thị Toast
    private void showToast(String message) {
        // Hiển thị thông báo ngắn với nội dung được truyền vào
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
