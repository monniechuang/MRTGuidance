package com.example.huangyoude.sa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_SearchPage_Search extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View searchPageView = inflater.inflate(R.layout.fragment_searchpage_search, container, false);
        //setContentView(R.layout.activity_search_page);

        final EditText sv = searchPageView.findViewById(R.id.editText);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run()
            {
                InputMethodManager m = (InputMethodManager)
                        sv.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m.showSoftInput(sv,InputMethodManager.SHOW_FORCED);
            }
        }, 200);

        return  searchPageView;
    }
}
