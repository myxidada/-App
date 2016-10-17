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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class RecyclerCokkAdapter extends RecyclerView.Adapter<RecyclerCokkAdapter.TTViewHolder> {

    private List<CookInfo.ResultBean.DataBean> beanList;
    private Context context;

    public RecyclerCokkAdapter(List<CookInfo.ResultBean.DataBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }
    public static class TTViewHolder extends RecyclerView.ViewHolder{
        private ImageView albums;
        private TextView title,tags,ingredients,burden;
        public TTViewHolder(View itemView) {
            super(itemView);
            albums= (ImageView) itemView.findViewById(R.id.albums);
            title= (TextView) itemView.findViewById(R.id.title_ooo);
            tags= (TextView) itemView.findViewById(R.id.tags);
            ingredients= (TextView) itemView.findViewById(R.id.ingredients);
            burden= (TextView) itemView.findViewById(R.id.burden);
        }
    }



    @Override
    public TTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,null);
        return new TTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TTViewHolder holder, final int position) {
        holder.title.setText(beanList.get(position).getTitle());
        holder.tags.setText(beanList.get(position).getTags());
        holder.ingredients.setText(beanList.get(position).getIngredients());
        holder.burden.setText(beanList.get(position).getBurden());
        //因为图片是存到集合里面的所以这里get直接get（0）就可以了
        Picasso.with(context).load(beanList.get(position).getAlbums().get(0)).into(holder.albums);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public void addAllData(Collection<CookInfo.ResultBean.DataBean> list){
        beanList.addAll(list);
        notifyDataSetChanged();
    }

    //回调接口
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener){
            this.mOnItemClickListener=mOnItemClickListener;
    }



}
