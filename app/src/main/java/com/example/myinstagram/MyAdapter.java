package com.example.myinstagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.danikula.videocache.HttpProxyCacheServer;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jzvd.JZVideoPlayerStandard;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    final String pictures = "https://ifame.000webhostapp.com/images/";
    final String videos = "https://ifame.000webhostapp.com/videos/";


    JSONArray jsonArray;
    Context context;

    public MyAdapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.context = context;

        Log.d("imagrurl", "MyAdapter: " + jsonArray);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.imagelist, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        Log.d("myViewHolder1", "onCreateViewHolder: " + myViewHolder);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Log.d("myCount", "onBindViewHolder: " + position);


        try {
            //  Log.e("printkarkedikhao","fnanf"+jsonArray.getJSONObject(position).getString("type"));
            JSONObject jsonObject = jsonArray.getJSONObject(position);


            String url1 = null;
            String url2 = null;

            if (jsonArray.getJSONObject(position).getString("type").equalsIgnoreCase("image")) {
                url1 = pictures + jsonArray.getJSONObject(position).getString("title") + ".jpg";
                Log.d("dakdvakvd", "onBindViewHolder:" + url1);
                holder.MyPost.setVisibility(View.VISIBLE);
              //  holder.myVideoPost.setVisibility(View.GONE);
                holder.jzVideoPlayerStandard.setVisibility(View.GONE);
              //  holder.ivPlay.setVisibility(View.GONE);

                Glide.with(holder.MyPost.getContext())
                        .load(url1)
                        .into(holder.MyPost);


            } else {
                 url2 = videos +jsonArray.getJSONObject(position).getString("title")+ ".mp4";
                Log.d("dakdvakvdqqqqq", "onBindViewHolder:" + url2);

                holder.MyPost.setVisibility(View.GONE);
              //  holder.myVideoPost.setVisibility(View.VISIBLE);
                holder.jzVideoPlayerStandard.setVisibility(View.VISIBLE);
              //  holder.ivPlay.setVisibility(View.VISIBLE);

//                Glide.with(context)
//                        .asBitmap()
//                        .load(url2)
////                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.DATA)
//                        .into(holder.myVideoPost);

               // Picasso.get().load(url2).into(holder.myVideoPost);
                MyApplication myApplication= new MyApplication();
                HttpProxyCacheServer proxy = myApplication.getProxy(context);
                holder.jzVideoPlayerStandard.setUp(proxy.getProxyUrl(url2)
                        , JZVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");

                Glide.with(context).load(url2).apply(new RequestOptions().override(50,50)).into(holder.jzVideoPlayerStandard.thumbImageView);



            }


            //    Picasso.get().load("http://ifame.000webhostapp.com/images/play3.jpg").into(holder.MyPost);

        } catch (JSONException e) {
            e.printStackTrace();
        }
//
//    holder.myVideoPost.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//        }
//    });



    }

    @Override
    public int getItemCount() {
        Log.d("ladllda", "getItemCount: " + jsonArray.length());
        return jsonArray.length();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Profile_Pic, MyPost, myVideoPost, ivPlay;
        TextView Username;
        JZVideoPlayerStandard jzVideoPlayerStandard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Profile_Pic = (ImageView) itemView.findViewById(R.id.profile_pic);
            MyPost = itemView.findViewById(R.id.My_Post_Image);
           // myVideoPost = itemView.findViewById(R.id.ivFeedVideo);
           // ivPlay = itemView.findViewById(R.id.ivPlay);

            jzVideoPlayerStandard = (JZVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);



        }
    }
}
