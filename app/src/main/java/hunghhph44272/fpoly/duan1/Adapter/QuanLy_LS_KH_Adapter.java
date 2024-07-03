package hunghhph44272.fpoly.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hunghhph44272.fpoly.duan1.LichSuDonHang_KH.DaThanhToanKh_Fragment;
import hunghhph44272.fpoly.duan1.LichSuDonHang_KH.DangChuanBiHangKH_Fragment;
import hunghhph44272.fpoly.duan1.LichSuDonHang_KH.DangGiaoKH_Fragment;
import hunghhph44272.fpoly.duan1.LichSuDonHang_KH.LichSuKH_Fragment;

public class QuanLy_LS_KH_Adapter extends FragmentStateAdapter {
    public QuanLy_LS_KH_Adapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LichSuKH_Fragment();
            case 1:
                return new DangChuanBiHangKH_Fragment();
            case 2:
                return new DangGiaoKH_Fragment();
            case 3:
                return new DaThanhToanKh_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
