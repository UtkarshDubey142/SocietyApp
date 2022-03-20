package com.utkarsh.practice;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {


    Context context;
    List<Message> messages;
    DatabaseReference messageDB;

    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messageDB){
        this.context = context;
        this.messages = messages;
        this.messageDB = messageDB;
    }


    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.message_items, parent, false);
      return new MessageAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int position) {
        Message message = messages.get(position);
        if(AllMethods.name.equals(message.getName())){
            holder.textView.setText("You:" + message.getUserMessage());
            holder.textView.setGravity(Gravity.START);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#536872"));
        }
        else{
            holder.textView.setText( message.getName()+":" + message.getUserMessage());
        }

    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;

        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.liMessage);
            textView = itemView.findViewById(R.id.tvId);
        }
    }
    public void refreshList(){
        notifyDataSetChanged();
    }
}
