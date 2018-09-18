package com.ibrahim.popularactors.repository.paging;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorsRecyclerAdapter extends
        PagedListAdapter<Actor, ActorsRecyclerAdapter.ActorViewHolder> {
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";
    private Context mContext;
    private ItemClickListener itemClickListener;


    public interface ItemClickListener {
        void OnItemClick(Actor actor);
    }

    public ActorsRecyclerAdapter(Context mContext, ItemClickListener itemClickListener) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.actor_list_item, viewGroup, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder actorViewHolder, int i) {
        Actor item = getItem(i);
        actorViewHolder.bind(item, itemClickListener, i);
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.actorImage)
        ImageView actorImage;
        @BindView(R.id.actorName)
        TextView actorName;

        public ActorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Actor actor, final
        ItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(actor);
                }
            });

            actorName.setText(actor.getName());
            Glide.with(mContext)
                    .load(BASE_IMAGE_URL + actor.getProfilePath())
                    .into(actorImage);

        }

    }

    public static DiffUtil.ItemCallback<Actor> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Actor>() {
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
