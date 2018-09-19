package com.ibrahim.popularactors.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibrahim.popularactors.listeners.ItemClickListener;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;

public class ActorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";

    private Actor actor;
    private TextView titleTextView;
    private ImageView thumbnailImageView;
    private ItemClickListener itemClickListener;
    private Context context;

    public ActorsViewHolder(Context context, View view, ItemClickListener itemClickListener) {
        super(view);
        this.titleTextView = view.findViewById(R.id.actorName);
        this.thumbnailImageView = view.findViewById(R.id.actorImage);
        this.itemClickListener = itemClickListener;
        this.context = context;
        view.setOnClickListener(this);

    }

    public void bindTo(Actor actor) {
        this.actor = actor;
        titleTextView.setText(actor.getName());

        Glide.with(context)
                .load(BASE_IMAGE_URL + actor.getProfilePath())
                .into(thumbnailImageView);
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.OnItemClick(actor); // call the onClick in the OnItemClickListener
        }
    }

}