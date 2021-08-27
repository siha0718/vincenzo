package com.example.vincenzo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CafeRecyclerAdapter extends RecyclerView.Adapter<CafeRecyclerAdapter.ItemViewHolder> {

    ArrayList<cafe> cafeList = new ArrayList<cafe>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.onBind(cafeList.get(position));
    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    public void addItem(cafe cafe) {
        cafeList.add(cafe);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);

        }
        // 실제 데이터를 1개의 객체마다 1:1 대응하여 바인딩시킨다.
        void onBind(cafe cafe) {

            textView1.setText(cafe.getName());
            // Glide URL로 이미지 불러오기 오픈소스

        }
    }
}