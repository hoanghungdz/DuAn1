package hunghhph44272.fpoly.duan1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import hunghhph44272.fpoly.duan1.Fragment.DoanhThuFragment;
import hunghhph44272.fpoly.duan1.Fragment.QuanLyCoffee_Fragment;
import hunghhph44272.fpoly.duan1.Fragment.QuanLyLoaiCoffee_Fragment;
import hunghhph44272.fpoly.duan1.Fragment.QuanLyTrangThaiFragment;

public class Admin_MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        doifgm(new QuanLyCoffee_Fragment());
        anhXa();
    }
    private void anhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.frameLayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Coffee");
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager= getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                if(id==R.id.nav_coffee){
                    doifgm(new QuanLyCoffee_Fragment());
                    toolbar.setTitle(item.getTitle());
                }else if (id== R.id.nav_donHang) {
                    doifgm(new QuanLyTrangThaiFragment());
                    toolbar.setTitle(item.getTitle());
                } else if (id==R.id.nav_DoanhThu) {
                    doifgm(new DoanhThuFragment());
                    toolbar.setTitle(item.getTitle());
                } else if (id==R.id.nav_loaiCoffee) {
                    doifgm(new QuanLyLoaiCoffee_Fragment());
                    toolbar.setTitle(item.getTitle());
                } else if (id== R.id.nav_DangXuat){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Admin_MainActivity.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không ?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("ISLOGIN",false);
                            editor.apply();
                            Intent intent = new Intent(Admin_MainActivity.this,DangNhap.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Toast.makeText(Admin_MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Toast.makeText(Admin_MainActivity.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    public void doifgm(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }
}