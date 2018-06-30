package com.cristiane.joyjetapp.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cristiane.joyjetapp.Fragments.CategoryListFragment;
import com.cristiane.joyjetapp.Fragments.FavoriteListFragment;
import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.Util.DataUtil;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "BaseActivity";

    private Toolbar toolbar;
    DrawerLayout drawer;
    public static DataUtil dataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initComponents();

        Fragment fragment = CategoryListFragment.newInstance();
        commitFragment(fragment, CategoryListFragment.TAG);
        getSupportActionBar().setTitle(getString(R.string.category_list_screen_title));
    }

    private void initComponents() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataUtil = new DataUtil(this);
        setDrawerMenu();
    }

    private void setDrawerMenu() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            Fragment fragment = CategoryListFragment.newInstance();
            commitFragment(fragment, CategoryListFragment.TAG);
            getSupportActionBar().setTitle(getString(R.string.category_list_screen_title));
            
        } else if (id == R.id.nav_favorite) {
            Fragment fragment = FavoriteListFragment.newInstance();
            commitFragment(fragment, FavoriteListFragment.TAG);
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
