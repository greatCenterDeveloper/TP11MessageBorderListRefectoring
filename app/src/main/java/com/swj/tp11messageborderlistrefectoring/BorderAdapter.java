package com.swj.tp11messageborderlistrefectoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swj.tp11messageborderlistrefectoring.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class BorderAdapter extends RecyclerView.Adapter<BorderAdapter.VH> {

    Context context;
    ArrayList<BorderItem> borderItems;

    public BorderAdapter(Context context, ArrayList<BorderItem> borderItems) {
        this.context = context;
        this.borderItems = borderItems;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        BorderItem border = borderItems.get(position);
        holder.binding.tvName.setText(border.name);
        holder.binding.tvNickname.setText(border.nickName);
        holder.binding.tvTitle.setText(border.title);
        holder.binding.tvContent.setText(border.content);
    }

    @Override
    public int getItemCount() { return borderItems.size(); }

    class VH extends RecyclerView.ViewHolder {

        RecyclerItemBinding binding;

        public VH(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
