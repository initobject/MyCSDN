package com.zy.mycsdn.Adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zy.mycsdn.Common.CommonRecyclerAdapter;
import com.zy.mycsdn.Common.CommonViewHolder;
import com.zy.mycsdn.Model.BlogContent;
import com.zy.mycsdn.R;

import java.util.List;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:54
 * 描述： 博客内容适配器
 */
public class BlogContentAdapter extends CommonRecyclerAdapter<BlogContent> {

    public BlogContentAdapter(Context context, List<BlogContent> dataList, MultiTypeSupport<BlogContent> multiTypeSupport, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, multiTypeSupport, commonClickListener);
    }

    @Override
    protected void bindData(CommonViewHolder holder, BlogContent data) {
        switch (data.getType()) {
            case BLOG_TEXT:
                holder.setText(R.id.blogContent_text, data.getContent());
                break;
            case BLOG_CODE:
                holder.setText(R.id.blogContent_code, data.getContent());
                break;
            case BLOG_IMAGE:
                Uri imageUri = Uri.parse(data.getContent());
                SimpleDraweeView blogContent_image = holder.getView(R.id.blogContent_image);
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(imageUri)
                        .setAutoPlayAnimations(true)
                        .build();
                blogContent_image.setController(controller);
                break;
            case BLOG_TITLE:
                holder.setText(R.id.blogContent_title, data.getContent());
                break;
        }
    }

}
