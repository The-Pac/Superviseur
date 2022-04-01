package com.example.superviseur;

import com.example.superviseur.classe.Package;
import com.example.superviseur.classe.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //variable
    public WebService webService;
    public Map carte;

    @FXML
    public TabPane main_TabPane;
    public Tab home_Tab, delivery_Tab, robots_Tab, package_Tab, map_Tab;
    public AnchorPane home_AnchorPane, delivery_AnchorPane, robots_AnchorPane, packages_AnchorPane, map_AnchorPane;
    public Button add_robot_Button, add_package_Button;
    public TextField id_robot_TextField;
    public TableView<Robot> robots_TabView;
    public TableView<Delivery> deliveries_TabView;
    public TableView<Package> packages_TabView;

    //robot columns
    public TableColumn<Robot, String> nom_robot_TableColumn;
    public TableColumn<Robot, Circle> statut_robot_TableColumn;

    //delivery columns
    public TableColumn<Delivery, String> address_delivery_TableColumn, date_livrer_delivery_TableColumn;
    public TableColumn<Delivery, Circle> statut_delivery_TableColumn;

    //paquet columns
    public TableColumn<Package, String> id_package_TableColumn, address_package_TableColumn, date_arriver_package_TableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //init
        webService = new WebService();
        carte = new Map();

        //set tablecolumn
        //nom_robot_TableColumn.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        //statut_robot_TableColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));

        //listener on tabpane
        main_TabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (observable.getValue().getText().toLowerCase()) {
                case "home":
                    break;
                case "paquets":
                    break;
                case "deliverys":
                    break;
                case "carte":
                    map_AnchorPane.getChildren().add(carte.display(map_AnchorPane));
                    break;
                case "robots":
                    ObservableList<Robot> robots = FXCollections.observableArrayList();
                    for (int i = 0; i < 5; i++) {
                        robots.add(new Robot("raoul" + i, "pret"));
                    }


                    //graphique
                    robots_TabView.setItems(robots);
                    robots_TabView.refresh();
                    break;
                default:
                    break;
            }
        });
    }

    public void add_robot_Button_Action(ActionEvent actionEvent) {
    }

    public void add_package_Button_Action(ActionEvent actionEvent) {
        //init
        Tab add_paquet_tab = new Tab("Ajouter paquet");
        AnchorPane add_paquet_Anchorpane = new AnchorPane();
        TextField identifiant_paquet_Textfield = new TextField("Identifiant du paquet");
        HBox hBox_button = new HBox();
        VBox vBox = new VBox();
        ScrollPane carte_scrollpane = carte.display(add_paquet_Anchorpane);
        Button ajouter_paquet_Button = new Button("Ajouter paquet");
        Button annuler_paquet_Button = new Button("Annuler");

        //style
        add_paquet_Anchorpane.setMinSize(packages_AnchorPane.getWidth(), packages_AnchorPane.getHeight());

        carte_scrollpane.setFitToHeight(true);
        carte_scrollpane.setFitToWidth(true);


        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxWidth(200);
        vBox.setSpacing(20);

        identifiant_paquet_Textfield.setMinWidth(200);
        identifiant_paquet_Textfield.setMaxWidth(200);

        hBox_button.setMaxWidth(200);
        hBox_button.setAlignment(Pos.CENTER);
        hBox_button.setSpacing(20);

        AnchorPane.setTopAnchor(carte_scrollpane, 50.0);
        AnchorPane.setBottomAnchor(carte_scrollpane, 100.0);
        AnchorPane.setRightAnchor(carte_scrollpane, 50.0);
        AnchorPane.setLeftAnchor(carte_scrollpane, 50.0);

        AnchorPane.setLeftAnchor(vBox, 50.0);
        AnchorPane.setRightAnchor(vBox, 50.0);
        AnchorPane.setBottomAnchor(vBox, 20.0);

        //event
        ajouter_paquet_Button.setOnAction(event -> {
            main_TabPane.getTabs().remove(add_paquet_tab);
            main_TabPane.getSelectionModel().selectFirst();
        });

        annuler_paquet_Button.setOnAction(event -> {
            main_TabPane.getTabs().remove(add_paquet_tab);
            main_TabPane.getSelectionModel().selectFirst();
        });

        //add
        hBox_button.getChildren().addAll(ajouter_paquet_Button, annuler_paquet_Button);
        vBox.getChildren().addAll(identifiant_paquet_Textfield, hBox_button);
        vBox.setTranslateX(add_paquet_Anchorpane.getWidth() / 2);
        vBox.setTranslateY(add_paquet_Anchorpane.getHeight() / 2);
        add_paquet_Anchorpane.getChildren().addAll(carte_scrollpane, vBox);
        add_paquet_tab.setContent(add_paquet_Anchorpane);
        main_TabPane.getTabs().add(add_paquet_tab);
        main_TabPane.getSelectionModel().selectLast();
    }
}