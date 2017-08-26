package com.zy.mycsdn.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zy.mycsdn.R;

public class ImageActivity extends AppCompatActivity {

    private static final String URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.simpleDraweeView);
        Intent intent = getIntent();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(intent.getStringExtra(URL))
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(controller);
    }

    public static void navToImageActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, ImageActivity.class);
        intent.putExtra(URL, url);
        activity.startActivity(intent);
    }

}
