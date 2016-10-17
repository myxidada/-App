package com.example.administrator.learntocook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.learntocook.R;
import com.example.administrator.learntocook.entity.CookInfo;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyDetailsHolder> {

    private List<CookInfo.ResultBean.DataBean> data;

    private Context context;

    public DetailsAdapter(List<CookInfo.ResultBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public class MyDetailsHolder extends RecyclerView.ViewHolder {
        ImageView details_img;
        TextView step;
        public MyDetailsHolder(View itemView) {
            super(itemView);
            details_img= (ImageView) itemView.findViewById(R.id.details_img);
            step= (TextView) itemView.findViewById(R.id.step);
        }
    }

    @Override
    public MyDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_details_item,null);
        return new MyDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(MyDetailsHolder holder, int position) {
        holder.step.setText(data.get(position).getSteps().get(position).getStep());
        Log.d("debug",data.size()+"!!!!!!!!!!!!!!!!");
        Picasso.with(context).load(data.get(position).getSteps().get(position).getImg()).into(holder.details_img);
//        holder.step.setText(data.get(position).getStep());
//        Picasso.with(context).load(data.get(position).getImg()).into(holder.details_img);

    }

    @Override
    public int getItemCount() {
        Log.e("asdja", data.size()+"");
        return data.size();
    }


    public void addData(Collection<CookInfo.ResultBean.DataBean> list){
        data.addAll(list);
        notifyDataSetChanged();
    }

}
