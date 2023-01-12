package com.example.huangyoude.sa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_Personalfile_MyCollection extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ListView listviewCollection;
    String[] CollectionName ={"輔大(5)", "台北車站(2)"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myCollectionView = inflater.inflate(R.layout.fragment_personalfile_mycollection, container, false);
        //setContentView(R.layout.activity_my_collection);
        listviewCollection= myCollectionView.findViewById (R.id.listviewMyCollection);
        //create instance of class MyAdapter
        MyAdapter adapter= new MyAdapter(getActivity(),CollectionName);
        //set Adapter to list
        listviewCollection.setAdapter(adapter);
        listviewCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                if(position==0){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_CollectList())
                            .addToBackStack(null)
                            .commit();
                    //intent.setClass(getActivity(), Fragment_Personalfile_CollectList.class);
                }
                if(position==1){
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_FJURestaurantList())
                            .addToBackStack(null)
                            .commit();
                    //intent.setClass(getActivity(), Fragment_Personalfile_FJURestaurantList.class);
                }

            }
        });

        return myCollectionView;
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String MyCollectionName[];
        MyAdapter(Context c,String[] CollectionName){
            super(c,R.layout.unknown_my_collection_listview,R.id.TextViewCollectionName,CollectionName);
            this.context=c;
            this.MyCollectionName=CollectionName;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater inflater = getLayoutInflater();
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.unknown_my_collection_listview,parent,false);
            TextView MyCollectionName=row.findViewById(R.id.TextViewCollectionName);
            MyCollectionName.setText(CollectionName[position]);
            return row;
        }
    }
}