package io.github.j141.bugGame;

import io.github.j141.bugGame.helpers.FileHelper;
import io.github.j141.bugGame.world.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class GameLogicManager {
    private World world;
    private WorldRenderer worldRenderer;
    private Stage stage;
    private SpawnManager spawnManager;
    private Pane gameLayout;
    private Timeline timeline;

    Button leftButton;
    Button rightButton;
    Button quitButton;
    Button moveButton;

    public GameLogicManager (Stage stage) throws FileNotFoundException {
        this.stage = stage;
        this.world= new World(20, 20);
        this.spawnManager = new SpawnManager();
    }

    public void startGame() throws FileNotFoundException {

        //  sets the title of the window
        stage.setTitle("Bug Game");
        //  sets fixed size of the window
        stage.setResizable(false);

        //  turn left
        leftButton = new Button("Turn left");
        leftButton.setOnAction(e -> {
            if(world.getPlayerCharacter().isMoving())
                return;
            world.getPlayerCharacter().rotateLeft();
        });
        leftButton.setLayoutX(100);
        leftButton.setLayoutY(460);

        //  turn right
        rightButton = new Button("Turn right");
        rightButton.setOnAction(e -> {
            if(world.getPlayerCharacter().isMoving())
                return;
            world.getPlayerCharacter().rotateRight();
        });
        rightButton.setLayoutX(240);
        rightButton.setLayoutY(460);

        // move character

        moveButton = new Button( "Move");
        moveButton.setOnAction(e -> {
            if(world.getPlayerCharacter().isMoving())
                return;

            Direction direction = world.getPlayerCharacter().getDirection();
            Position pos = world.getPlayerCharacter().getPosition();
            if ((direction == Direction.RIGHT && pos.getX() == world.getWidth() - 1) ||
                (direction == Direction.LEFT && pos.getX() == 0) ||
                (direction == Direction.UP && pos.getY() == 0) ||
                (direction == Direction.DOWN && pos.getY() == world.getHeight() - 1))
                return;

            world.getPlayerCharacter().startMove(4);
        });
        moveButton.setLayoutX(175);
        moveButton.setLayoutY(460);

        // quits the program
        quitButton = new Button("Leave program");
        quitButton.setOnAction(e -> System.exit(1));
        quitButton.setLayoutX(150);
        quitButton.setLayoutY(10);

        //  creates the StackPane and the background color (colors: 3c5e34, 153324)
        gameLayout = new Pane();
        this.worldRenderer= new WorldRenderer(gameLayout);


        gameLayout.setBackground(new Background(new BackgroundFill(Color.web("3c5e34"), CornerRadii.EMPTY, Insets.EMPTY)));

        //  adds elements to the StackPane
        gameLayout.getChildren().addAll(leftButton, moveButton, rightButton, quitButton);


        //  creates the menu scene
        Scene gameScene = new Scene(gameLayout, 390, 500);
        //setup the menu scene in the window
        stage.setScene(gameScene);
        stage.setResizable(false);
        //shows the window
        stage.show();

        ImageView imageView = FileHelper.createImageView("assets/LeafBackground.png");

        imageView.setX(0);
        imageView.setY(50);
        gameLayout.getChildren().add(imageView);

        for(int i = 0; i < 19; i++)
            spawnManager.spawnLiceAtRandomPosition(world);

        spawnManager.spawnPlayerCharacterAtRandomPosition(world);
        worldRenderer.renderWorld(world, 50, this);

        timeline = new Timeline(new KeyFrame(Duration.millis(250), event -> worldRenderer.renderWorld(world, 50,this)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void onMoveComplete(Position pos) {
        if(world.hasCharacterAt(pos)) {
            world.despawnCharacterAt(pos);
            if(world.getNPCs().size() == 0) {
                world.resetWorld();
                try {
                    ImageView wonPic = FileHelper.createImageView("assets/youWin.png");
                    wonPic.setX(93);
                    wonPic.setY(212);
                    gameLayout.getChildren().add(wonPic);
                    timeline.stop();
                    worldRenderer.renderWorld(world, 50, this);
                    gameLayout.getChildren().remove(leftButton);
                    gameLayout.getChildren().remove(rightButton);
                    gameLayout.getChildren().remove(moveButton);
                } catch(Exception e) {
                    System.exit(1);
                }
            }
        }
    }
}



