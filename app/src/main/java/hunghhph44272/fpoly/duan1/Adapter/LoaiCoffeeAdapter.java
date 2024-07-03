package hunghhph44272.fpoly.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.DAO.LoaiCoffeeDAO;
import hunghhph44272.fpoly.duan1.Model.LoaiCoffee;
import hunghhph44272.fpoly.duan1.R;

public class LoaiCoffeeAdapter extends BaseAdapter {

    private  ArrayList<LoaiCoffee> list;
    private  Context context;
    private LoaiCoffeeDAO loaiCoffeeDao;
    LoaiCoffee loaiCoffee;
    TextInputLayout ip_ten_loai;
    TextInputEditText ed_ten_loai;
    int check=0;
    int a=0;
    public LoaiCoffeeAdapter( ArrayList<LoaiCoffee> list, Context context1, LoaiCoffeeDAO loaiCoffeeDao) {
        this.list = list;
        this.context = context1;
        this.loaiCoffeeDao = loaiCoffeeDao;
    }

    public void setData(ArrayList<LoaiCoffee> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_recycler_loaicoffee, null);
            holder = new Holder();
            holder.txt_ten_loai_coffee= view.findViewById(R.id.item_name_loai_coffee);
            holder.delete_loai_coffee=view.findViewById(R.id.item_coffee_delete);
            holder.edit_loai_coffee=view.findViewById(R.id.item_coffee_edit);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        loaiCoffee = list.get(i);
        holder.txt_ten_loai_coffee.setText(loaiCoffee.getTenLoai());
        holder.delete_loai_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loaiCoffeeDao.delete(String.valueOf(loaiCoffee.getMaLoai()))>0){
                    Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaiCoffeeDao.getAll());
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.edit_loai_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.CENTER,i);

            }
        });
        return view;
    }
    public class Holder{
        TextView txt_ten_loai_coffee;
        ImageButton delete_loai_coffee,edit_loai_coffee;
    }
    private void openDialog(int gravity,int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_loai_coffee);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        TextView lcTile = (TextView) dialog.findViewById(R.id.title_loai_coffee);

        ed_ten_loai=dialog.findViewById(R.id.ed_name_loai_coffee);
        ip_ten_loai=dialog.findViewById(R.id.input_name_loai_coffee);

        Button btnadd = dialog.findViewById(R.id.btn_dialog_add_loai_coffee);
        Button btncancel = dialog.findViewById(R.id.btn_dialog_cancle_loai_coffee);

        loaiCoffeeDao = new LoaiCoffeeDAO(context);
        loaiCoffee = list.get(position);
        lcTile.setText("Sửa loại coffee");
        btnadd.setText("Sửa");
        btncancel.setText("Huỷ");
        ed_ten_loai.setText(loaiCoffee.getTenLoai());

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                if (check==0){
                    loaiCoffee.setTenLoai(ed_ten_loai.getText().toString());
                    if (loaiCoffeeDao.update(loaiCoffee)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(loaiCoffeeDao.getAll());
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Huỷ sửa", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
//

        dialog.show();
    }
    private void validate() {
        if (ed_ten_loai.getText().length() == 0) {
            ip_ten_loai.setError("Tên loại coffee không được để trống");
            check++;
        } else {
            ip_ten_loai.setError("");
        }
    }
}
