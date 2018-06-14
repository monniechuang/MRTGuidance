package com.example.user.mrtguidance;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyCollectionActivity extends AppCompatActivity {
    ListView listviewCollection;
    String[] CollectionName ={"輔大(5)", "台北車站(2)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        listviewCollection= findViewById (R.id.listviewMyCollection);
        //create instance of class MyAdapter
        MyAdapter adapter= new MyAdapter(this,CollectionName);
        //set Adapter to list
        listviewCollection.setAdapter(adapter);
        listviewCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){
                    Intent intent = new Intent();
                    intent.setClass(MyCollectionActivity.this, CollectList.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent();
                    intent.setClass(MyCollectionActivity.this, CollectionListActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String MyCollectionName[];
        MyAdapter(Context c,String[] CollectionName){
            super(c,R.layout.my_collection_listview,R.id.TextViewCollectionName,CollectionName);
            this.context=c;
            this.MyCollectionName=CollectionName;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater inflater = getLayoutInflater();
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.my_collection_listview,parent,false);
            TextView MyCollectionName=row.findViewById(R.id.TextViewCollectionName);
            MyCollectionName.setText(CollectionName[position]);
            return row;
        }
    }
}