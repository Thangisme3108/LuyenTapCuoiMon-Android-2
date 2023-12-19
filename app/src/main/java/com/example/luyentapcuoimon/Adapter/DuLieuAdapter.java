package com.example.luyentapcuoimon.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentapcuoimon.DAO.DuLieuDAO;
import com.example.luyentapcuoimon.DTO.DuLieuDTO;
import com.example.luyentapcuoimon.R;

import java.util.ArrayList;

public class DuLieuAdapter extends RecyclerView.Adapter<DuLieuAdapter.ViewHolder> {
    ArrayList<DuLieuDTO> list;
    Context context;

    public DuLieuAdapter(ArrayList<DuLieuDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item,parent,false);
        return new DuLieuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DuLieuDTO duLieuDTO = list.get(position);

        holder.txtID.setText(duLieuDTO.getId() + "");
        holder.txtCa.setText(duLieuDTO.getCa());
        holder.txtPhong.setText(duLieuDTO.getPhong());
        holder.txtNgay.setText(duLieuDTO.getNgay_thi());
        holder.txtMon.setText(duLieuDTO.getTen_mon());

        holder.img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo").setMessage("Bạn có muốn xoá").setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DuLieuDAO dAo = new DuLieuDAO(context);
                        int kq = dAo.Xoa(duLieuDTO);
                        if( kq == 1){
                            list.clear();
                            list.addAll(dAo.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xoá thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xoá thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        holder.img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);

                EditText edtNgay = dialog.findViewById(R.id.edtNgay);
                EditText edtCa = dialog.findViewById(R.id.edtCa);
                EditText edtPhong = dialog.findViewById(R.id.edtPhong);
                EditText edtMon = dialog.findViewById(R.id.edtMon);
                Button btnOK = dialog.findViewById(R.id.btnOK);

                edtCa.setText(duLieuDTO.getCa());
                edtNgay.setText(duLieuDTO.getNgay_thi());
                edtPhong.setText(duLieuDTO.getPhong());
                edtMon.setText(duLieuDTO.getTen_mon());

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngay = edtNgay.getText().toString();
                        String ca = edtCa.getText().toString();
                        String phong = edtPhong.getText().toString();
                        String mon = edtMon.getText().toString();

                        duLieuDTO.setNgay_thi(ngay);
                        duLieuDTO.setCa(ca);
                        duLieuDTO.setPhong(phong);
                        duLieuDTO.setTen_mon(mon);
                        DuLieuDAO duLieuDAO = new DuLieuDAO(context);

                        long kq = duLieuDAO.Sua(duLieuDTO);
                        if(kq > 0 ){
                            list.clear();
                            list.addAll(duLieuDAO.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Sửa thành công " + kq,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context,"Sửa thất bại " + kq,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.getWindow().setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID,txtCa,txtNgay,txtMon,txtPhong;
        ImageView img_sua, img_xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCa = itemView.findViewById(R.id.txtCa);
            txtID = itemView.findViewById(R.id.txtID);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtMon = itemView.findViewById(R.id.txtMon);
            txtPhong = itemView.findViewById(R.id.txtPhong);
            img_xoa = itemView.findViewById(R.id.img_xoa);
            img_sua = itemView.findViewById(R.id.img_sua);
        }
    }

}
