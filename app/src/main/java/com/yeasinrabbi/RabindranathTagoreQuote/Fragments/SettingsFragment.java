package com.yeasinrabbi.RabindranathTagoreQuote.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.yeasinrabbi.RabindranathTagoreQuote.Activities.ListOfContentActivity;
import com.yeasinrabbi.RabindranathTagoreQuote.Beans.Category;
import com.yeasinrabbi.RabindranathTagoreQuote.Cm.MainActivity;
import com.yeasinrabbi.RabindranathTagoreQuote.R;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.Constant;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.Utils;

import java.io.File;

public class SettingsFragment extends Fragment {



    private TextView myTextView;
    private TextView version;

    public  static  String packagename;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        packagename = requireActivity().getPackageName();


        myTextView = view.findViewById(R.id.t4);
        version = view.findViewById(R.id.version);
        version.setText("version " +getString(R.string.version));

        LinearLayout cleardate = view.findViewById(R.id.clearcache);


        LinearLayout share_app = view.findViewById(R.id.share_app);
        LinearLayout moreapp = view.findViewById(R.id.moreapp);
        LinearLayout rateus = view.findViewById(R.id.rateus);
        LinearLayout themeLayout = view.findViewById(R.id.themeLayout);


        TextView privacy = view.findViewById(R.id.privacy);

        privacy.setOnClickListener(v -> {

            ((MainActivity)requireActivity()).Privacy();
            Snackbar.make(v, " Privacy", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        });


        themeLayout.setOnClickListener(v -> ((MainActivity)requireActivity()).showThemeDialog());



        share_app.setOnClickListener(v -> {

            ((MainActivity)requireActivity()).Share();

        });

        moreapp.setOnClickListener(v -> {


            ((MainActivity)requireActivity()).More_Apps();

        });

        rateus.setOnClickListener(v -> {

            ((MainActivity)requireActivity()).Rate_Apps();

        });



        cleardate.setOnClickListener(v -> clearAppCache());

        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tagd", "settings distory");

    }


    public void clearAppCache() {


        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Clear App Data");
        builder.setMessage("Are you sure you want to clear all data for this app? This action cannot be undone.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Context context = requireContext();
            try {
                File cacheDir = context.getCacheDir();
                if (cacheDir != null && cacheDir.isDirectory()) {
                    deleteDir(cacheDir);
                }
                File externalCacheDir = context.getExternalCacheDir();
                if (externalCacheDir != null && externalCacheDir.isDirectory()) {
                    deleteDir(externalCacheDir);
                }
                Toast.makeText(context, "App cache cleared successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Failed to clear app cache", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();


    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    private void Share() {

        String title = getString(R.string.app_name)+ "' এপ্লিকেশনটি আমার খুবই ভালো লেগেছে, আশা করি আপনারও অনেক ভালো লাগবে। নিচের লিংক থেকে আপনি অ্যাপ ইনস্টল করতে পারবেন।";
        String link = "https://play.google.com/store/apps/details?id="+Constant.packageName;
        String code = title.concat("\n\n".concat(link));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", code);
        intent.putExtra("android.intent.extra.SUBJECT", "হুমায়ুন আহমেদ সমগ্র");
        startActivity(Intent.createChooser(intent, "Share App using"));

    }


    private void Email_Us() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("mailto:".concat("aryan.arafat.yr@gmail.com".concat("?subject=".concat("মতামত / পরামর্শ : হুমায়ুন আহমেদ সমগ্র")))));
        startActivity(intent);
    }





}





