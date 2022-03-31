package com.example.superviseur;

import com.example.superviseur.classe.Robot;
import com.example.superviseur.classe.WebService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //static
    public static double height, width;
    //variable
    public WebService webService = new WebService();

    @FXML
    public TabPane main_TabPane;
    public Tab home_Tab, livraison_Tab, robots_Tab, paquet_Tab, carte_Tab;
    public AnchorPane home_AnchorPane, livraison_AnchorPane, robots_AnchorPane, paquets_AnchorPane, carte_AnchorPane;
    public ScrollPane robots_ScrollPane, paquets_ScrollPane;
    public Button ajouter_robot_Button;
    public TextField identifiant_robot_TextField;


    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        MainController.height = height;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        MainController.width = width;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_TabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (observable.getValue().getText().toLowerCase()) {
                case "paquets":
                    paquets_AnchorPane.getChildren().clear();
                    break;
                case "home":
                    home_AnchorPane.getChildren().clear();
                    break;
                case "livraisons":
                    livraison_AnchorPane.getChildren().clear();
                    break;
                case "carte":
                    carte_AnchorPane.getChildren().clear();
                    break;
                case "robots":
                    robots_AnchorPane.getChildren().clear();

                    ArrayList<Robot> robots = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        robots.add(new Robot("raoul" + i, "pret"));
                    }

                    //graphique

                    ArrayList<HBox> hBoxes = new ArrayList<>();
                    VBox liste_robots_vBox = new VBox();

                    HBox robots_hBox = new HBox();
                    for (Robot robot : robots) {
                        //init
                        VBox robot_vBox = new VBox();
                        Label identifiant = new Label();
                        Circle statut_circle = new Circle();

                        //style vbox
                        robot_vBox.setAlignment(Pos.CENTER);
                        robot_vBox.setPrefWidth(100);
                        robot_vBox.setPrefHeight(200);
                        robot_vBox.setPadding(new Insets(10, 10, 10, 10));
                        robots_hBox.setAlignment(Pos.CENTER);
                        robots_hBox.setFillHeight(true);
                        robots_hBox.setSpacing(20);
                        robot_vBox.setPrefWidth(robots_ScrollPane.getPrefWidth());
                        statut_circle.setRadius(20);

                        //enregistrement des données robot
                        identifiant.setText(robot.getIdentifiant());
                        switch (robot.getStatut()) {
                            case "pret":
                                statut_circle.setFill(Color.GREEN);
                                break;
                            case "en course":
                                statut_circle.setFill(Color.YELLOW);
                                break;
                            default:
                                break;
                        }

                        //ajout FXMl element
                        robot_vBox.getChildren().add(statut_circle);
                        robot_vBox.getChildren().add(identifiant);

                        if (robots_hBox.getChildren().size() < 3) {
                            robots_hBox.getChildren().add(robot_vBox);
                        } else {
                            hBoxes.add(robots_hBox);
                            robots_hBox = new HBox();
                        }
                        System.out.println(robots_hBox);
                    }

                    //TODO for each result

                    liste_robots_vBox.getChildren().addAll(hBoxes);

                    robots_ScrollPane.setContent(liste_robots_vBox);
                    robots_AnchorPane.getChildren().add(robots_ScrollPane);
                    break;
                default:
                    break;
            }
        });
    }

    public void ajouter_robot_Button_Action(ActionEvent actionEvent) {
    }
}