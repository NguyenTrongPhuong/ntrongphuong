package com.javahelps.apporderfoods.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.javahelps.apporderfoods.DTO.BanAnDTO;
import com.javahelps.apporderfoods.R;

import java.util.List;

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;

    public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> banAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }



    public class ViewHolderBanAn{
        ImageView imBanAn, imGoiMon, imThanhToan, imAnButton;
        TextView txtTenBanAn;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn = new ViewHolderBanAn();
            view = inflater.inflate(R.layout.custom_layout_hienthibanan, parent, false);
            viewHolderBanAn.imBanAn = (ImageView) view.findViewById(R.id.imBanAn);
            viewHolderBanAn.imGoiMon = (ImageView) view.findViewById(R.id.imGoiMon);
            viewHolderBanAn.imThanhToan = (ImageView) view.findViewById(R.id.imThanhToan);
            viewHolderBanAn.imAnButton = (ImageView) view.findViewById(R.id.imAnButton);
            viewHolderBanAn.txtTenBanAn = (TextView) view.findViewById(R.id.txtTenBanAn);

            view.setTag(viewHolderBanAn);
        }else {
            viewHolderBanAn = (ViewHolderBanAn) view.getTag();
        }
        //Luu vi tri ban duoc chon
        if (banAnDTOList.get(position).isDuocChon()){
            HienThiButton();
        }else {
            AnButTon();
        }
        BanAnDTO banAnDTO = banAnDTOList.get(position);
        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());
        viewHolderBanAn.imBanAn.setTag(position);

        //Su kien click
        viewHolderBanAn.imBanAn.setOnClickListener(this);
        return view;
    }

    private void HienThiButton(){
        viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
        viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
    }
    private void AnButTon(){
        viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
    }

    //Su kien click
    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View) v.getParent()).getTag();
        switch (id){
            case R.id.imBanAn:
                String tenban = viewHolderBanAn.txtTenBanAn.getText().toString();
                int vitri = (int) v.getTag();
                banAnDTOList.get(vitri).setDuocChon(true);
                HienThiButton();
                break;
        }
    }
}
