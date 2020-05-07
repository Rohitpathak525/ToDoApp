package com.zayn.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zayn.myapplication.R;

import java.util.ArrayList;


public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>{
    private ArrayList<String> mSchedule=new ArrayList<>();
    private OnNoteListener mOnNoteListener;


    public RecyclerViewAdaptor(ArrayList<String> mSchedule,OnNoteListener mOnNoteListener) {
        this.mSchedule = mSchedule;
        this.mOnNoteListener=mOnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);
        ViewHolder viewHolder=new ViewHolder(view,mOnNoteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.schedule.setText(mSchedule.get(position));

    }

    @Override
    public int getItemCount() {
        return mSchedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView schedule;
        CardView cardview;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            schedule = itemView.findViewById(R.id.name);
            cardview=itemView.findViewById(R.id.cardView);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }
    public interface OnNoteListener{
        void onNoteClick(int postion);
    }
}
