package com.example.popularmoviesstage2.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Trailer;

import java.util.List;

public class AdapterTrailerList extends RecyclerView.Adapter<AdapterTrailerList.ViewHolderTrailer> {

    private List<Trailer> mTrailers;

    public void updateData(List<Trailer> trailers){
        mTrailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrailer holder, int position) {
        String content = mTrailers.get(position).getKey();
        holder.bind(content);
    }


    @NonNull
    @Override
    public ViewHolderTrailer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_trailer,parent,false);
        ViewHolderTrailer holder = new ViewHolderTrailer(textView);
        return holder;
    }

    @Override
    public int getItemCount() {
        if (mTrailers == null ) return 0;
        return mTrailers.size();
    }

    class ViewHolderTrailer extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolderTrailer(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }

        public void bind(String content){
            textView.setText(content);
        }
    }
}
