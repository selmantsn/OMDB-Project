package com.e.omdb_android_project.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.omdb_android_project.R;
import com.e.omdb_android_project.model.reponse.Rating;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.ViewHolder> {


    private List<Rating> items;


    public RatingsAdapter(List<Rating> items) {
        this.items = items;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ratings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Rating item = items.get(position);

        switch (item.getSource()) {
            case "Internet Movie Database":
                holder.rateSiteLogo.setVisibility(View.VISIBLE);
                holder.rateSiteLogo.setImageResource(R.drawable.imdb_logo);
                break;
            case "Rotten Tomatoes":
                holder.rateSiteLogo.setVisibility(View.VISIBLE);
                holder.rateSiteLogo.setImageResource(R.drawable.rotten_logo);
                break;
            case "Metacritic":
                holder.rateSiteLogo.setVisibility(View.VISIBLE);
                holder.rateSiteLogo.setImageResource(R.drawable.metacritic_logo);
                break;
            default:
                holder.rateSiteLogo.setVisibility(View.GONE);
                holder.rateSiteLogo.setImageResource(0);
                break;
        }

        holder.tvRatingSource.setText(item.getSource());
        holder.tvRatingValue.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rateSiteLogo)
        ImageView rateSiteLogo;
        @BindView(R.id.tvRatingSource)
        TextView tvRatingSource;
        @BindView(R.id.tvRatingValue)
        TextView tvRatingValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
