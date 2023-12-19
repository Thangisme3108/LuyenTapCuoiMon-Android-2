package com.example.luyentapcuoimon.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.luyentapcuoimon.DTO.DuLieuDTO;
import com.example.luyentapcuoimon.Database.DbHelper;

import java.util.ArrayList;

public class DuLieuDAO {
    String TAG = "zzzzzzzzzzzzzzzzzzzz";
    private final DbHelper dbHelper;
    SQLiteDatabase database;

    public DuLieuDAO(Context context) {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<DuLieuDTO> selectAll() {
        ArrayList<DuLieuDTO> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from ds_thi order by id asc", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    DuLieuDTO dl = new DuLieuDTO();
                    dl.setId(cursor.getInt(0));
                    dl.setNgay_thi(cursor.getString(1));
                    dl.setCa(cursor.getString(2));
                    dl.setPhong(cursor.getString(3));
                    dl.setTen_mon(cursor.getString(4));
                    list.add(dl);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "selectAll: Lỗi", e);
        }
        return list;
    }

    public long Them(DuLieuDTO duLieuDTO){
        ContentValues values = new ContentValues();
        values.put("ngay_thi",duLieuDTO.getNgay_thi());
        values.put("ca",duLieuDTO.getCa());
        values.put("phong",duLieuDTO.getPhong());
        values.put("ten_mon",duLieuDTO.getTen_mon());
        long kq = database.insert("ds_thi", null, values);
        return kq;
    }

    public int Xoa(DuLieuDTO duLieuDTO){

        int kq = database.delete("ds_thi","id=" + duLieuDTO.getId(),null);
        return kq;
    }

    public int Sua(DuLieuDTO duLieuDTO) {
        ContentValues values = new ContentValues();
        values.put("ngay_thi",duLieuDTO.getNgay_thi());
        values.put("ca",duLieuDTO.getCa());
        values.put("phong",duLieuDTO.getPhong());
        values.put("ten_mon",duLieuDTO.getTen_mon());
        int kq = database.update("ds_thi", values,"id=" + duLieuDTO.getId(),null);
        return kq;
    }
}
