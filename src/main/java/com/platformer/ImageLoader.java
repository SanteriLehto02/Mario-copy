package com.platformer;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ImageLoader {
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(Files.newInputStream(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}