package io.github.j141.bugGame.world;

import io.github.j141.bugGame.characters.PlayerCharacter;
import io.github.j141.bugGame.characters.Character;

import java.util.ArrayList;

public class World {
    private int height;
    private int width;
    private ArrayList<Character> characters = new ArrayList<Character>();
    private PlayerCharacter playerCharacter;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addCharacter(Character character) {
        Position pos = character.getPosition();
        if(pos.getX() >= width || pos.getY() >= height || pos.getX() < 0 || pos.getY() < 0)
            return;

        characters.add(character);
    }

    public void addPlayerCharacter(PlayerCharacter character) {
        Position pos = character.getPosition();
        if(pos.getX() >= width || pos.getY() >= height || pos.getX() < 0 || pos.getY() < 0)
            return;

        playerCharacter = character;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public ArrayList<Character> getNPCs() {
        return characters;
    }

    public boolean hasCharacterAt(Position pos) {
        if(pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0 || pos.getY() >= height)
            return false;

        for(Character character : characters) {
            if(character.getPosition().equals(pos))
                return true;
        }

        return false;
    }

    public boolean hasCharacterAt(int x, int y) {
        return hasCharacterAt(new Position(x, y));
    }

    public Character getCharacterAt(Position pos) {
        if(pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0 || pos.getY() >= height)
            return null;

        for(Character character : characters) {
            if(character.getPosition().equals(pos))
                return character;
        }

        return null;
    }

    public Character getCharacterAt(int x, int y) {
        return getCharacterAt(new Position(x, y));
    }

    public void despawnCharacter(Character character) {
        despawnCharacterAt(character.getPosition());
    }

    public void despawnCharacterAt(Position pos) {
        for(int i = 0; i < characters.size(); i++) {
            if(characters.get(i).getPosition().equals(pos)) {
                characters.remove(i);
                return;
            }
        }
    }

    public void resetWorld() {
        characters = new ArrayList<>();
        playerCharacter = null;
    }
}
