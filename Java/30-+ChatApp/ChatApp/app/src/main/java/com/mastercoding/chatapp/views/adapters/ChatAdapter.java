package com.mastercoding.chatapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.chatapp.BR;
import com.mastercoding.chatapp.R;
import com.mastercoding.chatapp.databinding.RowChatBinding;
import com.mastercoding.chatapp.model.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatMessage> chatMessageList;
    private Context context;
    public ChatAdapter(List<ChatMessage> chatMessageList, Context context) {
        this.chatMessageList = chatMessageList;
        //only in binding
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_chat,parent,false);

        //only in binding
        RowChatBinding binding = DataBindingUtil.bind(view);

        return new MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.chatMessage, chatMessageList.get(position));
        //beater expose list
        holder.getBinding().executePendingBindings();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private RowChatBinding binding;

        public MyViewHolder( RowChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public RowChatBinding getBinding() {
            return binding;
        }
    }
}
