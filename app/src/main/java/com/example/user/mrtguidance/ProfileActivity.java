package com.example.user.mrtguidance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    int[] IMAGES = {R.drawable.plus,R.drawable.star,R.drawable.edit,R.drawable.file,R.drawable.settings};
    String[] NAMES ={"新增食記", "我的收藏","我撰寫的食記","瀏覽紀錄","設定"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ListView listViewProfile =(ListView)findViewById(R.id.listviewProfile);

        CustomAdapter customAdapter=new CustomAdapter();

        listViewProfile.setAdapter(customAdapter);

        listViewProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent myintent = new Intent(view.getContext(),AddArticleActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==1){
                    Intent myintent = new Intent(view.getContext(),MyCollectionActivity.class);
                    startActivityForResult(myintent,1);
                }
                if(position==2){
                    Intent myintent = new Intent(view.getContext(),CollectionListActivity.class);
                    startActivityForResult(myintent,2);
                }
                if(position==3){
                    Intent myintent = new Intent(view.getContext(),CollectionListActivity.class);
                    startActivityForResult(myintent,3);
                }
                if(position==4){
                    Intent myintent = new Intent(view.getContext(),SettingsActivity.class);
                    startActivityForResult(myintent,4);
                }
            }
        });
    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {

            View view =getLayoutInflater().inflate(R.layout.customlayout_profile,null);

            ImageView imageView_profile=(ImageView)view.findViewById(R.id.imageProfile);
            TextView textView_profile= (TextView)view.findViewById(R.id.textViewProfile);

            imageView_profile.setImageResource(IMAGES[position]);
            textView_profile.setText(NAMES[position]);
            return view;
        }
    }
}
