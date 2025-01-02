package com.platformer;
import java.util.Objects;

public class Pipe {
    int x;
    int y;
    int width;
    int height;

    public Pipe() {
    }

    public Pipe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pipe x(int x) {
        setX(x);
        return this;
    }

    public Pipe y(int y) {
        setY(y);
        return this;
    }

    public Pipe width(int width) {
        setWidth(width);
        return this;
    }

    public Pipe height(int height) {
        setHeight(height);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pipe)) {
            return false;
        }
        Pipe pipe = (Pipe) o;
        return x == pipe.x && y == pipe.y && width == pipe.width && height == pipe.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            "}";
    }
}
