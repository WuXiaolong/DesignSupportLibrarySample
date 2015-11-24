package com.wuxiaolong.designsupportlibrarysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 小尛龙 on 2015/11/16 0016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> dataList;
    private Activity mActivity;

    RecyclerViewAdapter(Activity activity, List<String> dataList) {
        this.dataList = dataList;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position));
        if (position % 2 == 0) {
            holder.showImage.setBackgroundResource(R.mipmap.show_img1);
        } else {
            holder.showImage.setBackgroundResource(R.mipmap.show_img2);
        }
        ViewCompat.setTransitionName(holder.showImage, DetailActivity.TRANSITION_PIC);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView showImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            showImage = (ImageView) itemView.findViewById(R.id.showImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mActivity, DetailActivity.class);
                    intent.putExtra("position", getLayoutPosition());
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(mActivity, showImage, DetailActivity.TRANSITION_PIC);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mActivity.startActivity(intent, options.toBundle());
                    } else {
                        mActivity.startActivity(intent);
                    }
                }
            });
        }
    }
}
