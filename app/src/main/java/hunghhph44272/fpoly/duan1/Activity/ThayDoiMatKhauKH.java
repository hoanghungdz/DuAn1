package hunghhph44272.fpoly.duan1.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hunghhph44272.fpoly.duan1.DAO.KhachHang_Dao;
import hunghhph44272.fpoly.duan1.R;

public class ThayDoiMatKhauKH extends AppCompatActivity {

    EditText edPassOld,edPassChange,edRePassChange;

    Button btnSaveUserChange,btnCancelUserChange;

    KhachHang_Dao khdao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau_kh);
        edPassChange = findViewById(R.id.edPassChange);
        edPassOld = findViewById(R.id.edPassOld);
        edRePassChange = findViewById(R.id.edRePassChange);
        btnSaveUserChange = findViewById(R.id.btnSaveUserChange);
        btnCancelUserChange = findViewById(R.id.btnCancelUserChange);
        khdao = new KhachHang_Dao(this);


        btnCancelUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkcu = edPassOld.getText().toString();
                String mkmoi = edPassChange.getText().toString();
                String mknhaplai = edRePassChange.getText().toString();
                if (mkcu.isEmpty() || mkmoi.isEmpty() || mknhaplai.isEmpty()){
                    Toast.makeText(ThayDoiMatKhauKH.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return ;
                }

                if (mkmoi.equals(mknhaplai)){
                    if (khdao.checkPasswordAndChange(mkcu,mkmoi)){
                        Toast.makeText(ThayDoiMatKhauKH.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassChange.setText("");
                        edRePassChange.setText("");
                        edPassOld.setFocusable(false);
                        edPassChange.setFocusable(false);
                        edRePassChange.setFocusable(false);
                        finish();

                    } else {
                        Toast.makeText(ThayDoiMatKhauKH.this, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ThayDoiMatKhauKH.this, "Mật khẩu nhập lại sai", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}