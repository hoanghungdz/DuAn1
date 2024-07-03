package hunghhph44272.fpoly.duan1.LichSuDonHang_KH;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Activity.ChiTietDatHangMainActivity;
import hunghhph44272.fpoly.duan1.DAO.CoffeeAD_DAO;
import hunghhph44272.fpoly.duan1.DAO.HoaDonKH_DAO;
import hunghhph44272.fpoly.duan1.Model.HoaDonKH;
import hunghhph44272.fpoly.duan1.R;

public class HoaDonKH_Adapter extends RecyclerView.Adapter<HoaDonKH_Adapter.ViewHolder> {
    private ArrayList<HoaDonKH> list;
    private Context context;

    private HoaDonKH_DAO history_dao;

    CoffeeAD_DAO dao;

    public HoaDonKH_Adapter(ArrayList<HoaDonKH> list, Context context, HoaDonKH_DAO history_dao) {
        this.list = list;
        this.context = context;
        this.history_dao = history_dao;
        dao=new CoffeeAD_DAO(context);
    }

    public void setData(ArrayList<HoaDonKH> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HoaDonKH history = list.get(position);
        holder.name.setText(list.get(position).getName());
        holder.content.setText(list.get(position).getContten());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.format("%.0f",list.get(position).getSum())+ " VND");
        history_dao = new HoaDonKH_DAO(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChiTietDatHangMainActivity.class);
                i.putExtra("id_cart",list.get(position).getId_hoadon());
                i.putExtra("phone", list.get(position).getPhone());
                i.putExtra("name", list.get(position).getName());
                i.putExtra("address", list.get(position).getAddress());
                i.putExtra("time", list.get(position).getTime());
                i.putExtra("sum",(list.get(position).getSum()));
                i.putExtra("content",list.get(position).getContten());
                i.putExtra("status",list.get(position).getStatus());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,sum,time,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.id_hoten);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            content=itemView.findViewById(R.id.id_content);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        showDele(list.get(position).getId_hoadon(), v.getContext());
                        return true;
                    }
                    return false;
                }
            });

        }

    }
    public void showDele(int id,Context context){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_invoice);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonKH_DAO dao = new HoaDonKH_DAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = dialog.getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                    String loggedInUserName = sharedPreferences.getString("USERNAME", "");
                    list = dao.getByUser(loggedInUserName);
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
}
