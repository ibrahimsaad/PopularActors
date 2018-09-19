package com.ibrahim.popularactors.ui.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahim.popularactors.listeners.ItemClickListener;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;

public class ActorsRecyclerAdapter extends PagedListAdapter<Actor, RecyclerView.ViewHolder> {

    private ItemClickListener itemClickListener;

    public ActorsRecyclerAdapter(ItemClickListener itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View view = layoutInflater.inflate(R.layout.actor_list_item,
                    parent, false);

            ActorsViewHolder viewHolder = new ActorsViewHolder(parent.getContext(),
                    view, itemClickListener);

            return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ActorsViewHolder) holder).bindTo(getItem(position));

        }

    public static DiffUtil.ItemCallback<Actor> DIFF_CALLBACK = new DiffUtil.ItemCallback<Actor>() {
        @Override
        public boolean areItemsTheSame(@NonNull Actor oldItem, @NonNull Actor newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Actor oldItem, @NonNull Actor newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };
}