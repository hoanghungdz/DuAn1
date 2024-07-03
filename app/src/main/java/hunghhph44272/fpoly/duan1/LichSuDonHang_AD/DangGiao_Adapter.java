package hunghhph44272.fpoly.duan1.LichSuDonHang_AD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hunghhph44272.fpoly.duan1.Activity.ChiTietDatHangMainActivity;
import hunghhph44272.fpoly.duan1.DAO.HoaDon_Dao;
import hunghhph44272.fpoly.duan1.Model.HoaDon;
import hunghhph44272.fpoly.duan1.R;

public class DangGiao_Adapter extends RecyclerView.Adapter<DangGiao_Adapter.ViewHolder> {
    private ArrayList<HoaDon> list;
    private ArrayList<HoaDon> list1;
    private Context context;


    private HoaDon_Dao hoaDon_dao;

    public DangGiao_Adapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(ArrayList<HoaDon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        hoaDon_dao = new HoaDon_Dao(context);
        HoaDon inv = list.get(position);
        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.format("%.0f",list.get(position).getSum())+ " VND");
        holder.content.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());
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
        holder.status.setText(list.get(position).getStatus());

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.status.setText("Thanh Toán Thành Công");
                inv.setStatus("Thanh Toán Thành Công");
                hoaDon_dao.update(inv);
                list=hoaDon_dao.SeLectDangGiao();
                setData(list);
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String formattedTime = timeFormat.format(currentDate);
                String name = inv.getName();
                String orderTime = inv.getTime();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_cart, phone, name, address, sum, time, status, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.id_content);
            name = itemView.findViewById(R.id.id_hoten);
            sum = itemView.findViewById(R.id.id_sum);
            time = itemView.findViewById(R.id.id_time);
            status = itemView.findViewById(R.id.status);
//            content = itemView.findViewById(R.id.id_noidung);

        }
    }
}
