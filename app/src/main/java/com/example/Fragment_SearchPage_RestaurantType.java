package com.example.huangyoude.sa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Fragment_SearchPage_RestaurantType extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LayoutInflater inflater1 =  getLayoutInflater();
        View typeView = inflater.inflate(R.layout.fragment_searchpage_restauranttype, null);
        ListView typeList = (ListView) typeView.findViewById(R.id.ratingBar);
        //ListView 要顯示的內容
        String[] str = {"" +
                "\n 日式\n",
                "\n 義式\n",
                "\n 韓式\n",
                "\n 泰式\n",
                "\n 美式\n",
                "\n 和菜\n",
                "\n 牛排\n",
                "\n 壽司\n",
                "\n 下午茶\n",
                "\n 冰品\n"};
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        //ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, str);
        //typeList.setAdapter(adapter);
        return typeView;
    }
}