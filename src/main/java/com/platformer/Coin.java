package com.platformer;
import java.awt.Image;
import java.util.Objects;

public class Coin {
 int x;
 int y;
 int height = 20;
 int width = 20;
 private static final String IMAGE_PATH = "C:\\Users\\sante\\Downloads\\image_no_bg.png";
 public static final Image image = ImageLoader.loadImage(IMAGE_PATH);

    public Coin() {
    }

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coin x(int x) {
        setX(x);
        return this;
    }

    public Coin y(int y) {
        setY(y);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coin)) {
            return false;
        }
        Coin coin = (Coin) o;
        return x == coin.x && y == coin.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            "}";
    }
}