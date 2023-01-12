package com.example.huangyoude.sa;


import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_unknown_EditMealDiary extends android.support.v4.app.Fragment {
    private Button btnSubmit;

    //設定會用到的Fragment變數
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment mealDiary = new Fragment_unknown_MealDiary();
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View editMealDiary = inflater.inflate(R.layout.temp_editmealdiary, container, false);
        btnSubmit = (Button) editMealDiary.findViewById(R.id.btnSubmit);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuider = new AlertDialog.Builder(view.getContext());
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
                        //fragmentTransaction.add(R.id.viewPager  , fragment);
                        //fragmentTransaction.commit();
                        fragmentTransaction.replace(R.id.diarylayout, new Fragment_unknown_MealDiary()).commit();
                    }
                }
                ).show();
            }
        });

        return editMealDiary;
    }

}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuider = new AlertDialog.Builder(MainActivity.this);
                mBuider.setTitle("確認通知");
                mBuider.setIcon(R.drawable.star);
                mBuider.setMessage("確認輸入店家資訊?");
                mBuider.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        });

    }*/

