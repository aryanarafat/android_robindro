package com.yeasinrabbi.RabindranathTagoreQuote.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yeasinrabbi.RabindranathTagoreQuote.Activities.ListActivity;
import com.yeasinrabbi.RabindranathTagoreQuote.Cm.MainActivity;
import com.yeasinrabbi.RabindranathTagoreQuote.R;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.Constant;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.Utils;


public class HomeFragment extends Fragment implements View.OnClickListener {


    private static final String AD_UNIT_ID = "";
    private FrameLayout adContainerView;
    private boolean initialLayoutComplete = false;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_s, container, false);




        LinearLayout jiboni = view.findViewById(R.id.layout1);
        LinearLayout upanash = view.findViewById(R.id.layout2);
        LinearLayout science = view.findViewById(R.id.layout3);
        LinearLayout bises_rochona = view.findViewById(R.id.layout4);
        LinearLayout sada_shide_kotha = view.findViewById(R.id.layout5);
        LinearLayout golpo = view.findViewById(R.id.layout6);
        LinearLayout amar_science_mama = view.findViewById(R.id.layout7);
        LinearLayout aro_aktukhani_biggan = view.findViewById(R.id.layout8);
        LinearLayout akdojon_akjon = view.findViewById(R.id.layout9);
        LinearLayout aktu_khani_biggan = view.findViewById(R.id.layout10);
        LinearLayout kabil = view.findViewById(R.id.layout11);

        jiboni.setOnClickListener(this);
        upanash.setOnClickListener(this);
        science.setOnClickListener(this);
        bises_rochona.setOnClickListener(this);
        sada_shide_kotha.setOnClickListener(this);
        golpo.setOnClickListener(this);
        amar_science_mama.setOnClickListener(this);
        aro_aktukhani_biggan.setOnClickListener(this);
        akdojon_akjon.setOnClickListener(this);
        aktu_khani_biggan.setOnClickListener(this);
        kabil.setOnClickListener(this);



        TextView titel_textView2 = view.findViewById(R.id.titel_textView2);
        titel_textView2.setText(getString(R.string.sec_title));

        view.findViewById(R.id.open_menu).setOnClickListener(v->{

            ((MainActivity) requireActivity()).open();

        });


        Utils.extract(requireActivity(), msg -> {


        });

        return view;
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.layout1) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant1);
            intent.putExtra("name", getString(R.string.title_string1));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout2) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant2);
            intent.putExtra("name", getString(R.string.title_string2));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout3) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant3);
            intent.putExtra("name", getString(R.string.title_string3));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout4) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant4);
            intent.putExtra("name", getString(R.string.title_string4));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout5) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant5);
            intent.putExtra("name", getString(R.string.title_string5));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout6) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant6);
            intent.putExtra("name", getString(R.string.title_string6));
            startActivity(intent);
        }

        if (view.getId() == R.id.layout7) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant7);
            intent.putExtra("name", getString(R.string.title_string7));
            startActivity(intent);
        }
        if (view.getId() == R.id.layout8) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant8);
            intent.putExtra("name", getString(R.string.title_string8));
            startActivity(intent);
        }    if (view.getId() == R.id.layout9) {
            Intent intent = new Intent(requireActivity().getApplication(), ListActivity.class);
            intent.putExtra("Type", Constant.Constant9);
            intent.putExtra("name", getString(R.string.title_string9));
            startActivity(intent);
        }

    }




}