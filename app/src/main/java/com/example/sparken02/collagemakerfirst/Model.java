package com.example.sparken02.collagemakerfirst;

import android.graphics.Bitmap;

/**
 * Created by sparken02 on 11/7/17.
 */

public class Model {
    Bitmap image;

    public Model(Bitmap image) {
        this.image = image;
    }

    public Model() {
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
