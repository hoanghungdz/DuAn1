package hunghhph44272.fpoly.duan1.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hunghhph44272.fpoly.duan1.DAO.CoffeeAD_DAO;
import hunghhph44272.fpoly.duan1.DAO.GioHang_Dao;
import hunghhph44272.fpoly.duan1.Model.CoffeeAD;
import hunghhph44272.fpoly.duan1.Model.Gio_Hang;
import hunghhph44272.fpoly.duan1.R;

public class GioHang_Adapter extends RecyclerView.Adapter<GioHang_Adapter.ViewHolder> {
    Context context;
    List<Gio_Hang> list;
    GioHang_Dao gioHang_dao;
    CoffeeAD_DAO dao;

    private OnQuantityUpClickListener quantityUpClickListener;
    private OnQuantityDownClickListener quantityDownClickListener;

    public GioHang_Adapter(Context context, ArrayList<Gio_Hang> list, GioHang_Dao gioHang_dao) {
        this.context = context;
        this.list = list;
        this.gioHang_dao = gioHang_dao;
        this.dao = new CoffeeAD_DAO(context);
    }

    public void setData(ArrayList<Gio_Hang> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnQuantityUpClickListener(OnQuantityUpClickListener listener) {
        this.quantityUpClickListener = listener;
    }

    public void setOnQuantityDownClickListener(OnQuantityDownClickListener listener) {
        this.quantityDownClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang_kh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gio_Hang gio_hang = list.get(position);
        CoffeeAD coffeeAD = dao.getById(gio_hang.getIdCoffee());

        if (coffeeAD != null) {
            holder.tv_name.setText(coffeeAD.getName());
            Picasso.get().load(coffeeAD.getImg()).into(holder.iv_img);

            holder.tv_price.setText(String.valueOf(coffeeAD.getPrice()));
            holder.tv_size.setText("Size: "+ coffeeAD.getSize());
            holder.tv_type.setText("Loại: "+ coffeeAD.getType());
        }

        holder.tv_price.setText("Giá: "+ String.valueOf(coffeeAD.getPrice() * gio_hang.getQuanti()) + " VND");
        holder.tv_quanti.setText(String.valueOf(gio_hang.getQuanti()));
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quatity = gio_hang.getQuanti() + 1;
                holder.tv_quanti.setText(String.valueOf(quatity));
                holder.tv_price.setText(String.valueOf(coffeeAD.getPrice() * quatity) + "VND");
                gio_hang.setQuanti(quatity);
                gio_hang.setSum(coffeeAD.getPrice() * quatity);
                gioHang_dao.updateSum(gio_hang);

                if (quantityUpClickListener != null) {
                    quantityUpClickListener.onQuantityUpClick(holder.getAdapterPosition());
                }
            }
        });

        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = gio_hang.getQuanti()  ;
                if (quantity >1){
                    quantity -=1;
                    holder.tv_quanti.setText(String.valueOf(quantity));
                    holder.tv_price.setText(String.valueOf(coffeeAD.getPrice() * quantity) + "VND");
                    gio_hang.setQuanti(quantity);
                    gio_hang.setSum(coffeeAD.getPrice() * quantity);
                    gioHang_dao.updateSum(gio_hang);


                    if (quantityDownClickListener != null){
                        quantityDownClickListener.onQuantityDownClick(holder.getAdapterPosition());
                    }else {
                        Toast.makeText(context, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout_foreground;
        ImageView iv_img;
        TextView tv_name, tv_des, tv_price, tv_quanti,tv_size,tv_type;
        ImageView btn_up, btn_down, btn_del;
        //CardView layout_foreground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_cart_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_cart_foodName);
            tv_price = itemView.findViewById(R.id.tv_item_cart_foodPrice);
            tv_quanti = itemView.findViewById(R.id.tv_item_cart_quantity);
            tv_size = itemView.findViewById(R.id.tv_item_cart_foodsize);
            tv_type = itemView.findViewById(R.id.tv_item_cart_foodType);
            btn_up = itemView.findViewById(R.id.btn_item_cart_quantity_up);
            btn_down = itemView.findViewById(R.id.btn_item_cart_quantity_down);
            btn_del = itemView.findViewById(R.id.btn_item_cart_delete);
            layout_foreground = itemView.findViewById(R.id.layout_foreground);
        }
    }

    public interface OnQuantityUpClickListener {
        void onQuantityUpClick(int position);

        void onSwiped(RecyclerView.ViewHolder viewHolder);
    }

    public interface OnQuantityDownClickListener {
        void onQuantityDownClick(int position);
    }
}
