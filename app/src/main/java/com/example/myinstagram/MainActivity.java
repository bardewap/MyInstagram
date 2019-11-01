package com.example.myinstagram;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danikula.videocache.HttpProxyCacheServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jzvd.JZVideoPlayer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    ImageButton HomeFragment, SearchFragment, LikeFragment, MyProfileFragment;
    RecyclerView recyclerView;

    MyAdapter myAdapter;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;
//        getSupportActionBar().setTitle("\uD835\uDD74\uD835\uDD93\uD835\uDD98\uD835\uDD99\uD835\uDD86\uD835\uDD8C\uD835\uDD97\uD835\uDD86\uD835\uDD92");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.overlay_dark_90));


        init();
        loadData();

//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadData() {

       final  String Main_Url ="https://ifame.000webhostapp.com/get_video_info.php";

        Log.d("loadDataddv", "loadData: " + Main_Url);

        final String pictures = "https://ifame.000webhostapp.com/images/";
        final String videos = "https://ifame.000webhostapp.com/videos/";

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading Please Wait..");
        progressDialog.show();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, Main_Url, new Response.Listener<String>() {

        @Override
        public void onResponse (String response){
            progressDialog.dismiss();


            Log.d("gdkagd", "kadavdald" + response);

            try {
                JSONArray jsonArray = new JSONArray(response);

                myAdapter = new MyAdapter(jsonArray, context);
                recyclerView.setAdapter(myAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    },new Response.ErrorListener()

    {
        @Override
        public void onErrorResponse (VolleyError error){

    }
    });

    RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


}

    private void init() {

        HomeFragment = findViewById(R.id.homefragment);
        SearchFragment = findViewById(R.id.SearchFragment);
        LikeFragment = findViewById(R.id.myLikeFragment);
        MyProfileFragment = findViewById(R.id.myProfileFragment);
        recyclerView = findViewById(R.id.Recycle_View);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, AddPhotoActivity.class));
            }
        });


        MyProfileFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyProfileFragment.setImageResource(R.drawable.ic_my_profile_filled);

            }
        });


        HomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment.setImageResource(R.drawable.ic_home_blank_filled);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        if (JZVideoPlayer.backPress()) {
            return;
        }
        JZVideoPlayer.releaseAllVideos();
        super.onBackPressed();

    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}