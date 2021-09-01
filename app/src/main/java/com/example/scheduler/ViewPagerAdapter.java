package com.example.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewHolderPage> {

    private ArrayList<DataPage> listData;
    private ViewPager2 viewPager2;

    public void setArrayList(ArrayList<DataPage> list) {
        this.listData = list;
    }

    ViewPagerAdapter(ArrayList<DataPage> data) {
        this.listData = data;
    }

    @NonNull
    @Override
    public ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);

        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPage holder, int position) {
        if(holder instanceof ViewHolderPage){
            ViewHolderPage viewHolder = (ViewHolderPage) holder;
            viewHolder.onBind(listData.get(position));
        }
    }

    public void addItem(int i) {

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
}
