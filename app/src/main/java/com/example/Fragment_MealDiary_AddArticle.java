package com.example.huangyoude.sa;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

public class Fragment_MealDiary_AddArticle extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Button buttonSubmitArticle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View addArticleView = inflater.inflate(R.layout.fragment_personalfile_addarticle, container, false);
        //setContentView(R.layout.activity_add_article);
        buttonSubmitArticle = (Button) addArticleView.findViewById(R.id.buttonSubmitArticle);


        buttonSubmitArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                AlertDialog.Builder mBuider = new AlertDialog.Builder(getActivity());
                mBuider.setTitle("確認通知");
                mBuider.setIcon(R.drawable.star);
                mBuider.setMessage("確認輸入店家資訊?");
                mBuider.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
                mBuider.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fragmentTransaction.replace(R.id.diarylayout, new Fragment_MealDiary_Article())
                                .addToBackStack(null)
                                .commit();
                    }
                }).show();
            }


        });

        return addArticleView;
    }


}

