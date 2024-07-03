package hunghhph44272.fpoly.duan1.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import hunghhph44272.fpoly.duan1.Adapter.CoffeeAD_Adapter;
import hunghhph44272.fpoly.duan1.DAO.CoffeeAD_DAO;
import hunghhph44272.fpoly.duan1.DAO.LoaiCoffeeDAO;
import hunghhph44272.fpoly.duan1.Model.CoffeeAD;
import hunghhph44272.fpoly.duan1.Model.LoaiCoffee;
import hunghhph44272.fpoly.duan1.R;


public class QuanLyCoffee_Fragment extends Fragment {
    CoffeeAD_DAO coffeeDAO;
    RecyclerView recyclerCoffee;
    SearchView searchView;
    CoffeeAD_Adapter adapter;

    ArrayList<LoaiCoffee>llist;
    ArrayList<CoffeeAD> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly__coffee_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerCoffee = view.findViewById(R.id.recyclerCoffee1);
        ImageButton img_tapSearch = view.findViewById(R.id.btn_fragment_listFood_tapSearch_ad);
        EditText edSearch = view.findViewById(R.id.ed_fragment_listFood_search_ad);
        ImageButton imgAdd = view.findViewById(R.id.floadAdd);

        adapter = new CoffeeAD_Adapter(getContext(), list, coffeeDAO); // Thêm dòng này

        img_tapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSearch.length() > 0) {
                    String searchName = edSearch.getText().toString().trim();
                    LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerCoffee.setLayoutManager(layoutManager);
                    CoffeeAD_DAO food_dao1 = new CoffeeAD_DAO(getContext());
                    list = new ArrayList<>();
                    list = food_dao1.search(searchName);
                    adapter.setData(list);
                    recyclerCoffee.setAdapter(adapter);
                } else {
                    reloadData();
                }
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                LoaiCoffeeDAO loaiCoffeeDAO = new LoaiCoffeeDAO(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_coffee);
                CoffeeAD food = new CoffeeAD();
                EditText ed_listfood_img,ed_listfood_name,ed_listfood_price,ed_listfood_des,ed_listfood_size;
                Spinner spn = dialog.findViewById(R.id.spn_dialog_listfood_add_type);
                Button btnDialogAddSubmit;
                ed_listfood_img = dialog.findViewById(R.id.edt_dialog_listfood_add_img);
                ed_listfood_name = dialog.findViewById(R.id.edt_dialog_listfood_add_name);
                ed_listfood_price = dialog.findViewById(R.id.edt_dialog_listfood_add_price);
                ed_listfood_des = dialog.findViewById(R.id.edt_dialog_listfood_add_des);
                ed_listfood_size = dialog.findViewById(R.id.edt_dialog_listfood_add_size);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_listfood_add_add);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, LoaiCoffeeDAO.name());
                spn.setAdapter(adapter1);
                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        llist = LoaiCoffeeDAO.getAllData();
                        food.setType((llist.get(position).getTenLoai()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String img = ed_listfood_img.getText().toString();
                        String name = ed_listfood_name.getText().toString();
                        String priceString = ed_listfood_price.getText().toString();
                        String des = ed_listfood_des.getText().toString();
                        String size = ed_listfood_size.getText().toString();

                        if (img.trim().isEmpty() || name.trim().isEmpty() || size.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else if (priceString.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng nhập giá tiền", Toast.LENGTH_SHORT).show();
                        } else {
                            int price = 0;
                            try {
                                price = Integer.parseInt(priceString);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getContext(), "Giá tiền phải là một số", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            food.setImg(img);
                            food.setName(name);
                            food.setPrice(price);
                            food.setDes(des);
                            food.setSize(size);

                            if (coffeeDAO.insert(food) >= 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                                list = coffeeDAO.getAllData();
                                adapter.setData(list);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        reloadData();
    }

    private void reloadData(){
        coffeeDAO = new CoffeeAD_DAO(getContext());
        list = coffeeDAO.getAllData();
        Collections.reverse(list);
        adapter = new CoffeeAD_Adapter(getContext(),list,coffeeDAO);
        adapter.setData(list);
        recyclerCoffee.setAdapter(adapter);
        recyclerCoffee.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}