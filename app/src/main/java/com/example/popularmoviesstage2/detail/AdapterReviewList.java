package com.example.popularmoviesstage2.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Review;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterReviewList extends RecyclerView.Adapter<AdapterReviewList.ViewHolderReview> {

    private List<Review> mReviews;

    public void updateData(List<Review> reviews){
        mReviews = reviews;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderReview holder, int position) {
        String content = mReviews.get(position).getContent();
        holder.bind(content);
    }

    @NonNull
    @Override
    public ViewHolderReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_review,parent,false);
        ViewHolderReview holder = new ViewHolderReview(textView);
        return holder;
    }

    @Override
    public int getItemCount() {
        if (mReviews == null ) return 0;
        return mReviews.size();
    }

    class ViewHolderReview extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolderReview(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }

        public void bind(String content){
            textView.setText(content);
        }
    }
}
