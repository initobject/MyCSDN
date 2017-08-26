package com.zy.mycsdn.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.mycsdn.Adapter.BlogContentAdapter;
import com.zy.mycsdn.Common.CommonRecyclerAdapter;
import com.zy.mycsdn.Common.CommonViewHolder;
import com.zy.mycsdn.Model.BlogContent;
import com.zy.mycsdn.R;
import com.zy.mycsdn.Util.JsoupUtil;

import java.util.ArrayList;
import java.util.List;

import static com.zy.mycsdn.Model.BlogContent.enumContentType.BLOG_CODE;
import static com.zy.mycsdn.Model.BlogContent.enumContentType.BLOG_IMAGE;
import static com.zy.mycsdn.Model.BlogContent.enumContentType.BLOG_TEXT;

public class ContentByJsoupActivity extends AppCompatActivity implements CommonViewHolder.onItemCommonClickListener {

    private List<BlogContent> blogContentList;

    private BlogContentAdapter blogContentAdapter;

    private final static String URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_by_jsoup);
        RecyclerView rv_blogContent = (RecyclerView) findViewById(R.id.rv_blogContent);
        rv_blogContent.setLayoutManager(new LinearLayoutManager(this));
        blogContentList = new ArrayList<>();
        blogContentAdapter = new BlogContentAdapter(this, blogContentList,
                new CommonRecyclerAdapter.MultiTypeSupport<BlogContent>() {
                    @Override
                    public int getLayoutId(BlogContent item, int position) {
                        if (item.getType() == BLOG_TEXT) {
                            return R.layout.item_text;
                        } else if (item.getType() == BLOG_CODE) {
                            return R.layout.item_code;
                        } else if (item.getType() == BLOG_IMAGE) {
                            return R.layout.item_image;
                        }
                        return R.layout.item_title;
                    }
                }, this);
        rv_blogContent.setAdapter(blogContentAdapter);
        new ContentAsync().execute(getIntent().getStringExtra(URL));
    }

    public static void navToContentByJsoupActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, ContentByJsoupActivity.class);
        intent.putExtra(URL, url);
        activity.startActivity(intent);
    }

    @Override
    public void onItemClickListener(int position) {
        BlogContent blogContent = blogContentList.get(position);
        if (blogContent.getType() == BLOG_IMAGE) {
            ImageActivity.navToImageActivity(this, blogContent.getContent());
        }
    }

    @Override
    public void onItemLongClickListener(int position) {

    }

    /**
     * 获取博客内容，包括文本信息，代码，图片等
     */
    private class ContentAsync extends AsyncTask<String, Void, List<BlogContent>> {

        @Override
        protected List<BlogContent> doInBackground(String... params) {
            return JsoupUtil.getBlogContent(params[0]);
        }

        @Override
        protected void onPostExecute(List<BlogContent> blogContentList) {
            if (blogContentList != null) {
                ContentByJsoupActivity.this.blogContentList.addAll(blogContentList);
                blogContentAdapter.notifyDataSetChanged();
            } else {
                TextView textView = (TextView) findViewById(R.id.tv_hint);
                textView.setVisibility(View.VISIBLE);
                Toast.makeText(ContentByJsoupActivity.this, "内容加载不出来啊~", Toast.LENGTH_SHORT).show();
            }
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_contentByJsoup);
            progressBar.setVisibility(View.GONE);
        }
    }

}
