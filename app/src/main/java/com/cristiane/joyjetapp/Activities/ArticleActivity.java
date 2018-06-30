package com.cristiane.joyjetapp.Activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristiane.joyjetapp.Model.Article;
import com.cristiane.joyjetapp.R;

/**
 * Created by cristiane on 28/06/2018.
 */

public class ArticleActivity extends AppCompatActivity {

    public static final String TAG = "ArticleActivity";
    public static final String ARG_ARTICLE = "arg_article";
    private TextView tvTitle;
    private TextView tvCategory;
    private TextView tvText;
    private ImageView ivImage;
    private ImageView ivStar;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initComponents();
        setArguments();
        updateStarColor();
    }

    private void initComponents() {
        tvTitle = findViewById(R.id.tv_article_title);
        tvCategory = findViewById(R.id.tv_article_category);
        tvText = findViewById(R.id.tv_article_text);
        ivImage = findViewById(R.id.iv_article_image);
        ivStar = findViewById(R.id.iv_toolbar_star);
        ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFavorite();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //FontsOverride.setDefaultFont(this, "sans-serif", "fonts/MontserratAlternates-ExtraBold.otf");

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setArguments() {
        if (getIntent() != null) {
            article = getIntent().getExtras().getParcelable(ARG_ARTICLE);
            tvTitle.setText(article.getTitle().toUpperCase());
            tvCategory.setText(getCategoryName(article.getCategory()).toLowerCase());
            tvText.setText(article.getText());
            ivImage.setImageResource(article.getImageId());
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void toggleFavorite() {
        BaseActivity.dataUtil.toggleFavorite(article.getId());
        article.setFavorite(!article.isFavorite());
        updateStarColor();
    }

    private void updateStarColor() {
        if(article.isFavorite())
            ivStar.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow));
        else
            ivStar.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
    }
}
