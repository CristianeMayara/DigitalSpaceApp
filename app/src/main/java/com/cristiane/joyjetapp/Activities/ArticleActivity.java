package com.cristiane.joyjetapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initComponents();
        setArguments();
    }

    private void initComponents() {
        tvTitle = findViewById(R.id.tv_article_title);
        tvCategory = findViewById(R.id.tv_article_category);
        tvText = findViewById(R.id.tv_article_text);
        ivImage = findViewById(R.id.iv_article_image);

        //FontsOverride.setDefaultFont(this, "sans-serif", "fonts/MontserratAlternates-ExtraBold.otf");

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_star);
    }

    private void setArguments() {
        if (getIntent() != null) {
            article = getIntent().getExtras().getParcelable(ARG_ARTICLE);
            tvTitle.setText(article.getTitle().toUpperCase());
            tvCategory.setText(getCategoryName(article.getCategory()).toLowerCase());
            tvText.setText(article.getText());
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
}
