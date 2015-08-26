package com.challenge2;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chauphi90 on 25/8/2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    ArrayList<Movie> listMovie = new ArrayList<Movie>();
    Activity context;

    // For ribbon
    ArrayList<Integer> checkedPositions = new ArrayList<Integer>();

    public MovieAdapter(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // !!!
        this.context = (Activity) viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.movie_item_layout, viewGroup, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int position) {
        // Get movie info (text)
        Movie obj = listMovie.get(position);
//        int position2 = movieViewHolder.getAdapterPosition();
//        Movie obj = listMovie.get(position2);
        String posterPath = obj.getPoster_path();
        String originalTitle = obj.getOriginal_title();
        String releaseDate = obj.getRelease_date();
        double voteAverage = obj.getVote_average();

        // Update ListView item
        String url = "http://image.tmdb.org/t/p/w154" + posterPath;
        Picasso.with(context).load(url).into(movieViewHolder.ivPoster);
        movieViewHolder.tvTitle.setText(originalTitle);
        movieViewHolder.tvYear.setText("(" + releaseDate.substring(0, 4) + ")");
        movieViewHolder.tvRating.setText(voteAverage + "");

        removeWatchNow(movieViewHolder, releaseDate);

        // Check Ribbon
        if (checkedPositions.contains(position)) {
            movieViewHolder.ivRibbon.setVisibility(View.VISIBLE);
        }else{
            movieViewHolder.ivRibbon.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivPoster, ivRibbon, ivStar;
        public TextView tvTitle, tvYear, tvRating, tvWatch;
        public ImageButton ibWatch;

        public MovieViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            ivRibbon = (ImageView) itemView.findViewById(R.id.ivRibbon);
            ivStar = (ImageView) itemView.findViewById(R.id.ivStar);
            ibWatch = (ImageButton) itemView.findViewById(R.id.ibWatch);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvRating = (TextView) itemView.findViewById(R.id.tvRating);
            tvWatch = (TextView) itemView.findViewById(R.id.tvWatch);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int state = ivRibbon.getVisibility();
            if (state == View.INVISIBLE) {
                ivRibbon.setVisibility(View.VISIBLE);
                // !!! getAdapterPosition
                checkedPositions.add(getLayoutPosition());
            } else {
                ivRibbon.setVisibility(View.INVISIBLE);
                checkedPositions.remove(new Integer(getLayoutPosition()));
            }
        }
    }

    //     With 3 months condition
    private void removeWatchNow(MovieViewHolder movieViewHolder, String releaseDate) {
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date oldDate = format.parse(releaseDate);
            Date currentDate = new Date();
            int diffInDays = (int) ((currentDate.getTime() - oldDate.getTime()) / (1000 * 60 * 60 * 24));

            if (diffInDays <= 90) {
                // !!!
                movieViewHolder.tvWatch.setVisibility(View.GONE);
                movieViewHolder.ibWatch.setVisibility(View.GONE);
            } else {
                movieViewHolder.tvWatch.setVisibility(View.VISIBLE);
                movieViewHolder.ibWatch.setVisibility(View.VISIBLE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getCheckedPositions() {
        return checkedPositions;
    }

}
