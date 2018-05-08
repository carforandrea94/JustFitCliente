package com.example.andreacarfora.justfit_cliente.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.andreacarfora.justfit_cliente.Fragments.DiarioFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.HomeFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.ProfiloFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.ProgressiFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.WorkoutFragment;
import com.example.andreacarfora.justfit_cliente.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.andreacarfora.justfit_cliente.R.drawable.bottom_shadow;
import static com.example.andreacarfora.justfit_cliente.R.drawable.ic_edit;


public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView button_Dx, button_Sx;
    private String UNSELECTED_TAB_COLOR="#bcbcbc";//"#99FFFFFF";

    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_person,
            R.drawable.dumbbell,
            R.drawable.food_apple,
            R.drawable.ic_timeline
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor(UNSELECTED_TAB_COLOR), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setUpTabIcons();
    }

    private void bindUI(){
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        TextView textView = findViewById(R.id.textLogoMain);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        textView.setTypeface(font);

        button_Dx = findViewById(R.id.buttonDx);
        button_Sx = findViewById(R.id.buttonSx);

    }


    private void setUpTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor(UNSELECTED_TAB_COLOR), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor(UNSELECTED_TAB_COLOR), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor(UNSELECTED_TAB_COLOR), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(4).getIcon().setColorFilter(Color.parseColor(UNSELECTED_TAB_COLOR), PorterDuff.Mode.SRC_IN);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new ProfiloFragment(), "PROFILE");
        adapter.addFragment(new WorkoutFragment(), "WORKOUT");
        adapter.addFragment(new DiarioFragment(), "Diario");
        adapter.addFragment(new ProgressiFragment(), "Progressi");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return  mFragmentList.get(position);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


    public void editProfile(){
        button_Dx.setImageResource(R.drawable.ic_edit);
        button_Dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModificaProfiloActivity.class);
                startActivity(intent);
            }
        });
    }


}
