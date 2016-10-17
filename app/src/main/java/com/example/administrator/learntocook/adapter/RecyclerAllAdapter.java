package com.example.administrator.learntocook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.entity.AllCook;
import com.example.administrator.learntocook.entity.CookInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class RecyclerAllAdapter extends RecyclerView.Adapter<RecyclerAllAdapter.AllViewHolder> {

    private List<AllCook.ResultBean> title;


    public RecyclerAllAdapter(List<AllCook.ResultBean> title) {
        this.title = title;
    }

    @Override
    public AllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item,null);
        return new AllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllViewHolder holder, int position) {
        holder.title_all.setText(title.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return title.size();
    }

    public class AllViewHolder extends RecyclerView.ViewHolder {
        TextView title_all;
        RecyclerView recycler_item;
        public AllViewHolder(View itemView) {
            super(itemView);
            title_all= (TextView) itemView.findViewById(R.id.title_all);
            recycler_item= (RecyclerView) itemView.findViewById(R.id.recycler_item);
        }
    }

    public void addAllData(Collection<AllCook.ResultBean> list){
        title.addAll(list);
        notifyDataSetChanged();
    }
}
