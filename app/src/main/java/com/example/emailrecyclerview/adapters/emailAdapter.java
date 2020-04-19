package com.example.emailrecyclerview.adapters;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emailrecyclerview.R;
import com.example.emailrecyclerview.model.MailModel;

import java.util.List;

public class emailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MailModel> items;

    public emailAdapter(List<MailModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder=(ItemViewHolder) holder;
        MailModel mail=items.get(position);
        viewHolder.textTitle.setText(mail.getFullname().substring(0,1));
        viewHolder.textFullname.setText(mail.getFullname());
        viewHolder.textSubject.setText(mail.getSubject());
        viewHolder.textContent.setText(mail.getContent());
        viewHolder.textTime.setText(mail.getTime());
        //
        Drawable background=viewHolder.textTitle.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(mail.getColor(), PorterDuff.Mode.SRC_ATOP));//thử vài cái SRC
        //
        if(mail.isIsfavorite())
        {
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_favorite);
        }
        else
        {
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_normal);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //chức năng tìm kiếm
    public void filterAdapterList(List<MailModel> filterlist)
    {
        items=filterlist;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textFullname;
        TextView textSubject;
        TextView textContent;
        TextView textTime;
        ImageView imageFavorite;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.txt_title);
            textFullname=itemView.findViewById(R.id.txt_fullname);
            textSubject=itemView.findViewById(R.id.txt_subject);
            textContent=itemView.findViewById(R.id.txt_content);
            textTime=itemView.findViewById(R.id.txt_time);
            imageFavorite=itemView.findViewById(R.id.image_favorite);
            //sử lý sự kiện ở đây

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("TAG","vi trí xóa adapter: " +getAdapterPosition());
                    boolean isfavorite=items.get(getAdapterPosition()).isIsfavorite();
                    items.get(getAdapterPosition()).setIsfavorite(!isfavorite);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
