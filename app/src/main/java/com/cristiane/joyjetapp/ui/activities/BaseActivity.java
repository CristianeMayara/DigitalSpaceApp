package com.cristiane.joyjetapp.ui.activities;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cristiane.joyjetapp.model.Article;
import com.cristiane.joyjetapp.ui.fragments.CategoryListFragment;
import com.cristiane.joyjetapp.ui.fragments.FavoriteListFragment;
import com.cristiane.joyjetapp.model.ArticleTypeItem;
import com.cristiane.joyjetapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = BaseActivity.class.getSimpleName();

    private Toolbar toolbar;
    public DrawerLayout drawer;

    public static List<String> headerCache;
    public static HashMap<String, List<Article>> childCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initComponents();

        commitFragment(CategoryListFragment.newInstance(), CategoryListFragment.TAG);
        getSupportActionBar().setTitle(getString(R.string.category_list_screen_title));
    }

    private void initComponents() {
        headerCache = new ArrayList<>();
        childCache = new HashMap<>();
        setToolbar();
        setDrawerMenu();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);

        for(int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);

            if(view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");
                if(tv.getText().equals(toolbar.getTitle())) {
                    tv.setTypeface(titleFont);
                    tv.setTextColor(getResources().getColor(R.color.colorBlue));
                    break;
                }
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorWhite));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparent));
        }
    }

    private void setDrawerMenu() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tv = navigationView.getHeaderView(0).findViewById(R.id.tv_navigation);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.otf");
        tv.setTypeface(font);
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            commitFragment(CategoryListFragment.newInstance(), CategoryListFragment.TAG);
            getSupportActionBar().setTitle(getString(R.string.category_list_screen_title));

        } else if (id == R.id.nav_favorite) {
            commitFragment(FavoriteListFragment.newInstance(), FavoriteListFragment.TAG);
            getSupportActionBar().setTitle(getString(R.string.favorite_list_screen_title));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void commitFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment, tag)
                .addToBackStack(tag)
//                .setCustomAnimations(
//                        R.anim.card_flip_right_in,
//                        R.anim.card_flip_right_out,
//                        R.anim.card_flip_left_in,
//                        R.anim.card_flip_left_out)
//                .setCustomAnimations(R.anim.res_anim_fadein, R.anim.res_anim_fadeout)
                .commitAllowingStateLoss();
    }
}
