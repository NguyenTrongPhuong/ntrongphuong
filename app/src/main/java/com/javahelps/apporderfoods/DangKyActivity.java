package com.javahelps.apporderfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.javahelps.apporderfoods.DAO.NhanVienDAO;
import com.javahelps.apporderfoods.DTO.NhanVienDTO;
import com.javahelps.apporderfoods.Database.CreateDatabase;
import com.javahelps.apporderfoods.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edTenDangNhapDK, edMatKhauDK, edNgaySinhDK, edCMNDDK;
    Button btnDongYDK, btnThoatDK;
    RadioGroup rgGioiTinh;
    String sGioiTinh;
    NhanVienDAO nhanVienDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDK = (EditText) findViewById(R.id.edTenDangNhapDK);
        edMatKhauDK = (EditText) findViewById(R.id.edMatKhauDK);
        edNgaySinhDK = (EditText) findViewById(R.id.edNgaySinhDK);
        edCMNDDK = (EditText) findViewById(R.id.edCMNDDK);
        btnDongYDK = (Button) findViewById(R.id.btnDongYDK);
        btnThoatDK = (Button) findViewById(R.id.btnThoatDK);
        rgGioiTinh = (RadioGroup) findViewById(R.id.rgGioiTinh);

        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick (View v){
         int id = v.getId();
         switch (id){
             case R.id.btnDongYDK:
//                 Kiem tra bat buoc nhap
                 String sTenDangNhap = edTenDangNhapDK.getText().toString();
                 String sMatKhau = edMatKhauDK.getText().toString();

                 switch (rgGioiTinh.getCheckedRadioButtonId()){
                     case R.id.rdNam:
                         sGioiTinh = "Nam";
                         break;
                     case R.id.rdNu:
                         sGioiTinh = "Nữ";
                         break;
                 }

                 String sNgaySinh = edNgaySinhDK.getText().toString();
                 int sCMND = Integer.parseInt(edCMNDDK.getText().toString());

                 if (sTenDangNhap == null || sTenDangNhap.equals("")){
                     Toast.makeText(DangKyActivity.this, getResources().getString(R.string.loinhaptendangnhap), Toast.LENGTH_SHORT).show();
                 }else if (sMatKhau == null || sMatKhau.equals("")){
                     Toast.makeText(DangKyActivity.this, getResources().getString(R.string.loinhapmatkhau), Toast.LENGTH_SHORT).show();
                 }else {
                     NhanVienDTO nhanVienDTO = new NhanVienDTO();
                     nhanVienDTO.setTENDN(sTenDangNhap);
                     nhanVienDTO.setMATKHAU(sMatKhau);
                     nhanVienDTO.setCMND(sCMND);
                     nhanVienDTO.setNGAYSINH(sNgaySinh);
                     nhanVienDTO.setGIOITINH(sGioiTinh);

                     long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                     if (kiemtra != 0){
                         Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();

                     }

                 }


                 break;

             case R.id.btnThoatDK:
                 finish();
                 break;
         }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edNgaySinhDK:
                if (hasFocus){
//                    Xuat popup
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(), "Ngày Sinh");
                }
                break;
        }

    }
}