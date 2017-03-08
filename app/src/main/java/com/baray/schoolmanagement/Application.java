package com.baray.schoolmanagement;

/**
 * Created by Akram on 3/1/2017.
 */
public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "IRANSans.ttf");

    }
}
