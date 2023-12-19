package com.example.luyentapcuoimon.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luyentapcuoimon.Adapter.DuLieuAdapter;
import com.example.luyentapcuoimon.Adapter.PagerAdapter;
import com.example.luyentapcuoimon.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CollectionFragment extends Fragment {
    ViewPager2 pager;
    TabLayout tabLayout;
    PagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tabLayout);

        adapter = new PagerAdapter(this);
        pager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Danh sách");
                        break;
                    case 1:
                        tab.setText("Thông tin");
                        break;
                }
            }
        });
        mediator.attach();
    }
}