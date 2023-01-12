package com.example.huangyoude.sa;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_Personalfile_Profile extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    int[] IMAGES = {R.drawable.plus,R.drawable.star,R.drawable.edit,R.drawable.file,R.drawable.settings};
    String[] NAMES ={"新增食記", "我的收藏","我撰寫的食記","瀏覽紀錄","設定"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.fragment_personalfile_profile, container, false);
        //setContentView(R.layout.activity_profile);

        ListView listViewProfile =(ListView)profileView.findViewById(R.id.listviewProfile);

        CustomAdapter customAdapter=new CustomAdapter();

        listViewProfile.setAdapter(customAdapter);

        listViewProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                if(position==0){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_MealDiary_AddArticle())
                            .addToBackStack(null)
                            .commit();
                }
                if(position==1){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_MyCollection())
                            .addToBackStack(null)
                            .commit();
                }
                if(position==2){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_FJURestaurantList())
                            .addToBackStack(null)
                            .commit();
                }
                if(position==3){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_FJURestaurantList())
                            .addToBackStack(null)
                            .commit();
                }
                if(position==4){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Settings())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return profileView;
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

            View view =getLayoutInflater().inflate(R.layout.unknown_customlayout_profile,null);

            ImageView imageView_profile=(ImageView)view.findViewById(R.id.imageProfile);
            TextView textView_profile= (TextView)view.findViewById(R.id.textViewProfile);

            imageView_profile.setImageResource(IMAGES[position]);
            textView_profile.setText(NAMES[position]);
            return view;
        }
    }
}
