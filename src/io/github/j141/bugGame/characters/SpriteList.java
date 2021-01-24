package io.github.j141.bugGame.characters;

import io.github.j141.bugGame.helpers.FileHelper;
import io.github.j141.bugGame.world.Direction;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SpriteList {

    private HashMap<Direction, ArrayList<Image>> sprites = new HashMap<>();

    private Direction lastDirection = Direction.LEFT;
    private int spriteCounter = -1;

    public SpriteList() {
        //Init all sprite collections with empty ArrayList
        sprites.put(Direction.RIGHT, new ArrayList<>());
        sprites.put(Direction.DOWN, new ArrayList<>());
        sprites.put(Direction.LEFT, new ArrayList<>());
        sprites.put(Direction.UP, new ArrayList<>());
    }

    public void addSprite(Direction dir, Image frame) {
        sprites.get(dir).add(frame);
    }

    public void addSprite(Direction dir, String frame) throws FileNotFoundException {
        Image image = FileHelper.createImage(frame);
        addSprite(dir, image);
    }

    public void addSprites(Direction dir, ArrayList<Image> frames) {
        sprites.get(dir).addAll(frames);
    }

    public void addSpritesFromPaths(Direction dir, ArrayList<String> paths) throws FileNotFoundException {
        ArrayList<Image> frames = new ArrayList<>();
        for(String path : paths) {
            frames.add(FileHelper.createImage(path));
        }
        sprites.get(dir).addAll(frames);
    }

    public void addSpritesByDirectory(Direction dir, String directoryPath, String fileRegex) throws FileNotFoundException {
        ArrayList<Image> frames = FileHelper.createImages(directoryPath, fileRegex);
        sprites.get(dir).addAll(frames);
    }

    public Image getNextSprite(Direction dir) {
        if(lastDirection != dir)
            spriteCounter = -1;
        else if(spriteCounter == -1) //randomize start frame on app start
            spriteCounter = new Random().nextInt(sprites.get(dir).size());

        ArrayList<Image> frames = sprites.get(dir);

        spriteCounter++;
        if(spriteCounter == frames.size())
            spriteCounter = 0;

        lastDirection = dir;
        return frames.get(spriteCounter);
    }
}
