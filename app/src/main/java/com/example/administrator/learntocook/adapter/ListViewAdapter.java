package com.example.administrator.learntocook.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.entity.AllCook;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ListViewAdapter extends BaseAdapter {

    private ItemAdapter itemAdapter;

    private List<AllCook.ResultBean> title1;

    private List<AllCook.ResultBean> itemName;

    private Context context;

    public ListViewAdapter(List<AllCook.ResultBean> title, List<AllCook.ResultBean> itemName, Context context) {
        this.title1 = title;
        this.itemName=itemName;
        this.context = context;
        itemAdapter = new ItemAdapter(itemName);
    }

    @Override
    public int getCount() {
        return title1.size();
    }

    @Override
    public Object getItem(int position) {
        return title1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item,parent,false);
           TextView title = (TextView) v.findViewById(R.id.title_all);
            title.setText(title1.get(position).getName());
        Log.e("fffffffffffff", title1.get(position).getName()+"getView: ");
        RecyclerView recycler_item= (RecyclerView) v.findViewById(R.id.recycler_item);
        //显示模式为表格布局
        recycler_item.setLayoutManager(new GridLayoutManager(context,4));
        recycler_item.setAdapter(itemAdapter);
        return v;
    }

//    public void addAllDataList(Collection<AllCook.ResultBean> list){
//        itemName.addAll(list);
//        notifyDataSetChanged();
//    }

}
