package hunghhph44272.fpoly.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import hunghhph44272.fpoly.duan1.DAO.KhachHang_Dao;
import hunghhph44272.fpoly.duan1.Model.KhachHang;

public class DangKy extends AppCompatActivity {
    TextInputEditText edtTaiKhoanDK,edtSdt, edtMl_sdtDK, edtMatKhauDK, edtMatKhauDK2;
    TextView txtchuyenDN;
    Button btnDangKi;
    KhachHang_Dao khachHangDao;
    KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtTaiKhoanDK = findViewById(R.id.edtTaiKhoanDK);
        edtMl_sdtDK = findViewById(R.id.edtMl_sdtDK);
        edtSdt = findViewById(R.id.edtsdtDK);
        edtMatKhauDK = findViewById(R.id.edtMatKhauDK);
        edtMatKhauDK2 = findViewById(R.id.edtMatKhauDK2);
        txtchuyenDN = findViewById(R.id.txtchuyenDN);
        btnDangKi = findViewById(R.id.btnDangKi);

        txtchuyenDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
            }
        });
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoanDK = edtTaiKhoanDK.getText().toString();
                String email = edtMl_sdtDK.getText().toString();
                String sdt = edtSdt.getText().toString();
                String mk_DK = edtMatKhauDK.getText().toString();
                int validationResult = validate();
                if (validationResult == 1) {
                    khachHangDao = new KhachHang_Dao(DangKy.this);
                    khachHang = new KhachHang();
                    khachHang.setTEN_KH(taikhoanDK);
                    khachHang.setEMAIL_KH(email);
                    khachHang.setSDT(sdt);
                    khachHang.setMAT_KHAU_KH(mk_DK);

                    long result = khachHangDao.insert(khachHang);
                    if (result > 0) {
                        Toast.makeText(DangKy.this, "Đăng kí thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangKy.this, DangNhap.class));
                    } else {
                        Toast.makeText(DangKy.this, "đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public int validate() {
        int check = 1;
        if (edtTaiKhoanDK.getText().length() == 0 || edtMl_sdtDK.getText().length() == 0 || edtMatKhauDK.getText().length() == 0 || edtMatKhauDK2.getText().length() == 0) {
            Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtMatKhauDK.getText().toString();
            String pass2 = edtMatKhauDK2.getText().toString();

            if (!pass.equals(pass2)) {
                Toast.makeText(this, "Mật khẩu bạn nhập không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }

            String phoneNumber = edtSdt.getText().toString();
            String email = edtMl_sdtDK.getText().toString();

            if (!isValidPhoneNumber(phoneNumber)) {
                Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!EmailPatternMatches(email, regexEmail)) {
                Toast.makeText(this, "Email không chính xác", Toast.LENGTH_SHORT).show();
                check = -1 ;
            }
            if (pass.length()<5){
                Toast.makeText(this, "Mật khẩu phải hơn 5 ký tự", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Sử dụng biểu thức chính quy để kiểm tra số điện thoại
        // Ở đây, chúng ta sử dụng Patterns của Android
        // để kiểm tra xem chuỗi có phải là số điện thoại hợp lệ không.
        return Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length() == 10;
    }
    public static boolean EmailPatternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}


