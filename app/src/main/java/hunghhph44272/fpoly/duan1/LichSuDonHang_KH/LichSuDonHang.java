package hunghhph44272.fpoly.duan1.LichSuDonHang_KH;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import hunghhph44272.fpoly.duan1.Adapter.QuanLy_LS_KH_Adapter;
import hunghhph44272.fpoly.duan1.R;

public class LichSuDonHang extends AppCompatActivity {
    private TabLayout tabLayoutstatus;
    private ViewPager2 vpstatus;
    private QuanLy_LS_KH_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);
        tabLayoutstatus= findViewById(R.id.tab_layout_Hitory);
        vpstatus=findViewById(R.id.vp_Hitory);
        adapter= new QuanLy_LS_KH_Adapter(this);
        vpstatus.setAdapter(adapter);
        new TabLayoutMediator(tabLayoutstatus, vpstatus,((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đã Đặt Hàng");
                    break;
                case 1:
                    tab.setText("Đang Chuẩn Bị Hàng");
                    break;
                case 2:
                    tab.setText("Đang Giao");
                    break;
                case 3:
                    tab.setText("Đã Thanh Toán");

                    break;
            }
        })).attach();
    }
}