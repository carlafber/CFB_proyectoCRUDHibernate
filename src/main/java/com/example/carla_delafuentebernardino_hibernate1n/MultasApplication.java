package com.example.carla_delafuentebernardino_hibernate1n;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MultasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MultasApplication.class.getResource("coches.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Multas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}