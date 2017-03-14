package com.baray.schoolmanagement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.baray.tools.DefaultImageLoader;
import com.baray.tools.DefaultVideoLoader;
import com.baray.tools.MediaInfo;
import com.baray.tools.MediaLoader;
import com.baray.tools.PicassoImageLoader;
import com.baray.tools.ScrollGalleryView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends FragmentActivity {

    private static final ArrayList<String> images = new ArrayList<>(Arrays.asList(
            "http://www.nature.org/cs/groups/webcontent/@web/@northcarolina/documents/media/nc-mountain-sunset.jpg",
            "https://www.gslcs.org/wp-content/uploads/2013/08/computer-lab-IMG_5572.jpg",
            "http://budapestbeacon.com/wp-content/uploads/2016/08/iskola-1.jpg",
            "http://scontent.cdninstagram.com/t51.2885-15/s480x480/e15/11015672_1549449035343242_1819189967_n.jpg?ig_cache_key=OTQxNDU4MjAxMjI4MDgxMjMx.2"
    ));
    private static final String movieUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);

        scrollGalleryView.setThumbnailSize(100);
        scrollGalleryView.setZoom(true);
        scrollGalleryView.setFragmentManager(getSupportFragmentManager());
        try {
            scrollGalleryView.addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)));
            scrollGalleryView.addMedia(MediaInfo.mediaLoader(new DefaultVideoLoader(movieUrl, R.mipmap.default_video)));
            scrollGalleryView.addMedia(infos);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private Bitmap toBitmap(int image) {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }
}
