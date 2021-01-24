package io.github.j141.bugGame.world;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Position))
            return false;

        Position otherPos = (Position)other;
        return this.x == otherPos.x && this.y == otherPos.y;
    }
}
