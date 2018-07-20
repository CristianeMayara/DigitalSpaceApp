package com.cristiane.joyjetapp.Activities;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.R;
import com.cristiane.joyjetapp.viewmodel.ArticleViewModel;
import com.cristiane.joyjetapp.viewmodel.CategoryListViewModel;

/**
 * Created by cristiane on 28/06/2018.
 */

public class ArticleActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String TAG = ArticleActivity.class.getSimpleName();
    public static final String ARG_ARTICLE = "arg_article";

    private TextView tvTitle;
    private TextView tvCategory;
    private TextView tvText;
    private ImageView ivImage;
    private Article article;
    private Drawable starDrawable;

    private ArticleViewModel viewmodel;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initComponents();
        setArguments();
        initViewModel();
    }

    private void initComponents() {
        tvTitle = findViewById(R.id.tv_article_title);
        tvCategory = findViewById(R.id.tv_article_category);
        tvText = findViewById(R.id.tv_article_text);
        ivImage = findViewById(R.id.iv_article_image);

        Typeface tfLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");
        Typeface tfSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.otf");
        tvText.setTypeface(tfLight);
        tvTitle.setTypeface(tfSemiBold);
        tvCategory.setTypeface(tfLight);

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparent));
        }
    }

    private void setArguments() {
        if (getIntent() != null) {
            article = getIntent().getExtras().getParcelable(ARG_ARTICLE);
            tvTitle.setText(article.getTitle().toUpperCase());
            tvCategory.setText(getCategoryName(article.getCategory()).toLowerCase());
            tvText.setText(article.getDescription());
//            ivImage.setImageResource(article.getImageId());
        }
    }

    private String getCategoryName(int categoryId) {
        switch(categoryId) {
            case 1:
                return "Places";
            case 2:
                return "My Life";
            default:
                return "Others";
        }
    }

    private void initViewModel() {
        viewmodel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        attachObserver(viewmodel);
    }

    private void attachObserver(ArticleViewModel viewModel) {
        viewModel.getArticle().observe(this, new Observer<Article>() {
            @Override
            public void onChanged(@Nullable Article article) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article, menu);

        starDrawable = menu.getItem(0).getIcon();
        if(starDrawable != null) starDrawable.mutate();

        if (article.isFavorite())
            setYellowColor();
        else
            setWhiteColor();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                viewmodel.toggleFavorite(this, article);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setWhiteColor() {
        starDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
    }

    public void setYellowColor() {
        starDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow), PorterDuff.Mode.SRC_ATOP);
    }

    public void showMarkedSuccessfully() {
        Toast.makeText(this, getString(R.string.successfully_marked), Toast.LENGTH_SHORT).show();
    }

    public void showRemovedSuccessfully() {
        Toast.makeText(this, getString(R.string.successfully_removed), Toast.LENGTH_SHORT).show();
    }
}
