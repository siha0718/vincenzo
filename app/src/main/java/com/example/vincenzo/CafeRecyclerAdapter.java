package com.example.vincenzo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class CafeRecyclerAdapter extends RecyclerView.Adapter<CafeRecyclerAdapter.ItemViewHolder> {

    ArrayList<cafe> cafeList = new ArrayList<cafe>();
    private TextView textView1, textView2, textView3;        //textView4, textView5;\
    //private Button textView1;
    private ImageView ImageView1, ImageView2;
    private Context mContext;


    public CafeRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.onBind(cafeList.get(position));
        itemViewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {   return cafeList.size();  }

    public void addItem(cafe cafe) {
        cafeList.add(cafe);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            ImageView1 = itemView.findViewById(R.id.ImageTest);
//            textView2 = itemView.findViewById(R.id.textView2);
//            ImageView2 = itemView.findViewById(R.id.ImageTest2);
//            textView3 = itemView.findViewById(R.id.textView3);
//            textView4 = itemView.findViewById(R.id.textView4);
//            textView5 = itemView.findViewById(R.id.textView5);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext, second_activity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("name", cafeList.get(pos).getName());
                        intent.putExtra("latitude", cafeList.get(pos).getLatitude());
                        intent.putExtra("longitude", cafeList.get(pos).getLongitude());
                        mContext.startActivity(intent);
                    }
                }
            });

        }
        // 실제 데이터를 1개의 객체마다 1:1 대응하여 바인딩시킨다.
        void onBind(cafe cafe) {

            CafeImageList List = new CafeImageList();
            String name = cafe.getName();
            int imageId = (int)(Math.random() * 5);

            if(name.equals("스타벅스숙대점")){
               textView1.setText(cafe.getName());
               ImageView1.setBackgroundResource(List.starbucks.get(imageId));
            }else if (name.equals("에이그레이트카페숙대점")){
               textView1.setText(cafe.getName());
               ImageView1.setBackgroundResource(List.agreat.get(imageId));
            }else if (name.equals("뽀빠")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.bbobba.get(imageId));
            }else if (name.equals("프라넬")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.flanel.get(imageId));
            }else if (name.equals("효이다방")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.hyoidabang.get(imageId));
            }else if (name.equals("본솔카페")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.bonsol.get(imageId));
            }else if (name.equals("일미오카페")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.ilmio.get(imageId));
            }else if (name.equals("투썸플레이스(숙대점)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.twosome.get(imageId));
            }else if (name.equals("투썸플레이스 용산청파삼거리")) {
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.twosome.get(imageId));
            }else if (name.equals("카페 품다")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.cafepumda.get(imageId));
            }else if (name.equals("뚜레쥬르(숙대입구)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.touslesjours.get(imageId));
            }else if (name.equals("커피나무숙명여대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.coffenamu.get(imageId));
            }else if (name.equals("차얌(숙대점)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.chayam.get(imageId));
            }else if (name.equals("낭만청파")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.nangman.get(imageId));
            }else if (name.equals("설빙숙대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.sulbing.get(imageId));
            }else if (name.equals("베이글팩토리")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.baglefactory.get(imageId));
            }else if (name.equals("놀숲숙대입구점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.nolsup.get(imageId));
            }else if (name.equals("근사한하루merci")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.merci.get(imageId));
            }else if (name.equals("카페쿠바노")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.cafecubano.get(imageId));
            }else if (name.equals("디어파인(dear fine)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.dearfine.get(imageId));
            }else if (name.equals("모몽(MOMONG)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.momong.get(imageId));
            }else if (name.equals("와플덴숙대정문점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.waffleden.get(imageId));
            }else if (name.equals("레드우드(Redwood)")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.redwood.get(imageId));
            }else if (name.equals("카페인중독숙대입구점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.caffeinejungdok.get(imageId));
            }else if (name.equals("베브릿지 숙명여대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.bebridge.get(imageId));
            }else if (name.equals("공차숙명여대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.gongcha.get(imageId));
            }else if (name.equals("너드커피스탠드")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.nerd.get(imageId));
            }else if (name.equals("청파동커피")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.cheongpadongcoffee.get(imageId));
            }else if (name.equals("마시그래이숙대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.masigeuraei.get(imageId));
            }else if (name.equals("브루다숙명여대")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.brewda.get(imageId));
            }else if (name.equals("카페코지숙명여대점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.caphecozy.get(imageId));
            }else if (name.equals("스토리원")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.storyone.get(imageId));
            }else if (name.equals("이디야커피용산청파점")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.ediya.get(imageId));
            }else if (name.equals("숙대앞맛있는샌드위치")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.delicioussand.get(imageId));
            }else if (name.equals("블랙버드커피")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.blackbird.get(imageId));
            }else if (name.equals("에그맛있다")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.eggmasissda.get(imageId));
            }else if (name.equals("이티씨")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.etccoffee.get(imageId));
            }else if (name.equals("요지트")){
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(List.yozit.get(imageId));
            }else {
                textView1.setText(cafe.getName());
                ImageView1.setBackgroundResource(R.drawable.shop1);
            }




//            textView1.setText(cafe.getName());
//            ImageView1.setBackgroundResource(listResId.get(imageId));

//            textView2.setText(cafe.getName());
//            ImageView2.setBackgroundResource(listResId2.get(imageId2));

//            textView2.setText(cafe.getAddress());
//            textView3.setText(cafe.getSize());
//            textView4.setText(cafe.getLatitude());
//            textView5.setText(cafe.getLongitude());
            // Glide URL로 이미지 불러오기 오픈소스


        }

    }


}