package com.yehowah.yehimalaya.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.yehowah.yehimalaya.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//创建适配器，先要继承Adapter
public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.InnerHolder> {
    private List<Album> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载View
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置数据
        holder.itemView.setTag(position);//
        holder.setData(mData.get(position));//将数据传递给InnerHolder，给UI上进行显示
    }

    @Override
    public int getItemCount() {
        if (mData != null){
            return mData.size();
        }
        return 0;
    }

    //通过喜马拉雅API获取到数据
    public void setData(List<Album> albumList) {
        if (mData != null){
            mData.clear();
            mData.addAll(albumList);
        }
        //更新一下UI
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(Album album) {
            //找到各个控件，设置数据
            ImageView albumConverIv = itemView.findViewById(R.id.album_cover);              //专辑封面
            TextView albumTitleTv = itemView.findViewById(R.id.album_title_tv);             //title
            TextView albumDecriptionTv = itemView.findViewById(R.id.album_description_tv);  //描述
            TextView albumPlayCountTv = itemView.findViewById(R.id.album_play_count_tv);    //播放数量
            TextView albumContentSizeTv = itemView.findViewById(R.id.album_content_size_tv);//专辑内容数量

            albumTitleTv.setText(album.getAlbumTitle());
            albumDecriptionTv.setText(album.getAlbumIntro());
            albumPlayCountTv.setText(album.getPlayCount() + "");
            albumContentSizeTv.setText(album.getIncludeTrackCount()+"");//专辑包含声音数

            Picasso.with(itemView.getContext())
                    .load(album.getCoverUrlLarge())
                    .into(albumConverIv);//使用Picasso库进行加载图片
        }
    }
}
