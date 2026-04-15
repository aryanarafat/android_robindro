package com.yeasinrabbi.RabindranathTagoreQuote.Cm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;
import com.yeasinrabbi.RabindranathTagoreQuote.AboutApp;
import com.yeasinrabbi.RabindranathTagoreQuote.Fragments.FavoriteFragment;
import com.yeasinrabbi.RabindranathTagoreQuote.Fragments.HistoryFragment;
import com.yeasinrabbi.RabindranathTagoreQuote.Fragments.HomeFragment;
import com.yeasinrabbi.RabindranathTagoreQuote.Fragments.SettingsFragment;
import com.yeasinrabbi.RabindranathTagoreQuote.R;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.Constant;
import com.yeasinrabbi.RabindranathTagoreQuote.Utils.ViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final int THEME_LIGHT = 0;
    private static final int THEME_DARK = 1;
    private static final String PREFS_NAME = "MyPrefs";
    private int selectedTheme;
    private static final String KEY_SELECTED_THEME = "SelectedTheme";
    SharedPreferences prefs;

    public static String night = "";
    public static String day = "";
    Toolbar toolbar;
    SwitchCompat switchCompat;
    private static final String DIALOG_SHOWN_KEY = "dialogShown";
    private androidx.appcompat.app.AlertDialog dialog;

    DrawerLayout drawer;

    private static final String ONESIGNAL_APP_ID = "229cc786-12ab-41de-887d-cbabf340ca34";

    public static String PACKAGE_NAME;

    public static String packagename;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);




        packagename = getApplicationContext().getPackageName();
        Constant.packageName = packagename;



        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(Constant.ONESIGNAL_APP_ID);
        OneSignal.promptForPushNotifications();


        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        selectedTheme = prefs.getInt(KEY_SELECTED_THEME, THEME_LIGHT);

        applyTheme();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        bottomnavigatoncontrol();

        navigationView.setNavigationItemSelectedListener(this);






    }

    public void open(){
        drawer.open();
    }



    private void Firsttimedailog() {

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        boolean dialogShown = prefs.getBoolean(DIALOG_SHOWN_KEY, false);

        if (!dialogShown) {


            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.first_time_dailog, null);
            materialAlertDialogBuilder.setView(view);
            materialAlertDialogBuilder.setBackground(getResources().getDrawable(android.R.color.transparent));


            TextView first_time_dailog_titel = view.findViewById(R.id.first_time_dailog_titel);
            TextView first_time_dailog_titel_version = view.findViewById(R.id.first_time_dailog_titel_version);
            TextView first_time_text = view.findViewById(R.id.dailog_details_text);
            TextView done_textview = view.findViewById(R.id.dailog_positive_button);
            TextView dailog_nagative_button = view.findViewById(R.id.dailog_nagative_button);
            TextView dailog_nautral_button = view.findViewById(R.id.dailog_nautral_button);

            dailog_nagative_button.setVisibility(View.GONE);
            dailog_nautral_button.setVisibility(View.GONE);

            first_time_dailog_titel.setText(R.string.app_name);
            first_time_text.setText("আমাদের এই এপ্লিকেশনে কোনো ধরনের ভূল চোখে পড়লে অথবা আপনার যেকোনো ধরণের পরামর্শ থাকলে তা জানালে আপনার কাছে কৃতজ্ঞ থাকবো।  আপনার রেটিং এবং শেয়ার আমাদের জন্য গুরুত্বপূর্ণ এবং উৎসাহব্যঞ্জক। ৫ স্টার রেটিং দিয়ে আপনার মূল্যবান মতামত লিখুন।");
            first_time_dailog_titel_version.setText(Constant.version);
            done_textview.setText("ঠিক আছে");


            ImageView dailog_imageview = view.findViewById(R.id.dailog_imageview);
            dailog_imageview.setImageResource(R.mipmap.ic_launcher_round);
            ImageView closeimageview = view.findViewById(R.id.clsoe_imageview);
            LinearLayout Donelinearlayout = view.findViewById(R.id.Done_linearlayout);


            closeimageview.setOnClickListener(v -> dialog.dismiss());
            Donelinearlayout.setOnClickListener(v -> {

                dialog.dismiss();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(DIALOG_SHOWN_KEY, true);
                editor.apply();

            });


            dialog = materialAlertDialogBuilder.create();
            dialog.show();


        }




    }

    public void Feedback() {



        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.first_time_dailog, null);
        materialAlertDialogBuilder.setView(view);
        materialAlertDialogBuilder.setBackground(getResources().getDrawable(android.R.color.transparent));


        TextView first_time_dailog_titel = view.findViewById(R.id.first_time_dailog_titel);
        TextView first_time_dailog_titel_version = view.findViewById(R.id.first_time_dailog_titel_version);
        TextView dailog_details_textview = view.findViewById(R.id.dailog_details_text);
        TextView dailog_positive_button = view.findViewById(R.id.dailog_positive_button);
        TextView dailog_nautral_button = view.findViewById(R.id.dailog_nautral_button);
        TextView dailog_nagative_button = view.findViewById(R.id.dailog_nagative_button);
        TextView dailog_titel_textview = view.findViewById(R.id.dailog_titel_textview);

        dailog_titel_textview.setVisibility(View.VISIBLE);
        dailog_titel_textview.setText("মতামত");

        first_time_dailog_titel.setText(R.string.app_name);
        dailog_details_textview.setText("আমাদের এই এপ্লিকেশনে কোনো ধরনের ভূল চোখে পড়লে অথবা আপনার যেকোনো ধরণের পরামর্শ থাকলে তা জানালে আপনার কাছে কৃতজ্ঞ থাকবো।  আপনার রেটিং এবং শেয়ার আমাদের জন্য গুরুত্বপূর্ণ এবং উৎসাহব্যঞ্জক। ৫ স্টার রেটিং দিয়ে আপনার মূল্যবান মতামত লিখুন।");
        first_time_dailog_titel_version.setText("Version 2.5");

        dailog_positive_button.setText("প্লে স্টোরে ");
        dailog_nautral_button.setText("ইমেইল করুন");
        dailog_nagative_button.setText("হোয়াটসঅ্যাপ অথবা ডায়াল");


        ImageView dailog_imageview = view.findViewById(R.id.dailog_imageview);
        dailog_imageview.setImageResource(R.mipmap.ic_launcher_round);
        ImageView closeimageview = view.findViewById(R.id.clsoe_imageview);
        LinearLayout Donelinearlayout = view.findViewById(R.id.Done_linearlayout);


        closeimageview.setOnClickListener(v -> dialog.dismiss());
        dailog_nautral_button.setOnClickListener(v -> {



            Email_Us();
            dialog.dismiss();


        });


        dailog_positive_button.setOnClickListener(v -> {

            Rate_Apps();
            dialog.dismiss();

        });

        dailog_nagative_button.setOnClickListener(v -> {


            Phone();
            dialog.dismiss();

        });



        dialog = materialAlertDialogBuilder.create();
        dialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_rate) {


         Rate_Apps();


            return true;
        }


        if (id == R.id.menu_share) {


            Share();

            return true;
        }

        if (id == R.id.menu_about) {

            Intent intent = new Intent(MainActivity.this, AboutApp.class);
            startActivity(intent);

            return true;
        }


        if (id == R.id.developer) {


            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    SmoothBottomBar bottomNavigationView;
    ViewPager viewPager;
    private void bottomnavigatoncontrol() {

          viewPager = findViewById(R.id.nav_host_fragment_activity_main);


        HomeFragment homeFragment = new HomeFragment();
        HistoryFragment historyFragment = new HistoryFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        List<Fragment> fragments =  new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(historyFragment);
        fragments.add(favoriteFragment);
        fragments.add(settingsFragment);


        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager(),0,fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);






          bottomNavigationView = findViewById(R.id.bottom_nav_view);





        bottomNavigationView.setOnItemSelectedListener((OnItemSelectedListener) i -> {

            viewPager.setCurrentItem(i,false);




            return true;
        });



    }


    public void Email_Us() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("mailto:".concat("yeasinrabbi789@gmail.com".concat("?subject=".concat("মতামত / পরামর্শ : "+getString(R.string.app_name))))));
        startActivity(intent);
    }


  public void Phone() {

      Uri uri = Uri.parse("tel:+8801797540278");
      Intent intent = new Intent(Intent.ACTION_DIAL, uri);
      startActivity(intent);;
    }


    public void applyTheme() {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(KEY_SELECTED_THEME, selectedTheme);
        editor.apply();

        if (viewPager != null)
         viewPager.setCurrentItem(0);

        switch (selectedTheme) {
            case THEME_LIGHT:


                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                break;
            case THEME_DARK:

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                break;
        }
    }

    public void showThemeDialog() {

        String[] themes = new String[]{"Light Theme", "Dark Theme", "Sync with OS Default Theme"};
        int checkedItem;
        switch (selectedTheme) {
            case THEME_LIGHT:
                checkedItem = 0;
                break;
            case THEME_DARK:
                checkedItem = 1;
                break;
            default:
                checkedItem = 2;
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Theme")
                .setSingleChoiceItems(themes, checkedItem, (dialogInterface, selectedIndex) -> {
                    switch (selectedIndex) {
                        case 0:
                            selectedTheme = THEME_LIGHT;
                            break;
                        case 1:
                            selectedTheme = THEME_DARK;
                            break;
                        case 2:
                            syncWithOsDefaultTheme();
                            break;
                    }
                    dialogInterface.dismiss();
                    applyTheme();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void syncWithOsDefaultTheme() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        int currentNightMode = uiModeManager.getNightMode();
        switch (currentNightMode) {
            case UiModeManager.MODE_NIGHT_NO:
                selectedTheme = THEME_LIGHT;
                break;
            case UiModeManager.MODE_NIGHT_YES:
                selectedTheme = THEME_DARK;
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

    /*    Group 1 */

        if (id == R.id.feedback) {

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            }

            Feedback();

        } else if (id == R.id.donate) {

            // do something

        } else if (id == R.id.update) {

            Rate_Apps();

        } else if (id == R.id.tafsir_and_writer) {

            // do something

        }

      /*  Group 2 */

        else if (id == R.id.book1) {

            /* আরিফ আজাদ সমগ্র  */

            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+Constant.promo_apps1));
            startActivity(intent);

        }

        else if (id == R.id.book2) {
          /*  জাফর ইকবাল সমগ্র  */

            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+Constant.promo_apps2));
            startActivity(intent);


        } else if (id == R.id.book3) {

         /*   রবীন্দ্রনাথ সমগ্র */

            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+Constant.promo_apps3));
            startActivity(intent);

        }

    /*    Group 3  */

        else if (id == R.id.rating) {

            Rate_Apps();

        } else if (id == R.id.moreapps) {

            More_Apps();

        } else if (id == R.id.privacy_policy) {
           // do something

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            }
            Privacy();



        } else if (id == R.id.about_app) {

            // About Activity

            Intent intent = new Intent(MainActivity.this,AboutApp.class);
            startActivity(intent);

        } else if (id == R.id.contact_us) {

          /*  Email contract */

            Email_Us();

        } else if (id == R.id.aryanarafat) {

           // do something Developer Dailog

        }


        return false;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {


            final BottomSheetDialog dialog =  new BottomSheetDialog(this,R.style.BottomSheetDialog); // Style here

            dialog.setContentView(R.layout.bottom_sheet);
            dialog.setCanceledOnTouchOutside(true);


            ImageView botton_icon = dialog.findViewById(R.id.botton_icon);
            TextView botton_app_titel = dialog.findViewById(R.id.botton_app_titel);
            TextView botton_version = dialog.findViewById(R.id.botton_version);


            assert botton_icon != null;
            botton_icon.setImageResource(R.mipmap.ic_launcher);
            assert botton_app_titel != null;
            botton_app_titel.setText(getString(R.string.app_name));
            assert botton_version != null;
            botton_version.setText("Version "+getString(R.string.version));

            LinearLayout  bottom_share  = dialog.findViewById(R.id.bottom_share);
            LinearLayout  bottom_more_apps  = dialog.findViewById(R.id.bottom_more_apps);
            LinearLayout   bottom_rate = dialog.findViewById(R.id.bottom_rate);
            LinearLayout  bottom_cancel  = dialog.findViewById(R.id.bottom_cancel);
            LinearLayout  bottom_exit  = dialog.findViewById(R.id.bottom_exit);

            assert bottom_share != null;
            bottom_share.setOnClickListener(v -> {

               Share();

            });
            assert bottom_more_apps != null;
            bottom_more_apps.setOnClickListener(v -> {


               More_Apps();

            });
            assert bottom_rate != null;
            bottom_rate.setOnClickListener(v -> {

               Rate_Apps();

            });
            assert bottom_cancel != null;
            bottom_cancel.setOnClickListener(v -> {


               Email_Us();

            });
            assert bottom_exit != null;
            bottom_exit.setOnClickListener(v -> {

              finish();

            });


            dialog.show();

        }

    }




    @SuppressLint("SetTextI18n")
    public   void Privacy(){


        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.first_time_dailog, null);
        materialAlertDialogBuilder.setView(view);
        materialAlertDialogBuilder.setBackground(getResources().getDrawable(android.R.color.transparent));


        TextView first_time_dailog_titel = view.findViewById(R.id.first_time_dailog_titel);
        TextView first_time_dailog_titel_version = view.findViewById(R.id.first_time_dailog_titel_version);
        TextView dailog_details_textview = view.findViewById(R.id.dailog_details_text);
        TextView dailog_positive_button = view.findViewById(R.id.dailog_positive_button);
        TextView dailog_nautral_button = view.findViewById(R.id.dailog_nautral_button);
        TextView dailog_nagative_button = view.findViewById(R.id.dailog_nagative_button);
        TextView dailog_titel_textview = view.findViewById(R.id.dailog_titel_textview);

        dailog_titel_textview.setVisibility(View.VISIBLE);
        dailog_titel_textview.setText("Privacy Policy");

        first_time_dailog_titel.setText(R.string.app_name);
        dailog_details_textview.setText("Privacy Policy\n" +
                "Yeasin Rabbee built the "+getString(R.string.app_name)+" app as a Free app. This SERVICE is provided by Yeasin Rabbee at no cost and is intended for use as is.\n" +
                "\n" +
                "This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.\n" +
                "\n" +
                "If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.\n" +
                "\n" +
                "The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which are accessible at "+getString(R.string.app_name)+" unless otherwise defined in this Privacy Policy.\n" +
                "\n" +
                "Information Collection and Use\n" +
                "\n" +
                "For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information. The information that I request will be retained on your device and is not collected by me in any way.\n" +
                "\n" +
                "The app does use third-party services that may collect information used to identify you.\n" +
                "\n" +
                "Link to the privacy policy of third-party service providers used by the app\n" +
                "\n" +
                "Google Play Services\n" +
                "AdMob\n" +
                "Google Analytics for Firebase\n" +
                "Firebase Crashlytics\n" +
                "Facebook\n" +
                "Appodeal\n" +
                "Unity\n" +
                "One Signal\n" +
                "AppLovin\n" +
                "StartApp\n" +
                "Log Data\n" +
                "\n" +
                "I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.\n" +
                "\n" +
                "Cookies\n" +
                "\n" +
                "Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.\n" +
                "\n" +
                "This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.\n" +
                "\n" +
                "Service Providers\n" +
                "\n" +
                "I may employ third-party companies and individuals due to the following reasons:\n" +
                "\n" +
                "To facilitate our Service;\n" +
                "To provide the Service on our behalf;\n" +
                "To perform Service-related services; or\n" +
                "To assist us in analyzing how our Service is used.\n" +
                "I want to inform users of this Service that these third parties have access to their Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.\n" +
                "\n" +
                "Security\n" +
                "\n" +
                "I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.\n" +
                "\n" +
                "Links to Other Sites\n" +
                "\n" +
                "This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.\n" +
                "\n" +
                "Children’s Privacy\n" +
                "\n" +
                "These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13 years of age. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do the necessary actions.\n" +
                "\n" +
                "Changes to This Privacy Policy\n" +
                "\n" +
                "I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page.\n" +
                "\n" +
                "This policy is effective as of 2023-06-18\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me at \n" +
                "yeasinrabbi789@gmail.com.");

        first_time_dailog_titel_version.setText(Constant.version);

        dailog_positive_button.setText("Ok ");
        dailog_nautral_button.setText("Developer contact");
        dailog_nagative_button.setVisibility(View.GONE);


        ImageView dailog_imageview = view.findViewById(R.id.dailog_imageview);
        dailog_imageview.setImageResource(R.mipmap.ic_launcher_round);
        ImageView closeimageview = view.findViewById(R.id.clsoe_imageview);
        LinearLayout Donelinearlayout = view.findViewById(R.id.Done_linearlayout);


        closeimageview.setOnClickListener(v -> dialog.dismiss());
        dailog_nautral_button.setOnClickListener(v -> {




            Email_Us();


        });


        dailog_positive_button.setOnClickListener(v -> {


            dialog.dismiss();

        });


        dialog = materialAlertDialogBuilder.create();
        dialog.show();



    }



    public void Share(){

        String title = getString(R.string.app_name)+ "' এপ্লিকেশনটি আমার খুবই ভালো লেগেছে, আশা করি আপনারও অনেক ভালো লাগবে। নিচের লিংক থেকে আপনি অ্যাপ ইনস্টল করতে পারবেন।";
        String link = "https://play.google.com/store/apps/details?id="+ packagename;
        String code = title.concat("\n\n".concat(link));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", code);
        intent.putExtra("android.intent.extra.SUBJECT", "হুমায়ুন আহমেদ সমগ্র");
        startActivity(Intent.createChooser(intent, "Share App using"));
    }



    public void More_Apps() {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id="+Constant.developer_id));
        startActivity(intent);
    }


    public void Rate_Apps() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+ packagename));
        startActivity(intent);


    }



}



