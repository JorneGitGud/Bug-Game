package io.github.j141.bugGame.characters;

import io.github.j141.bugGame.world.Direction;
import io.github.j141.bugGame.world.Position;
import javafx.scene.image.Image;

public class Character {
    protected SpriteList idleSprites;

    protected Direction direction;

    protected Position position;

    public Character(SpriteList sprites, Position position) {
        idleSprites = sprites;
        this.position = position;
        direction = Direction.LEFT;
    }

    public Position getPosition() {
        return position;
    }
    public Image getNextIdleFrame() {
        return idleSprites.getNextSprite(this.direction);
    }
    public boolean isPlayerCharacter(){
        return false;
    }
}
