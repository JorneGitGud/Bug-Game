package io.github.j141.bugGame.world;

import io.github.j141.bugGame.characters.Character;
import io.github.j141.bugGame.characters.PlayerCharacter;
import io.github.j141.bugGame.characters.SpriteList;
import io.github.j141.bugGame.helpers.FileHelper;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class SpawnManager {
    private SpriteList liceIdleSpriteList;

    private SpriteList pcIdleSpriteList;
    private SpriteList pcWalkSpriteList;

    private Random random;

    public SpawnManager() throws FileNotFoundException {
        liceIdleSpriteList = new SpriteList();
        pcIdleSpriteList = new SpriteList();
        pcWalkSpriteList = new SpriteList();

        //using regex to load all lice idle sprites
        ArrayList<Image> liceIdleLeftImages = FileHelper.createImages("assets/sprites/lice", "^liceIdle.*");
        liceIdleSpriteList.addSprites(Direction.LEFT, liceIdleLeftImages);

        ArrayList<Image> bugIdleDownImages = FileHelper.createImages("assets/sprites/bug", "^bugIdleDown.*");
        ArrayList<Image> bugIdleLeftImages = FileHelper.createImages("assets/sprites/bug", "^bugIdleLeft.*");
        ArrayList<Image> bugIdleRightImages = FileHelper.createImages("assets/sprites/bug", "^bugIdleRight.*");
        ArrayList<Image> bugIdleUpImages = FileHelper.createImages("assets/sprites/bug", "^bugIdleUp.*");
        ArrayList<Image> bugWalkDownImages = FileHelper.createImages("assets/sprites/bug", "^bugWalkDown.*");
        ArrayList<Image> bugWalkLeftImages = FileHelper.createImages("assets/sprites/bug", "^bugWalkLeft.*");
        ArrayList<Image> bugWalkRightImages = FileHelper.createImages("assets/sprites/bug", "^bugWalkRight.*");
        ArrayList<Image> bugWalkUpImages = FileHelper.createImages("assets/sprites/bug", "^bugWalkUp.*");

        pcIdleSpriteList.addSprites(Direction.DOWN, bugIdleDownImages);
        pcIdleSpriteList.addSprites(Direction.LEFT, bugIdleLeftImages);
        pcIdleSpriteList.addSprites(Direction.RIGHT, bugIdleRightImages);
        pcIdleSpriteList.addSprites(Direction.UP, bugIdleUpImages);
        pcWalkSpriteList.addSprites(Direction.DOWN, bugWalkDownImages);
        pcWalkSpriteList.addSprites(Direction.LEFT, bugWalkLeftImages);
        pcWalkSpriteList.addSprites(Direction.RIGHT, bugWalkRightImages);
        pcWalkSpriteList.addSprites(Direction.UP, bugWalkUpImages);

        random = new Random();
    }

    public void spawnLiceAtRandomPosition(World world) {
        Position pos = new Position(random.nextInt(world.getWidth()), random.nextInt(world.getHeight()));
        spawnliceAt(world, pos);
    }

    public void spawnliceAt(World world, Position position) {
        Character lice = new Character(liceIdleSpriteList, position);
        world.addCharacter(lice);
    }

    public void spawnPlayerCharacterAtRandomPosition(World world) {
        Position pos = new Position(random.nextInt(world.getWidth()), random.nextInt(world.getHeight()));
        spawnPlayerCharacterAt(world, pos);
    }

    public void spawnPlayerCharacterAt(World world, Position position) {
        PlayerCharacter pc = new PlayerCharacter(pcIdleSpriteList, pcWalkSpriteList, position);
        pc.rotateLeft();
        pc.rotateLeft();
        world.addPlayerCharacter(pc);
    }


}
