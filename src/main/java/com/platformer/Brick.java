package com.platformer;

import java.awt.Image;

public class Brick extends Block {
    
    int state;
    private static final String IMAGE_PATH = "src\\main\\resources\\bricktesti.png";
    public  static final Image image = ImageLoader.loadImage(IMAGE_PATH);
    public Brick() {
        super();
    }

    public Brick(int x, int y, int height, int width, int state) {
        super(x, y, height, width,image);
        this.state = state;
    }
}
