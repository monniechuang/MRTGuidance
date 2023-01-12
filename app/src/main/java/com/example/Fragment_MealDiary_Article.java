package com.example.huangyoude.sa;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_MealDiary_Article extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View articleView = inflater.inflate(R.layout.fragment_personalfile_article, container, false);
        //setContentView(R.layout.activity_article);

        return articleView;
    }
}


