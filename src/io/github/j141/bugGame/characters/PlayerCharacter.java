package io.github.j141.bugGame.characters;

import io.github.j141.bugGame.GameLogicManager;
import io.github.j141.bugGame.world.Direction;
import io.github.j141.bugGame.world.Position;
import javafx.scene.image.Image;

public class PlayerCharacter extends Character {
    private SpriteList walkSprites;
    private boolean isMoving;
    private int moveFrames;
    private int currentFrame;
    private Position nextPos;

    public PlayerCharacter(SpriteList idleSprites, SpriteList walkSprites, Position position) {
        super(idleSprites, position);
        this.walkSprites = walkSprites;
    }

    @Override
    public boolean isPlayerCharacter() {
        return true;
    }

    public Image getNextWalkFrame() {
        return walkSprites.getNextSprite(this.direction);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public Direction getDirection() {
        return direction;
    }

    public void rotateRight() {
        switch(direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.UP;
                break;
        }
    }

    public void rotateLeft() {
        switch(direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            default:
                direction = Direction.DOWN;
                break;
        }
    }

    public void startMove(int frames) {
        if(isMoving)
            return;
        isMoving = true;
        moveFrames = frames;
        currentFrame = 0;

        int newX = direction == Direction.RIGHT ? position.getX() + 1 : direction == Direction.LEFT ? position.getX() - 1 : position.getX();
        int newY = direction == Direction.DOWN ? position.getY() + 1 : direction == Direction.UP ? position.getY() - 1 : position.getY();
        nextPos = new Position(newX, newY);
    }

    public double updateMove(GameLogicManager game) {
        currentFrame++;
        if(currentFrame >= moveFrames) {
            isMoving = false;
            position = nextPos;
            game.onMoveComplete(nextPos);
            return 0;
        }
        return (1.0 / moveFrames) * currentFrame;
    }
}
