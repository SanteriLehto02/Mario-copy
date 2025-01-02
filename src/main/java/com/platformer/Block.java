package com.platformer;
import java.awt.Image;
import java.util.Objects;

public class Block {
    int x;
    int y;
    int height;
    int width;
    Image image;
    public Block() {
    }

    public Block(int x, int y, int height, int width, Image image) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.image = image;
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

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Block x(int x) {
        setX(x);
        return this;
    }

    public Block y(int y) {
        setY(y);
        return this;
    }

    public Block height(int height) {
        setHeight(height);
        return this;
    }

    public Block width(int width) {
        setWidth(width);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Block)) {
            return false;
        }
        Block block = (Block) o;
        return x == block.x && y == block.y && height == block.height && width == block.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height, width);
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", height='" + getHeight() + "'" +
            ", width='" + getWidth() + "'" +
            "}";
    }
    
}
