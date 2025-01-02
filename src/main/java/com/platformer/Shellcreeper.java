package com.platformer;
import java.util.Objects;

public class Shellcreeper {
    int leftCoordinate;
    int rightCoordinate;
    int x;
    int y;
    int ShellcreeperSpeed;
    int state = 1;
    int shellspeed = 5;
    int collision = 0;
    public Shellcreeper() {
    }

    public Shellcreeper(int leftCoordinate, int rightCoordinate, int x, int y, int ShellcreeperSpeed) {
        this.leftCoordinate = leftCoordinate;
        this.rightCoordinate = rightCoordinate;
        this.x = x;
        this.y = y;
        this.ShellcreeperSpeed = ShellcreeperSpeed;
        
    }
    public void  ShellcreeperMovement() {
        
        if (this.state == 2 ) {
            this.x += this.shellspeed;
         
        }else if (state == 1) {
            this.x += this.ShellcreeperSpeed;
            if (this.x <= leftCoordinate || this.x == rightCoordinate  ) {
                this.ShellcreeperSpeed = -this.ShellcreeperSpeed;
            }else if (this.x >= rightCoordinate) {
                this.x = rightCoordinate; 
                this.ShellcreeperSpeed = -this.ShellcreeperSpeed;
            }
        }
        else{
            
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

    public int getShellcreeperSpeed() {
        return this.ShellcreeperSpeed;
    }

    public void setShellcreeperSpeed(int ShellcreeperSpeed) {
        this.ShellcreeperSpeed = ShellcreeperSpeed;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Shellcreeper leftCoordinate(int leftCoordinate) {
        setLeftCoordinate(leftCoordinate);
        return this;
    }

    public Shellcreeper rightCoordinate(int rightCoordinate) {
        setRightCoordinate(rightCoordinate);
        return this;
    }

    public Shellcreeper x(int x) {
        setX(x);
        return this;
    }

    public Shellcreeper y(int y) {
        setY(y);
        return this;
    }

    public Shellcreeper ShellcreeperSpeed(int ShellcreeperSpeed) {
        setShellcreeperSpeed(ShellcreeperSpeed);
        return this;
    }

    public Shellcreeper state(int state) {
        setState(state);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shellcreeper)) {
            return false;
        }
        Shellcreeper shellcreeper = (Shellcreeper) o;
        return leftCoordinate == shellcreeper.leftCoordinate && rightCoordinate == shellcreeper.rightCoordinate && x == shellcreeper.x && y == shellcreeper.y && ShellcreeperSpeed == shellcreeper.ShellcreeperSpeed && state == shellcreeper.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftCoordinate, rightCoordinate, x, y, ShellcreeperSpeed, state);
    }

    @Override
    public String toString() {
        return "{" +
            " leftCoordinate='" + getLeftCoordinate() + "'" +
            ", rightCoordinate='" + getRightCoordinate() + "'" +
            ", x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", ShellcreeperSpeed='" + getShellcreeperSpeed() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }

}
