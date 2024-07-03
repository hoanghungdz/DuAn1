package hunghhph44272.fpoly.duan1.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import hunghhph44272.fpoly.duan1.R;

public class LienHeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he_admin);
    }
    public void cancelButtonClicked(View view) {

        finish();
    }
}