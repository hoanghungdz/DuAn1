package hunghhph44272.fpoly.duan1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import hunghhph44272.fpoly.duan1.Adapter.TrangThai_adapter;
import hunghhph44272.fpoly.duan1.R;


public class QuanLyTrangThaiFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 vpstatus;
    private TrangThai_adapter quanLy_trangThai_adapter;
    public QuanLyTrangThaiFragment() {
        // Required empty public constructor
    }

    public static QuanLyTrangThaiFragment newInstance(String param1, String param2) {
        QuanLyTrangThaiFragment fragment = new QuanLyTrangThaiFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout= view.findViewById(R.id.tab_layout_status);
        vpstatus=view.findViewById(R.id.vp_status);
        quanLy_trangThai_adapter=new TrangThai_adapter(getActivity());
        vpstatus.setAdapter(quanLy_trangThai_adapter);
        new TabLayoutMediator(tabLayout,vpstatus,((tab, position) ->{
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_trang_thai, container, false);
    }
}