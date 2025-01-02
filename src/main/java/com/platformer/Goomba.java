package com.platformer;
import java.util.Objects;

public class Goomba {
    int leftCoordinate;
    int rightCoordinate;
    int x;
    int y;
    int GoombaSpeed;


    public Goomba() {
    }

    public Goomba(int leftCoordinate, int rightCoordinate, int x, int y, int GoombaSpeed) {
        this.leftCoordinate = leftCoordinate;
        this.rightCoordinate = rightCoordinate;
        this.x = x;
        this.y = y;
        this.GoombaSpeed = GoombaSpeed;
    }
    public void  GoombaMovement() {
       
        this.x += this.GoombaSpeed;
        if (this.x <= leftCoordinate || this.x == rightCoordinate  ) {
            this.GoombaSpeed = -this.GoombaSpeed;
        }else if (this.x >= rightCoordinate) {
            this.x = rightCoordinate; 
            this.GoombaSpeed = -this.GoombaSpeed;
        }
        
        
    }
    public int getLeftCoordinate() {
        return this.leftCoordinate;
    }

    public void setLeftCoordinate(int leftCoordinate) {
        this.leftCoordinate = leftCoordinate;
    }

    public int getRightCoordinate() {
        return this.rightCoordinate;
    }

    public void setRightCoordinate(int rightCoordinate) {
        this.rightCoordinate = rightCoordinate;
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

    public int getGoombaSpeed() {
        return this.GoombaSpeed;
    }

    public void setGoombaSpeed(int GoombaSpeed) {
        this.GoombaSpeed = GoombaSpeed;
    }

    public Goomba leftCoordinate(int leftCoordinate) {
        setLeftCoordinate(leftCoordinate);
        return this;
    }

    public Goomba rightCoordinate(int rightCoordinate) {
        setRightCoordinate(rightCoordinate);
        return this;
    }

    public Goomba x(int x) {
        setX(x);
        return this;
    }

    public Goomba y(int y) {
        setY(y);
        return this;
    }

    public Goomba GoombaSpeed(int GoombaSpeed) {
        setGoombaSpeed(GoombaSpeed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Goomba)) {
            return false;
        }
        Goomba goomba = (Goomba) o;
        return leftCoordinate == goomba.leftCoordinate && rightCoordinate == goomba.rightCoordinate && x == goomba.x && y == goomba.y && GoombaSpeed == goomba.GoombaSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftCoordinate, rightCoordinate, x, y, GoombaSpeed);
    }

    @Override
    public String toString() {
        return "{" +
            " leftCoordinate='" + getLeftCoordinate() + "'" +
            ", rightCoordinate='" + getRightCoordinate() + "'" +
            ", x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", GoombaSpeed='" + getGoombaSpeed() + "'" +
            "}";
    }


   
}
