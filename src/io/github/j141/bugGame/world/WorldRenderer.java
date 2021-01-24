package io.github.j141.bugGame.world;

import io.github.j141.bugGame.GameLogicManager;
import io.github.j141.bugGame.characters.Character;
import io.github.j141.bugGame.characters.PlayerCharacter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class WorldRenderer {
    private Pane pane;
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public WorldRenderer(Pane pane) {
        this.pane = pane;
    }

    public void renderWorld(World world, int yOffset, GameLogicManager game) {
        int imageViewCounter = 0;

        ArrayList<Character> npcs = world.getNPCs();

        for(Character npc : npcs) {
            imageViewCounter++;
            if(imageViews.size() < imageViewCounter) {
                ImageView imageView = new ImageView(npc.getNextIdleFrame());
                imageView.setX(npc.getPosition().getX() * 20);
                imageView.setY(yOffset + (npc.getPosition().getY() * 20));
                imageView.setFitHeight(20);
                imageView.setFitWidth(20);
                imageView.toFront();
                imageViews.add(imageView);
                pane.getChildren().add(imageView);
            }
            else {
                ImageView imageView = imageViews.get(imageViewCounter - 1);
                imageView.setImage(npc.getNextIdleFrame());
                imageView.setX(npc.getPosition().getX() * 20);
                imageView.setY(yOffset + (npc.getPosition().getY() * 20));
            }
        }


        PlayerCharacter pc = world.getPlayerCharacter();
        if(pc != null) {
            imageViewCounter++;

            if (pc.isMoving())
                if (imageViews.size() < imageViewCounter) {
                    ImageView imageView = new ImageView(pc.getNextWalkFrame());
                    double movePartial = pc.updateMove(game);
                    if (pc.getDirection() == Direction.LEFT) {
                        imageView.setX((pc.getPosition().getX() * 20) + movePartial * -20);
                        imageView.setY(yOffset + (pc.getPosition().getX() * 20));
                    } else if (pc.getDirection() == Direction.RIGHT) {
                        imageView.setX((pc.getPosition().getX() * 20) + movePartial * 20);
                        imageView.setY(yOffset + (pc.getPosition().getX() * 20));
                    } else if (pc.getDirection() == Direction.UP) {
                        imageView.setX(pc.getPosition().getY() * 20);
                        imageView.setY(yOffset + ((pc.getPosition().getY() * 20) + movePartial * -20));
                    } else {
                        imageView.setX(pc.getPosition().getY() * 20);
                        imageView.setY(yOffset + ((pc.getPosition().getY() * 20) + movePartial * 20));
                    }
                    imageView.setFitHeight(20);
                    imageView.setFitWidth(20);
                    imageView.toFront();
                    imageViews.add(imageView);
                    pane.getChildren().add(imageView);
                } else {
                    ImageView imageView = imageViews.get(imageViewCounter - 1);
                    imageView.setImage(pc.getNextWalkFrame());
                    double movePartial = pc.updateMove(game);
                    if (pc.getDirection() == Direction.LEFT) {
                        imageView.setX((pc.getPosition().getX() * 20) + movePartial * -20);
                        imageView.setY(yOffset + (pc.getPosition().getY() * 20));
                    } else if (pc.getDirection() == Direction.RIGHT) {
                        imageView.setX((pc.getPosition().getX() * 20) + movePartial * 20);
                        imageView.setY(yOffset + (pc.getPosition().getY() * 20));
                    } else if (pc.getDirection() == Direction.UP) {
                        imageView.setX(pc.getPosition().getX() * 20);
                        imageView.setY(yOffset + ((pc.getPosition().getY() * 20) + movePartial * -20));
                    } else {
                        imageView.setX(pc.getPosition().getX() * 20);
                        imageView.setY(yOffset + ((pc.getPosition().getY() * 20) + movePartial * 20));
                    }
                }
            else if (imageViews.size() < imageViewCounter) {
                ImageView imageView = new ImageView(pc.getNextIdleFrame());
                imageView.setX(pc.getPosition().getX() * 20);
                imageView.setY(yOffset + (pc.getPosition().getY() * 20));
                imageView.toFront();
                imageViews.add(imageView);
                pane.getChildren().add(imageView);
            } else {
                ImageView imageView = imageViews.get(imageViewCounter - 1);
                imageView.setImage(pc.getNextIdleFrame());
                imageView.setX(pc.getPosition().getX() * 20);
                imageView.setY(yOffset + (pc.getPosition().getY() * 20));
            }
        }

        if(imageViews.size() > imageViewCounter)
            for(int i = imageViews.size() - 1; i >= imageViewCounter; i--) {
                pane.getChildren().remove(imageViews.get(i));
                imageViews.remove(imageViews.get(i));
            }
    }
}
