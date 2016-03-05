/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.support.android.designlibdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.support.android.designlibdemo.CategoryFragment;
import com.support.android.designlibdemo.R;
import com.support.android.designlibdemo.model.Category;
import com.support.android.designlibdemo.model.CategoryContainer;
import com.support.android.designlibdemo.model.FinalData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CHEEEESE";

    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager1;
    private ViewPager viewPager2;
    private ViewPager viewPager3;
    private TabLayout tabLayout1;
    private TabLayout tabLayout2;
    private TabLayout tabLayout3;

    Adapter adapter;
    Adapter adapter2;
    Adapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate");
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        tabLayout1 = (TabLayout) findViewById(R.id.tabs1);
        tabLayout2 = (TabLayout) findViewById(R.id.tabs2);
        tabLayout3 = (TabLayout) findViewById(R.id.tabs3);
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        viewPager2 = (ViewPager) findViewById(R.id.viewpager2);
        viewPager3 = (ViewPager) findViewById(R.id.viewpager3);

//        if (viewPager1 != null) {
            setupViewPager(viewPager1, FinalData.one, adapter);
            tabLayout1.setupWithViewPager(viewPager1);
//        }
//        if (viewPager2 != null) {
            setupViewPager(viewPager2, FinalData.two, adapter2);
            tabLayout2.setupWithViewPager(viewPager2);
//        }
//        if (viewPager3 != null) {
            setupViewPager(viewPager3, FinalData.three, adapter3);
            tabLayout3.setupWithViewPager(viewPager3);
//        }

        viewPager2.setVisibility(View.GONE);
        viewPager3.setVisibility(View.GONE);
        tabLayout2.setVisibility(View.GONE);
        tabLayout3.setVisibility(View.GONE);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    boolean drawerOpened = false;
    @Override
    protected void onResume() {
        super.onResume();
        if (!drawerOpened) mDrawerLayout.openDrawer(GravityCompat.START);
        drawerOpened = true;
    }

    private void setupViewPager(ViewPager viewPager, CategoryContainer categoryContainer, Adapter adapter) {
        Log.d(TAG, "setupViewPager - " + categoryContainer.getName());
        adapter = new Adapter(getSupportFragmentManager());
        for (Category cat : categoryContainer.getCategories()) {
            CategoryFragment fragment = new CategoryFragment();
            Bundle arguments = new Bundle();
            arguments.putParcelable("data", cat);
            fragment.setArguments(arguments);
            adapter.addFragment(fragment, cat.getTitle());
        }
        viewPager.setAdapter(adapter);
    }

    private void setViewPagerVisibility(ViewPager vp) {
        switch (vp.getId()){
            case R.id.viewpager1: {
                Log.e("????", "first");
                viewPager1.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);
                viewPager3.setVisibility(View.GONE);
                tabLayout1.setVisibility(View.VISIBLE);
                tabLayout2.setVisibility(View.GONE);
                tabLayout3.setVisibility(View.GONE);
                break;
            }
            case R.id.viewpager2: {
                Log.e("????", "second");
                viewPager1.setVisibility(View.GONE);
                viewPager2.setVisibility(View.VISIBLE);
                viewPager3.setVisibility(View.GONE);
                tabLayout1.setVisibility(View.GONE);
                tabLayout2.setVisibility(View.VISIBLE);
                tabLayout3.setVisibility(View.GONE);
                break;
            }
            case R.id.viewpager3: {
                Log.e("????", "third");
                viewPager1.setVisibility(View.GONE);
                viewPager2.setVisibility(View.GONE);
                viewPager3.setVisibility(View.VISIBLE);
                tabLayout1.setVisibility(View.GONE);
                tabLayout2.setVisibility(View.GONE);
                tabLayout3.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.nav_map:
                                Intent intent = new Intent(MainActivity.this, SurveyActivity.class);
//                                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_young:
                                Toast.makeText(MainActivity.this, "tip 1", Toast.LENGTH_SHORT).show();
                                setViewPagerVisibility(viewPager1);
                                break;
                            case R.id.nav_fault:
                                Toast.makeText(MainActivity.this, "tip 2", Toast.LENGTH_SHORT).show();
                                setViewPagerVisibility(viewPager2);
                                break;
                            case R.id.nav_active:
                                Toast.makeText(MainActivity.this, "tip 3", Toast.LENGTH_SHORT).show();
                                setViewPagerVisibility(viewPager3);
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
