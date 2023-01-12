package com.example.huangyoude.sa;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Fragment_unknown_RestaurantList extends Fragment {
private RecyclerView mrestaurant;
private DatabaseReference mDatabase;
FragmentManager fragmentManager;
FragmentTransaction fragmentTransaction;

    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View restaurantListView = inflater.inflate(R.layout.unknown_restaurant_list, container, false);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_restaurant_list);
        mDatabase = FirebaseDatabase.getInstance().getReference("Restaurant");
        mDatabase.keepSynced(true);
        mrestaurant = (RecyclerView) restaurantListView.findViewById(R.id.recycler_restaurant);
        mrestaurant.setHasFixedSize(true);
        mrestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));

        return restaurantListView;
    }
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Function_Restaurant,RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Function_Restaurant, RestaurantViewHolder>
                (Function_Restaurant.class,R.layout.unknown_restaurant_list_card,RestaurantViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder viewHolder, Function_Restaurant model,final int position) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());
                viewHolder.setDescription(model.getDescription());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        fragmentManager = getFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();

                        if(position==0){
                            fragmentTransaction.replace(R.id.personalfilelayout, new Fragment_Fordemonstration_fjupork())
                                    .addToBackStack(null)
                                    .commit();
                            //intent.setClass(RestaurantList.this,Dairy.class);

                        }else if(position==1){
                            fragmentTransaction.replace(R.id.personalfilelayout, new Fragment_Fordemonstration_fjupork())
                                    .addToBackStack(null)
                                    .commit();
                            //intent.setClass(RestaurantList.this,listview.class);

                        }else{
                            fragmentTransaction.replace(R.id.personalfilelayout, new Fragment_Fordemonstration_fjupork())
                                    .addToBackStack(null)
                                    .commit();
                            //intent.setClass(RestaurantList.this,signin.class);

                        }

                    }
                });
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
