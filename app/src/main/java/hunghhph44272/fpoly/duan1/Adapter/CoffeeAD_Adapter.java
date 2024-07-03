package hunghhph44272.fpoly.duan1.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.DAO.CoffeeAD_DAO;
import hunghhph44272.fpoly.duan1.DAO.LoaiCoffeeDAO;
import hunghhph44272.fpoly.duan1.Model.CoffeeAD;
import hunghhph44272.fpoly.duan1.Model.LoaiCoffee;
import hunghhph44272.fpoly.duan1.R;

public class CoffeeAD_Adapter extends RecyclerView.Adapter<CoffeeAD_Adapter.ViewHoler> {

    Context context;
    private ArrayList<CoffeeAD> list;
    private CoffeeAD_DAO dao;

    public CoffeeAD_Adapter(Context context, ArrayList<CoffeeAD> list, CoffeeAD_DAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    public void setData(ArrayList<CoffeeAD> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CoffeeAD_Adapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_admin_coffee,null);
        return new CoffeeAD_Adapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name.setText(list.get(position).getName());
        String img=list.get(position).getImg();
        Picasso.get().load(img).into(holder.iv_img);
        holder.tv_des.setText(list.get(position).getDes());
        holder.tv_type.setText(list.get(position).getType());
        holder.tv_size.setText(list.get(position).getSize());
        holder.tv_price.setText(String.valueOf(list.get(position).getPrice())+" VND");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_popup_edit_delete, builder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, v);
                builder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_edit) {
                            // viết code update có tể sử lý qua hàm ở bên dưới và gán hàm vào đây
                            updateDia(list.get(position),position);
                            return true;
                        } else if (item.getItemId() == R.id.option_delete) {
                            //viết code delete
                            showDele(list.get(position).getId());
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });

                optionmenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name, tv_des, tv_price,tv_size,tv_type;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_listFood_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_listFood_foodName);
            tv_des = itemView.findViewById(R.id.tv_item_listFood_foodContent);
            tv_price = itemView.findViewById(R.id.tv_item_listFood_foodPrice);
            tv_size = itemView.findViewById(R.id.tv_item_listFood_foodSize);
            tv_type = itemView.findViewById(R.id.tv_item_listFood_foodType);
        }
    }

    public void showDele(int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_invoice);
        TextView content = dialog.findViewById(R.id.tv_dialog_delete);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);
        content.setText("Bạn có muốn xoá không ?");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoffeeAD_DAO dao = new CoffeeAD_DAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    private void updateDia(CoffeeAD coffee, int id) {
        Dialog dialog = new Dialog(context);
        CoffeeAD_DAO foodDAO = new CoffeeAD_DAO(context);
        LoaiCoffeeDAO loaiCoffeeDAO = new LoaiCoffeeDAO(context);
        dialog.setContentView(R.layout.dialog_listcoffee_update);

        EditText ed_listcoffee_img,ed_listcoffee_name,ed_listcoffee_price,ed_listcoffee_des,ed_listcoffee_size;
        Button btnDialogAddCancel, btnDialogAddSubmit;
        Spinner spn = dialog.findViewById(R.id.spn_dialog_listcoffee_update_type);
        ed_listcoffee_img = dialog.findViewById(R.id.edt_dialog_listcoffee_update_img);
        ed_listcoffee_name = dialog.findViewById(R.id.edt_dialog_listcoffee_update_name);
        ed_listcoffee_price = dialog.findViewById(R.id.edt_dialog_listcoffee_update_price);
        ed_listcoffee_des = dialog.findViewById(R.id.edt_dialog_listcoffee_update_des);
        ed_listcoffee_size = dialog.findViewById(R.id.edt_dialog_listcoffee_update_size);


        ed_listcoffee_img.setText(list.get(id).getImg());
        ed_listcoffee_name.setText(list.get(id).getName());
        ed_listcoffee_price.setText(String.valueOf(list.get(id).getPrice()));
        ed_listcoffee_des.setText(list.get(id).getDes());
        ed_listcoffee_size.setText(list.get(id).getSize());


        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_listfood_update_add);
        ArrayList<LoaiCoffee> listLoai = loaiCoffeeDAO.getAllData();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, loaiCoffeeDAO.name());
        spn.setAdapter(adapter1);
        int spIndex = 0;
        for (LoaiCoffee type : listLoai) {
            if (type.getTenLoai().equals(coffee.getType())) {
                spn.setSelection(spIndex);
                break;
            }
            spIndex++;
        }
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String i = listLoai.get(position).getTenLoai();
                coffee.setType(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoffeeAD_DAO coffeeDAO1 = new CoffeeAD_DAO(context);
                String img = ed_listcoffee_img.getText().toString();
                String name = ed_listcoffee_name.getText().toString();
                String priceString = ed_listcoffee_price.getText().toString();
                String des = ed_listcoffee_des.getText().toString();
                String size = ed_listcoffee_size.getText().toString();

                if (img.trim().isEmpty() || name.trim().isEmpty() || priceString.trim().isEmpty() || des.trim().isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!priceString.matches("\\d+")) {
                    Toast.makeText(context, "Giá tiền phải là một số", Toast.LENGTH_SHORT).show();
                } else {
                    int price = Integer.parseInt(priceString);
                    coffee.setImg(img);
                    coffee.setName(name);
                    coffee.setPrice(price);
                    coffee.setDes(des);
                    coffee.setSize(size);

                    if (coffeeDAO1.update(coffee) > 0) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                        list = coffeeDAO1.getAllData();
                        setData(list);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_LONG).show();
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
}
