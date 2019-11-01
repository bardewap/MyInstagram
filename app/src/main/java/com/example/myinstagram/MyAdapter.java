package com.example.myinstagram;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    final String pictures = "https://ifame.000webhostapp.com/images/";
    final String videos = "https://ifame.000webhostapp.com/videos/";

    JSONArray jsonArray;
    Context context;

    public MyAdapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.context = context;

        Log.d("imagrurl", "MyAdapter: "+jsonArray);
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
      View view=layoutInflater.inflate(R.layout.imagelist,parent,false);

      MyViewHolder myViewHolder=new MyViewHolder(view);

        Log.d("myViewHolder1", "onCreateViewHolder: "+myViewHolder);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Log.d("myCount", "onBindViewHolder: "+position);

//        for(int i=0;i<jsonArray.length();i++){
//
//
//
//            try {
//                JSONObject jsonObject=jsonArray.getJSONObject(i);
//
//                String title=jsonObject.getString("title");
//                String type=jsonObject.getString("type");
//
//                if(type.equalsIgnoreCase("image")){
//
//                    String imageUrl=pictures+title+".jpg";
//
//                    Log.d("imageUrss", "onBindViewHolder: "+imageUrl);
//
//                    Glide.with(holder.MyPost.getContext())
//                            .load(imageUrl)
//                            .fitCenter()
//                            .into(holder.MyPost);
//
//
//                }
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }




        try {
          //  Log.e("printkarkedikhao","fnanf"+jsonArray.getJSONObject(position).getString("type"));
            JSONObject jsonObject=jsonArray.getJSONObject(position);


            String url1=null;
            String url2=null;

            if (jsonArray.getJSONObject(position).getString("type").equalsIgnoreCase("image")) {
                url1 = pictures + jsonArray.getJSONObject(position).getString("title") + ".jpg";
                Log.d("dakdvakvd", "onBindViewHolder:" +url1);

            }else{
               //  url2 = videos +jsonArray.getJSONObject(position).getString("title")+ ".mp4";
                Log.d("dakdvakvdqqqqq", "onBindViewHolder:" +url2);
            }


            Glide.with(holder.MyPost.getContext())
                    .load(url1)
                    .into(holder.MyPost);

        //    Picasso.get().load("http://ifame.000webhostapp.com/images/play3.jpg").into(holder.MyPost);

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    @Override
    public int getItemCount()
    {
        Log.d("ladllda", "getItemCount: "+jsonArray.length());
        return jsonArray.length();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView Profile_Pic,MyPost;
        TextView Username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Profile_Pic=(ImageView)itemView.findViewById(R.id.profile_pic);
            MyPost=itemView.findViewById(R.id.My_Post_Image);

        }
    }
}
