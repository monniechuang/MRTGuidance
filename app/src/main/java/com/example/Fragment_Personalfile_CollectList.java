package com.example.huangyoude.sa;

import android.content.Context;
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

public class Fragment_Personalfile_CollectList extends Fragment {
    private RecyclerView mrestaurant;
    private DatabaseReference mDatabase;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View collectListView = inflater.inflate(R.layout.fragment_personalfile_collectlist, container, false);
        //setContentView(R.layout.activity_collect_list);
        mDatabase = FirebaseDatabase.getInstance().getReference("Collection");
        mDatabase.keepSynced(true);
        mrestaurant = (RecyclerView) collectListView.findViewById(R.id.recycler_restaurant);
        mrestaurant.setHasFixedSize(true);
        mrestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));

        return collectListView;
    }
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Function_Restaurant,Fragment_Personalfile_FJURestaurantList.RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Function_Restaurant,Fragment_Personalfile_FJURestaurantList.RestaurantViewHolder>
                (Function_Restaurant.class,R.layout.unknown_collect_list,Fragment_Personalfile_FJURestaurantList.RestaurantViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(Fragment_Personalfile_FJURestaurantList.RestaurantViewHolder viewHolder, Function_Restaurant model,final int position) {
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


