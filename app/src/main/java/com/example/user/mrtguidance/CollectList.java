package com.example.user.mrtguidance;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CollectList extends AppCompatActivity {
    private RecyclerView mrestaurant;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_list);
        mDatabase = FirebaseDatabase.getInstance().getReference("Collection");
        mDatabase.keepSynced(true);
        mrestaurant = (RecyclerView) findViewById(R.id.recycler_restaurant);
        mrestaurant.setHasFixedSize(true);
        mrestaurant.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Restaurant,CollectionListActivity.RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Restaurant,CollectionListActivity.RestaurantViewHolder>
                (Restaurant.class,R.layout.collect_list,CollectionListActivity.RestaurantViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(CollectionListActivity.RestaurantViewHolder viewHolder, Restaurant model, int position) {
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
}


