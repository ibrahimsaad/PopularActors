package com.ibrahim.popularactors.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.ActorImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder> {

    private final List<ActorImage> actorImageList;
    private final Context mContext;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onImageItemClick(String url, int position);
    }

    public ImagesRecyclerAdapter(List<ActorImage> actorImageList, Context mContext,
                                 OnItemClickListener onItemClickListener) {
        this.actorImageList = actorImageList;
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.actor_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ActorImage actorImage = actorImageList.get(position);
        Glide.with(mContext)
                .load(BASE_IMAGE_URL + actorImage.getFilePath())
                .into(holder.actorImage);
        holder.actorName.setVisibility(View.GONE);
        holder.actorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onImageItemClick(BASE_IMAGE_URL + actorImage.getFilePath(), position);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (actorImageList.size() >= 6) {
            return 6;
        }
        return actorImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.actorImage)
        ImageView actorImage;
        @BindView(R.id.actorName)
        TextView actorName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}