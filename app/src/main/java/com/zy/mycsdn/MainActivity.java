package com.zy.mycsdn;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zy.mycsdn.Adapter.PagerAdapter;
import com.zy.mycsdn.Model.BlogAuthor;
import com.zy.mycsdn.Ui.AboutActivity;
import com.zy.mycsdn.Ui.SettingsActivity;
import com.zy.mycsdn.Util.JsoupUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        SimpleDraweeView top_image = (SimpleDraweeView) findViewById(R.id.top_image);
        ViewPager viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        top_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        new LoadImageAsync().execute();
    }

    /**
     * 菜单点击响应函数
     *
     * @param item 菜单
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        return false;
    }

    /**
     * 重写返回键响应函数
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 用来加载主界面顶部头像以及侧边栏顶部头像
     */
    private class LoadImageAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            BlogAuthor blogAuthor = JsoupUtil.getBlogAutoMessage();
            String avatarUrl = "";
            if (blogAuthor != null) {
                avatarUrl = blogAuthor.getAvatarUrl();
            }
            return avatarUrl;
        }

        @Override
        protected void onPostExecute(String avatarUrl) {
            Uri uri = Uri.parse(avatarUrl);
            SimpleDraweeView top_image = (SimpleDraweeView) findViewById(R.id.top_image);
            SimpleDraweeView nav_head_image = (SimpleDraweeView) findViewById(R.id.nav_head_image);
            top_image.setImageURI(uri);
            nav_head_image.setImageURI(uri);
        }
    }

}
