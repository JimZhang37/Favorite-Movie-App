package com.example.popularmoviesstage2.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Trailer;

import java.util.List;

public class AdapterTrailerList extends RecyclerView.Adapter<AdapterTrailerList.ViewHolderTrailer> {

    private List<Trailer> mTrailers;

    private ListItemClickListener mListener;

    public AdapterTrailerList(ListItemClickListener mListener) {
        this.mListener = mListener;
    }

    public void updateData(List<Trailer> trailers){
        mTrailers = trailers;
        notifyDataSetChanged();
    }
    public interface ListItemClickListener {
        void onListItemClick(String key);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrailer holder, int position) {
        String key = mTrailers.get(position).getKey();
        String title = mTrailers.get(position).getName();
        holder.bind(key,title);
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

        public void bind(String key, String title){
            textView.setText(title);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListItemClick(key);
                }
            });
        }


    }
}
