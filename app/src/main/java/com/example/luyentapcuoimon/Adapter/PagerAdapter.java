package com.example.luyentapcuoimon.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.luyentapcuoimon.Fragment.DanhSachFragment;
import com.example.luyentapcuoimon.Fragment.ThongTinFragment;

public class PagerAdapter extends FragmentStateAdapter {
    int soLuong_tab = 2;
    DanhSachFragment danhSachFragment;
    ThongTinFragment thongTinFragment;

    public PagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        danhSachFragment = new DanhSachFragment();
        thongTinFragment = new ThongTinFragment();
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return danhSachFragment;
            case 1: return thongTinFragment;
            default: return danhSachFragment;
        }
    }

    @Override
    public int getItemCount() {
        return soLuong_tab;
    }
}
