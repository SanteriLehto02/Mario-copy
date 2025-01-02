package com.platformer;
import java.awt.Image;
import java.util.Objects;

public class QuestionMarkBlock extends Block{
    String item; 
    int state;
    //src\main\resources\questionmarkblock.jpg
    private static final String IMAGE_PATH = "src\\main\\resources\\questionmarkblock.jpg";
    private static final Image image = ImageLoader.loadImage(IMAGE_PATH);
    public QuestionMarkBlock() {
        super();
    }

    public QuestionMarkBlock(int x, int y, int height, int width, String item, int state) {
        super(x, y, height, width,image);
        this.item = item;
        this.state = state;
    }
    

    public void setItem(String item) {
        this.item = item;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public QuestionMarkBlock item(String item) {
        setItem(item);
        return this;
    }

    public QuestionMarkBlock state(int state) {
        setState(state);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof QuestionMarkBlock)) {
            return false;
        }
        QuestionMarkBlock questionMarkBlock = (QuestionMarkBlock) o;
        return Objects.equals(item, questionMarkBlock.item) && state == questionMarkBlock.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, state);
    }

    @Override
    public String toString() {
        return "{" +
            " item='" + getItem() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }

    private String getItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

   
