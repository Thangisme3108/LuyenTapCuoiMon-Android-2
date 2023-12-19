package com.example.luyentapcuoimon.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.luyentapcuoimon.Adapter.DuLieuAdapter;
import com.example.luyentapcuoimon.DAO.DuLieuDAO;
import com.example.luyentapcuoimon.DTO.DuLieuDTO;
import com.example.luyentapcuoimon.R;

import java.util.ArrayList;

public class DanhSachFragment extends Fragment {
    RecyclerView rcv_ds;
    ImageView img_add;
    DuLieuAdapter adapter;
    ArrayList<DuLieuDTO> list;
    DuLieuDAO duLieuDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danh_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_ds = view.findViewById(R.id.rcv_ds);
        img_add= view.findViewById(R.id.img_add);
        duLieuDAO = new DuLieuDAO(getContext());
        list = duLieuDAO.selectAll();
        adapter = new DuLieuAdapter(list, getContext());
        rcv_ds.setAdapter(adapter);
        rcv_ds.setLayoutManager(new LinearLayoutManager(getContext()));

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog);

                EditText edtNgay = dialog.findViewById(R.id.edtNgay);
                EditText edtCa = dialog.findViewById(R.id.edtCa);
                EditText edtPhong = dialog.findViewById(R.id.edtPhong);
                EditText edtMon = dialog.findViewById(R.id.edtMon);
                Button btnOK = dialog.findViewById(R.id.btnOK);

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngay = edtNgay.getText().toString();
                        String ca = edtCa.getText().toString();
                        String phong = edtPhong.getText().toString();
                        String mon = edtMon.getText().toString();

                        DuLieuDTO duLieuDTO = new DuLieuDTO();
                        duLieuDTO.setNgay_thi(ngay);
                        duLieuDTO.setCa(ca);
                        duLieuDTO.setPhong(phong);
                        duLieuDTO.setTen_mon(mon);

                        long kq = duLieuDAO.Them(duLieuDTO);
                        if(kq >0 ){
                            list.clear();
                            list.addAll(duLieuDAO.selectAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(),"Thêm thành công"+kq,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(),"Thêm thất bại"+kq,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.getWindow().setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }
}