package com.e.omdb_android_project.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.omdb_android_project.R;
import com.e.omdb_android_project.model.reponse.FilmItem;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {


    private List<FilmItem> items;
    private Context context;


    public FilmListAdapter(Context context, List<FilmItem> items) {
        this.items = items;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_film_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FilmItem item = items.get(position);

        Glide.with(context).load(item.getPoster()).apply((new RequestOptions().placeholder(R.color.gray))).into(holder.ivFilmPoster);

        holder.tvFilmName.setText(item.getTitle() + " (" + item.getYear() + ")");
        holder.tvReleased.setText(item.getReleased());
        holder.tvRuntime.setText(item.getRuntime());
        holder.tvGenre.setText(item.getGenre());
        holder.tvDirector.setText(item.getDirector());
        holder.tvWriter.setText(item.getWriter());
        holder.tvActors.setText(item.getActors());

        if (item.getRatings() != null) {
            if (item.getRatings().size() > 0) {
                RatingsAdapter adapter = new RatingsAdapter(item.getRatings());
                holder.recRatings.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                holder.recRatings.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivFilmPoster)
        RoundedImageView ivFilmPoster;
        @BindView(R.id.tvFilmName)
        TextView tvFilmName;
        @BindView(R.id.tvReleased)
        TextView tvReleased;
        @BindView(R.id.tvRuntime)
        TextView tvRuntime;
        @BindView(R.id.tvGenre)
        TextView tvGenre;
        @BindView(R.id.tvDirector)
        TextView tvDirector;
        @BindView(R.id.tvWriter)
        TextView tvWriter;
        @BindView(R.id.tvActors)
        TextView tvActors;
        @BindView(R.id.recRatings)
        RecyclerView recRatings;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
