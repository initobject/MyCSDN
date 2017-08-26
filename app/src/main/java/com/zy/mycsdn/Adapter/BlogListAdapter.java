package com.zy.mycsdn.Adapter;

import android.content.Context;

import com.zy.mycsdn.Common.CommonRecyclerAdapter;
import com.zy.mycsdn.Common.CommonViewHolder;
import com.zy.mycsdn.Model.BlogIntroduction;
import com.zy.mycsdn.R;

import java.util.List;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 11:25
 * 描述：
 */
public class BlogListAdapter extends CommonRecyclerAdapter<BlogIntroduction> {

    public BlogListAdapter(Context context, List<BlogIntroduction> dataList, int layoutId, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, layoutId, commonClickListener);
    }

    @Override
    protected void bindData(CommonViewHolder holder, BlogIntroduction data) {
        holder.setText(R.id.tv_blogTitle, data.getTitle())
                .setText(R.id.tv_blogIntroduction, data.getDescription())
                .setText(R.id.tv_blogMessage, data.getMsg());
    }

}
