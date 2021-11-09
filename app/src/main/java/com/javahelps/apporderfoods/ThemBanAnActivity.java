package com.javahelps.apporderfoods;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.javahelps.apporderfoods.DAO.BanAnDAO;

public class ThemBanAnActivity extends Activity implements View.OnClickListener {

    EditText edTenThemBanAn;
    Button btnDongYThemBanAn;
    BanAnDAO banAnDAO;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);
        edTenThemBanAn = (EditText) findViewById(R.id.edThemTenBanAn);
        btnDongYThemBanAn = (Button) findViewById(R.id.btnDongYThemBanAn);
        banAnDAO = new BanAnDAO(this);
        btnDongYThemBanAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenBanAn = edTenThemBanAn.getText().toString();
        if (sTenBanAn != null || sTenBanAn.equals("")){
            boolean kiemtra = banAnDAO.ThemBanAn(sTenBanAn);
            Intent intent = new Intent();
            intent.putExtra("ketquathem", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
