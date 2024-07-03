package hunghhph44272.fpoly.duan1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.ChiTietCoffee;
import hunghhph44272.fpoly.duan1.DAO.CoffeeDAO;
import hunghhph44272.fpoly.duan1.DAO.GioHang_Dao;
import hunghhph44272.fpoly.duan1.Model.Coffee;
import hunghhph44272.fpoly.duan1.Model.Gio_Hang;
import hunghhph44272.fpoly.duan1.R;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Coffee> list;
//    private ArrayList<HashMap<String, Object>> listHM;
    private CoffeeDAO coffeeDAO;

    public CoffeeAdapter(Context context, ArrayList<Coffee> list, CoffeeDAO coffeeDAO) {
        this.context = context;
        this.list = list;
        this.coffeeDAO = coffeeDAO;
    }

    public void setData(ArrayList<Coffee> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recylet_coffee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Coffee coffee = list.get(position);
        holder.tv_name.setText(list.get(position).getName());
        String img = list.get(position).getImg();
        Picasso.get().load(img).into(holder.iv_img);
        holder.tv_des.setText(coffee.getDes());
        holder.tv_price.setText(String.valueOf(list.get(position).getPrice()) + " VND");
        holder.tv_size.setText(coffee.getSize());
        holder.tv_type.setText(coffee.getType());
        holder.btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_item_them_cart);

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnSubmit, btnCancel;
                btnSubmit = dialog.findViewById(R.id.btn_dialog_item_add_cart);
                btnCancel = dialog.findViewById(R.id.btn_dialog_item_cancel_cart);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GioHang_Dao gioHang_dao = new GioHang_Dao(context);
                        Gio_Hang gio_hang = new Gio_Hang();
                        gio_hang.setIdCoffee(coffee.getId());
                        gio_hang.setQuanti(1);
                        gio_hang.setSum(coffee.getPrice());
                        gio_hang.setSize(coffee.getSize());
                        gio_hang.setLoai(coffee.getType());
                        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                        String usernameLogged = sharedPreferences.getString("USERNAME", "");
                        gio_hang.setUsername(usernameLogged);
                        if (!gioHang_dao.FoodExists(gio_hang.getIdCoffee(), gio_hang.getUsername())) {
                            if (gioHang_dao.insert(gio_hang) > 0) {
                                Toast.makeText(context, "đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "không Thêm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(context, "Món ăn đã được chọn", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick (View v){
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChiTietCoffee.class);
                i.putExtra("coffeeImg", list.get(position).getImg());
                i.putExtra("coffeeName", list.get(position).getName());
                i.putExtra("coffeeDes", list.get(position).getDes());
                i.putExtra("coffeePrice", list.get(position).getPrice());
                i.putExtra("coffeeSize", list.get(position).getSize());
                i.putExtra("coffeeId",list.get(position).getId());
                i.putExtra("coffeeType",list.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name, tv_price, tv_des,tv_size,tv_type;
        ImageView btn_addCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_coffee_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_coffee_foodName);
            tv_price = itemView.findViewById(R.id.tv_item_coffee_foodPrice);
            btn_addCart = itemView.findViewById(R.id.btn_item_coffee_addCart);
            tv_des = itemView.findViewById(R.id.tv_item_coffee_des);
            tv_size = itemView.findViewById(R.id.tv_item_coffee_foodsize);
            tv_type = itemView.findViewById(R.id.tv_item_coffee_foodType);
        }
    }


}
