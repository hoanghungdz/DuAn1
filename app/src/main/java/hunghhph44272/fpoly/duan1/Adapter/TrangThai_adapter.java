package hunghhph44272.fpoly.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hunghhph44272.fpoly.duan1.Fragment.DaThanhToanFragment;
import hunghhph44272.fpoly.duan1.Fragment.DangChuanBiHangFragment;
import hunghhph44272.fpoly.duan1.Fragment.DangGiao_Fragment;
import hunghhph44272.fpoly.duan1.Fragment.LichSuDonHangFragment;


public class TrangThai_adapter extends FragmentStateAdapter {
    public TrangThai_adapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LichSuDonHangFragment();
            case 1:
                return new DangChuanBiHangFragment();
            case 2:
                return new DangGiao_Fragment();
            case 3:
                return new DaThanhToanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
