package com.example.user.mrtguidance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CollectionListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private RecyclerView mrestaurant;
    private DatabaseReference mDatabase;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list);

        //firebase cardview
        mDatabase = FirebaseDatabase.getInstance().getReference("Restaurant");
        mDatabase.keepSynced(true);
        mrestaurant = (RecyclerView) findViewById(R.id.recycler_restaurant);
        mrestaurant.setHasFixedSize(true);
        mrestaurant.setLayoutManager(new LinearLayoutManager(this));

        //toolbar
        //toolbar =(Toolbar)findViewById(R.id.toolbarCollectionList);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("我的收藏");
        //toolbar.setNavigationIcon(R.drawable.back);

        //下拉式選單
        Spinner spinnerArrangeOrder = findViewById(R.id.spinnerArrangeOrder);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.ArrangeOrder,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrangeOrder.setAdapter(adapter);
        spinnerArrangeOrder.setOnItemSelectedListener(this);
    }

    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Restaurant,RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Restaurant, RestaurantViewHolder>
                (Restaurant.class,R.layout.collection_row,RestaurantViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, Restaurant model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.setDescription(model.getDescription());
            }
        };
        mrestaurant.setAdapter(firebaseRecyclerAdapter);
    }
    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mView= itemView;
        }
        public void setName(String name){
            TextView restaurant_name = (TextView)mView.findViewById(R.id.restaurant_name);
            restaurant_name.setText(name);
        }
        public void setDescription(String description){
            TextView restaurant_description = (TextView)mView.findViewById(R.id.restaurant_description);
            restaurant_description.setText(description);
        }
        public void setImage(Context ctx, String image){
            ImageView restaurant_image = (ImageView)mView.findViewById(R.id.restaurant_image);
            Picasso.with(ctx).load(image).into(restaurant_image);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



