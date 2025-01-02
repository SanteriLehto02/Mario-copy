package com.platformer;
import java.util.Objects;

public class Goal {
    int x;
    int y;
    int height;
    int width;



    public Goal() {
    }

    public Goal(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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

    public Goal x(int x) {
        setX(x);
        return this;
    }

    public Goal y(int y) {
        setY(y);
        return this;
    }

    public Goal height(int height) {
        setHeight(height);
        return this;
    }

    public Goal width(int width) {
        setWidth(width);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) o;
        return x == goal.x && y == goal.y && height == goal.height && width == goal.width;
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
