package hunghhph44272.fpoly.duan1.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hunghhph44272.fpoly.duan1.Adapter.GioHang_Adapter;
import hunghhph44272.fpoly.duan1.DAO.CoffeeAD_DAO;
import hunghhph44272.fpoly.duan1.DAO.GioHang_Dao;
import hunghhph44272.fpoly.duan1.DAO.KhachHang_Dao;
import hunghhph44272.fpoly.duan1.DAO.LichSuDonHangDAO;
import hunghhph44272.fpoly.duan1.ItemTouchHelperListener;
import hunghhph44272.fpoly.duan1.Model.CoffeeAD;
import hunghhph44272.fpoly.duan1.Model.Gio_Hang;
import hunghhph44272.fpoly.duan1.Model.KhachHang;
import hunghhph44272.fpoly.duan1.Model.LichSuDonHang;
import hunghhph44272.fpoly.duan1.R;
import hunghhph44272.fpoly.duan1.RecycleViewItemTouchHelper;

public class GioHang extends AppCompatActivity implements GioHang_Adapter.OnQuantityUpClickListener, GioHang_Adapter.OnQuantityDownClickListener, ItemTouchHelperListener {
    RecyclerView recyclerView;
    TextView tv_sumPrice;
    Button btn_confirm;
    ImageView backBtn;
    GioHang_Dao gioHang_dao;
    CoffeeAD_DAO foodDAO;
    ArrayList<Gio_Hang> list_gioHang;
    ArrayList<CoffeeAD> listFood;
    ArrayList<LichSuDonHang> listHis;
    GioHang_Adapter gioHang_adapter;
    KhachHang_Dao khachHang_dao;
    LichSuDonHangDAO historyDao;
    ArrayList<KhachHang> listKhachHang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recy_fragment_cart_listFood);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String InUsername1 = sharedPreferences.getString("USERNAME", "");
        gioHang_dao = new GioHang_Dao(this);
        list_gioHang = gioHang_dao.getByUser(InUsername1);
        tv_sumPrice = findViewById(R.id.tv_fragment_cart_sumPrice);
        btn_confirm = findViewById(R.id.btn_fragment_cart_confirm);
        backBtn = findViewById(R.id.backBtn);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onpenDialog_confirm();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        reloadData();
        updateTotalSum();
    }

    private void reloadData() {
        gioHang_dao = new GioHang_Dao(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        list_gioHang = gioHang_dao.getByUser(loggedInUserName);
        gioHang_adapter = new GioHang_Adapter(this, list_gioHang, gioHang_dao);
        gioHang_adapter.setOnQuantityUpClickListener(this);
        gioHang_adapter.setOnQuantityDownClickListener(this);
        recyclerView.setAdapter(gioHang_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    @Override
    public void onQuantityUpClick(int position) {
        updateTotalSum();
    }

    @Override
    public void onQuantityDownClick(int position) {
        updateTotalSum();
    }

    private void updateTotalSum() {
        int totalSum = calculateTotalSum();
        tv_sumPrice.setText(String.valueOf(totalSum) + " VND");
    }

    private int calculateTotalSum() {
        int totalSum = 0;
        for (Gio_Hang gio_hang : list_gioHang) {
            totalSum += gio_hang.getSum();
        }
        return totalSum;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof GioHang_Adapter.ViewHolder) {
            int id = list_gioHang.get(viewHolder.getAdapterPosition()).getIdCart();
            if (gioHang_dao.delete(id) > 0) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                reloadData();
                updateTotalSum();
            } else {
                Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onpenDialog_confirm() {
        Dialog dialog = new Dialog(this);
        LichSuDonHang history = new LichSuDonHang();

        khachHang_dao = new KhachHang_Dao(this);
        historyDao = new LichSuDonHangDAO(this);

        dialog.setContentView(R.layout.dialog_confirm_invoice);

        EditText ed_address, ed_phone;
        TextView tvDateTime, tvInvSum, tvContent, tvUsername, tvType, tvSize;
        Button btnDialogAddCancel, btnDialogAddSubmit;

        ed_address = dialog.findViewById(R.id.ed_dialog_invoice_confirm_address);
        ed_phone = dialog.findViewById(R.id.ed_dialog_invoice_confirm_phone);
        tvContent = dialog.findViewById(R.id.tv_dialog_invoice_confirm_content);
        tvDateTime = dialog.findViewById(R.id.tv_dialog_invoice_confirm_date);
        tvUsername = dialog.findViewById(R.id.tv_dialog_invoice_confirm_user);
        tvInvSum = dialog.findViewById(R.id.tv_dialog_invoice_confirm_priceSum);
//        tvType = dialog.findViewById(R.id.tv_dialog_invoice_confirm_type);
//        tvSize = dialog.findViewById(R.id.tv_dialog_invoice_confirm_size);
        SharedPreferences sharedPreferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("TEN", "");

        listKhachHang = khachHang_dao.getUsersByName(loggedInUserName);
        for (KhachHang khachHang : listKhachHang) {
            if (khachHang.getTEN_KH().equals(loggedInUserName)) {
                loggedInUserName = khachHang.getTEN_KH();
                break;
            }
        }
        tvUsername.setText(loggedInUserName);
        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String formattedDate = dateFormat.format(currentDate);
        String formattedTime = timeFormat.format(currentDate);

        tvDateTime.setText(formattedTime + " " + formattedDate);
        double totalSum = calculateTotalSum();
        tvInvSum.setText(String.valueOf(totalSum));

        String InUsername2 = sharedPreferences.getString("USERNAME", "");
        list_gioHang = gioHang_dao.getByUser(InUsername2);
        foodDAO = checkFoodDao();
        listFood = foodDAO.getAllData();
        String cartData = "";
        for (Gio_Hang gio_hang : list_gioHang) {
            for (CoffeeAD food : listFood) {
                if (food.getId() == gio_hang.getIdCoffee()) {
                    cartData += "- " + food.getName() + "(" + gio_hang.getSum() + " VNĐ)" + "\n" + "- " + "Số Lượng: " + gio_hang.getQuanti() + "\n" + "- " + "Loại: " + food.getType() + "\n" + "- " + "Size: " + food.getSize() + "\n";
                    break;
                }
            }
        }
        String content = cartData;
        tvContent.setText(content);

        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_invoice_add_add);

        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addrs = ed_address.getText().toString();
                String phoneString = ed_phone.getText().toString();
                String dateTime = tvDateTime.getText().toString();
                String content = tvContent.getText().toString();
//                String size = tvSize.getText().toString();
//                String loai = tvType.getText().toString();
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("TEN", "");
                String name1 = sharedPreferences.getString("USERNAME","");

                if (addrs.trim().isEmpty()) {
                    Toast.makeText(GioHang.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (phoneString.trim().isEmpty()) {
                    Toast.makeText(GioHang.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                } else if (!phoneString.matches("^[0-9]{10}$")) {
                    Toast.makeText(GioHang.this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(GioHang.this, "Hãy chọn món ăn trước khi đặt hàng", Toast.LENGTH_SHORT).show();
                } else {

                    int phone = 0;
                    try {
                        phone = Integer.parseInt(phoneString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(GioHang.this, "Số điện thoại phải là một số", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    history.setName(name);
                    history.setAddress(addrs);
                    history.setPhone(phone);
                    history.setTime(dateTime);
                    history.setSum(calculateTotalSum());
                    history.setContten(content);
//                    history.setSize(size);
//                    history.setType(loai);
                    history.setStatus("Đang Chuẩn Bị Hàng");


                    if (historyDao.insert(history) >= 0) {
                        Toast.makeText(GioHang.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        gioHang_dao.DeleteCart(name1);
                        list_gioHang = gioHang_dao.getAllData();
                        gioHang_adapter.setData(list_gioHang);
                        dialog.dismiss();
                        Intent i = new Intent(GioHang.this, DatHangThanhCong.class);
                        i.putExtra("address", history.getAddress());
                        startActivity(i);
                        updateTotalSum();
                    } else {
                        Toast.makeText(GioHang.this, "Thêm thất bại!", Toast.LENGTH_LONG).show();
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

    private CoffeeAD_DAO checkFoodDao() {
        if (foodDAO == null) {
            // Khởi tạo foodDao nếu chưa tồn tại
            foodDAO = new CoffeeAD_DAO(this);
        }
        return foodDAO;
    }
}