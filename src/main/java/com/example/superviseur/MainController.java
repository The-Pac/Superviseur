package com.example.superviseur;

import com.example.superviseur.classe.Robot;
import com.example.superviseur.classe.WebService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //variable
    public WebService webService = new WebService();

    @FXML
    public TabPane main_TabPane;
    public Tab home_Tab, livraison_Tab, robots_Tab, paquet_Tab, carte_Tab;
    public AnchorPane home_AnchorPane, livraison_AnchorPane, robots_AnchorPane, paquets_AnchorPane, carte_AnchorPane;
    public Button ajouter_robot_Button;
    public TextField identifiant_robot_TextField;
    public TableView<String> livraisons_TabView, robots_TabView, paquets_TabView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_TabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (observable.getValue().getText().toLowerCase()) {
                case "paquets":
                    break;
                case "home":
                    break;
                case "livraisons":
                    break;
                case "carte":
                    //init
                    GridPane carte_gridpane = new GridPane();

                    //style
                    carte_gridpane.setGridLinesVisible(true);
                    carte_gridpane.getStyleClass().add("carte_gridpane");
                    carte_gridpane.setAlignment(Pos.CENTER);
                    carte_gridpane.setHgap(1);
                    carte_gridpane.setVgap(1);
                    carte_gridpane.setTranslateX(50);
                    carte_gridpane.setTranslateY(50);
                    carte_gridpane.setPrefSize(carte_AnchorPane.getWidth() - 50, carte_AnchorPane.getHeight() - 50);


                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            AnchorPane anchorPane = new AnchorPane();
                            Button top_button = new Button("" + i), bottom_button = new Button("" + i),
                                    right_button = new Button("" + i), left_button = new Button("" + i);
                            //style
                            top_button.setMinWidth(10);
                            bottom_button.setMinWidth(10);
                            right_button.setMinWidth(10);
                            left_button.setMinWidth(10);


                            anchorPane.setMinSize(100,100);
                            //top
                            anchorPane.getChildren().add(top_button);
                            //bottom
                            anchorPane.getChildren().add(bottom_button);
                            //right
                            anchorPane.getChildren().add(right_button);
                            //left
                            anchorPane.getChildren().add(left_button);


                            top_button.setLayoutX(anchorPane.getWidth() / 2);
                            top_button.setLayoutY(anchorPane.getHeight());

                            bottom_button.setLayoutX(anchorPane.getWidth() / 2);
                            bottom_button.setLayoutY(-anchorPane.getHeight());

                            right_button.setLayoutX(anchorPane.getWidth());
                            right_button.setLayoutY(0);

                            left_button.setLayoutX(-anchorPane.getWidth());
                            left_button.setLayoutY(0);

                            carte_gridpane.add(anchorPane, i, j);
                        }
                    }


                    carte_AnchorPane.getChildren().add(carte_gridpane);
                    break;
                case "robots":
                    ArrayList<Robot> robots = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        robots.add(new Robot("raoul" + i, "pret"));
                    }

                    //graphique


                    for (Robot robot : robots) {

                    }
                    break;
                default:
                    break;
            }
        });
    }

    public void ajouter_robot_Button_Action(ActionEvent actionEvent) {
    }
}