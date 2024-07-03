package hunghhph44272.fpoly.duan1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

import hunghhph44272.fpoly.duan1.Adapter.CoffeeAdapter;
import hunghhph44272.fpoly.duan1.DAO.CoffeeDAO;
import hunghhph44272.fpoly.duan1.Model.Coffee;
import hunghhph44272.fpoly.duan1.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CoffeeDAO coffeeDAO;
    ArrayList<Coffee> list;
    CoffeeAdapter coffeeAdapter;
    SearchView searchView;
    boolean isSearching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.view1);
        initRecyclerview();
        bottom_navigation();
        Imgslide();

        ImageButton searchButton = findViewById(R.id.btn_Trangchu_tapSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        if (!isSearching) {
            isSearching = true;
            String searchQuery = getSearchQueryFromSearchView();
            if (searchQuery != null && !searchQuery.isEmpty()) {
                list.clear();
                list.addAll(coffeeDAO.search(searchQuery));
                coffeeAdapter.notifyDataSetChanged();
            }
        } else {
            isSearching = false;
            list.clear();
            list.addAll(coffeeDAO.getAllData());
            coffeeAdapter.notifyDataSetChanged();
        }
    }

    private String getSearchQueryFromSearchView() {
        // Lấy SearchView từ layout
        EditText searchView = findViewById(R.id.ed_Trangchu_search); // Thay thế your_searchview_id bằng ID của SearchView trong layout

        // Lấy từ khóa tìm kiếm từ SearchView
        if (searchView != null) {
            return searchView.getText().toString();
        }

        return null;
    }


    private void Imgslide() {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner, "Cà phê – điểm tựa của những giấc mơ...", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.banner1, "Mỗi hạt cà phê là một câu chuyện về đam mê.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.banner2, "Năng lượng của ngày mới nằm trong một ly cà phê.", ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout btnCaNhan = findViewById(R.id.btnCaNhan);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GioHang.class));
            }
        });

        btnCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TrangCaNhan.class));
            }
        });
    }

    private void initRecyclerview() {
        coffeeDAO = new CoffeeDAO(this);
        list = coffeeDAO.getAllData();
        coffeeAdapter = new CoffeeAdapter(this, list, coffeeDAO);
        coffeeAdapter.setData(list);
        recyclerView.setAdapter(coffeeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}