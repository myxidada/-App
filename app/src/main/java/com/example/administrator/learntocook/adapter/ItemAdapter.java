package com.example.administrator.learntocook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.entity.AllCook;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<AllCook.ResultBean> itemName;

    public ItemAdapter(List<AllCook.ResultBean> itemName) {
        this.itemName = itemName;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_reycler,null);
        return new  ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv_item_item.setText(itemName.get(position).getList().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_item_item;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_item= (TextView) itemView.findViewById(R.id.tv_item_item);
        }
    }
    public void addAllDataTwo(Collection<AllCook.ResultBean> list){
        itemName.addAll(list);
        notifyDataSetChanged();
    }

}
