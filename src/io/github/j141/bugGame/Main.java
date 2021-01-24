package io.github.j141.bugGame;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    // variables used in de Scene
    Button leftButton, rightButton, quitButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameLogicManager game = new GameLogicManager(primaryStage);
        game.startGame();
    }

}