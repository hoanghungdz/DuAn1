package hunghhph44272.fpoly.duan1.LichSuDonHang_KH;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import hunghhph44272.fpoly.duan1.DAO.HoaDonKH_DAO;
import hunghhph44272.fpoly.duan1.Model.HoaDonKH;
import hunghhph44272.fpoly.duan1.R;

public class LichSuKH_Fragment extends Fragment {
    private HoaDonKH_DAO dao;
    private ArrayList<HoaDonKH> list;

    private HoaDonKH_Adapter adapter;
    RecyclerView recyclerView;

    public LichSuKH_Fragment() {
    }


    public static LichSuKH_Fragment newInstance(String param1, String param2) {
        LichSuKH_Fragment fragment = new LichSuKH_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_su_k_h_, container, false);
        recyclerView =view.findViewById(R.id.History_ry);
        reloadData();
        return view;

    }

    public void reloadData(){
        dao = new HoaDonKH_DAO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("TEN", "");
        list = dao.getByUser(loggedInUserName);
        Collections.reverse(list);
        adapter = new HoaDonKH_Adapter(list, getContext(), dao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}