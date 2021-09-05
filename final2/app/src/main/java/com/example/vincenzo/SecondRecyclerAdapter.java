package com.example.vincenzo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class SecondRecyclerAdapter extends RecyclerView.Adapter<Holder> {
    ArrayList<String> list;

    SecondRecyclerAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.second_item, parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //holder.tv.setBackgroundResource();
        //holder.tv.setText(list.get(position));
        CafeImageList List = new CafeImageList();
        String name = list.get(position);

        if (name.equals("스타벅스숙대점")) {
            holder.ImageView1.setBackgroundResource(List.starbucks.get(position));
        } else if (name.equals("에이그레트카페숙대점")) {
            holder.ImageView1.setBackgroundResource(List.agreat.get(position));
        } else if (name.equals("뽀빠")) {
            holder.ImageView1.setBackgroundResource(List.bbobba.get(position));
        } else if (name.equals("프라넬")) {
            holder.ImageView1.setBackgroundResource(List.flanel.get(position));
        } else if (name.equals("효이다방")) {
            holder.ImageView1.setBackgroundResource(List.hyoidabang.get(position));
        } else if (name.equals("본솔카페")) {
            holder.ImageView1.setBackgroundResource(List.bonsol.get(position));
        } else if (name.equals("일미오카페")) {
            holder.ImageView1.setBackgroundResource(List.ilmio.get(position));
        } else if (name.equals("투썸플레이스(숙대점)")) {
            holder.ImageView1.setBackgroundResource(List.twosome.get(position));
        } else if (name.equals("투썸플레이스 용산청파삼거리")) {
            holder.ImageView1.setBackgroundResource(List.twosome.get(position));
        } else if (name.equals("카페 품다")) {
            holder.ImageView1.setBackgroundResource(List.cafepumda.get(position));
        } else if (name.equals("뚜레쥬르(숙대입구)")) {
            holder.ImageView1.setBackgroundResource(List.touslesjours.get(position));
        } else if (name.equals("커피나무숙명여대점")) {
            holder.ImageView1.setBackgroundResource(List.coffenamu.get(position));
        } else if (name.equals("차얌(숙대점)")) {
            holder.ImageView1.setBackgroundResource(List.chayam.get(position));
        } else if (name.equals("낭만청파")) {
            holder.ImageView1.setBackgroundResource(List.nangman.get(position));
        } else if (name.equals("설빙숙대점")) {
            holder.ImageView1.setBackgroundResource(List.sulbing.get(position));
        } else if (name.equals("베이글팩토리")) {
            holder.ImageView1.setBackgroundResource(List.baglefactory.get(position));
        } else if (name.equals("놀숲숙대입구점")) {
            holder.ImageView1.setBackgroundResource(List.nolsup.get(position));
        } else if (name.equals("근사한하루merci")) {
            holder.ImageView1.setBackgroundResource(List.merci.get(position));
        } else if (name.equals("카페쿠바노")) {
            holder.ImageView1.setBackgroundResource(List.cafecubano.get(position));
        } else if (name.equals("디어파인(dear fine)")) {
            holder.ImageView1.setBackgroundResource(List.dearfine.get(position));
        } else if (name.equals("모몽(MOMONG)")) {
            holder.ImageView1.setBackgroundResource(List.momong.get(position));
        } else if (name.equals("와플덴숙대정문점")) {
            holder.ImageView1.setBackgroundResource(List.waffleden.get(position));
        } else if (name.equals("레드우드(Redwood)")) {
            holder.ImageView1.setBackgroundResource(List.redwood.get(position));
        } else if (name.equals("카페인중독숙대입구점")) {
            holder.ImageView1.setBackgroundResource(List.caffeinejungdok.get(position));
        } else if (name.equals("베브릿지 숙명여대점")) {
            holder.ImageView1.setBackgroundResource(List.bebridge.get(position));
        } else if (name.equals("공차숙명여대점")) {
            holder.ImageView1.setBackgroundResource(List.gongcha.get(position));
        } else if (name.equals("너드커피스탠드")) {
            holder.ImageView1.setBackgroundResource(List.nerd.get(position));
        } else if (name.equals("청파동커피")) {
            holder.ImageView1.setBackgroundResource(List.cheongpadongcoffee.get(position));
        } else if (name.equals("마시그래이숙대점")) {
            holder.ImageView1.setBackgroundResource(List.masigeuraei.get(position));
        } else if (name.equals("브루다숙명여대")) {
            holder.ImageView1.setBackgroundResource(List.brewda.get(position));
        } else if (name.equals("카페코지숙명여대점")) {
            holder.ImageView1.setBackgroundResource(List.caphecozy.get(position));
        } else if (name.equals("스토리원")) {
            holder.ImageView1.setBackgroundResource(List.storyone.get(position));
        } else if (name.equals("이디야커피용산청파점")) {
            holder.ImageView1.setBackgroundResource(List.ediya.get(position));
        } else if (name.equals("숙대앞맛있는샌드위치")) {
            holder.ImageView1.setBackgroundResource(List.delicioussand.get(position));
        } else if (name.equals("블랙버드커피")) {
            holder.ImageView1.setBackgroundResource(List.blackbird.get(position));
        } else if (name.equals("에그맛있다")) {
            holder.ImageView1.setBackgroundResource(List.eggmasissda.get(position));
        } else if (name.equals("이티씨")) {
            holder.ImageView1.setBackgroundResource(List.etccoffee.get(position));
        } else if (name.equals("요지트")) {
            holder.ImageView1.setBackgroundResource(List.yozit.get(position));
        } else {
            holder.ImageView1.setBackgroundResource(R.drawable.shop1);
        }


    }

}

class Holder extends RecyclerView.ViewHolder {
    ImageView ImageView1;

    public Holder(@NonNull View itemView) {
        super(itemView);
        ImageView1 = itemView.findViewById(R.id.second_itemview);
    }
}


