package com.example.superviseur;

import com.example.superviseur.classe.Package;
import com.example.superviseur.classe.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //const
    public static final String ADDRESS = "http://10.3.1.235:8000/";
    public static final int TIMEOUT = 15;

    //variable
    public static ObservableList<Robot> robots_list;
    public static String selected_house;
    public WebService webService;
    public Map map;

    @FXML
    public TabPane main_TabPane;
    public Tab home_Tab, delivery_Tab, robots_Tab, package_Tab, map_Tab, admin_Tab;
    public AnchorPane home_AnchorPane, delivery_AnchorPane, robots_AnchorPane, packages_AnchorPane, map_AnchorPane, admin_AnchorPane, admin_map_AnchorPane;
    public Button add_robot_Button, add_package_Button, close_Button, add_map_Button;
    public TextField id_robot_TextField;
    public TableView<Robot> robots_TabView;
    public TableView<Delivery> deliveries_TabView;
    public TableView<Package> packages_TabView;
    public ComboBox<Robot> robot_ComboBox;


    //robot columns
    public TableColumn<Robot, String> nom_robot_TableColumn;
    public TableColumn<Robot, Circle> statut_robot_TableColumn;

    //delivery columns
    public TableColumn<Delivery, String> address_delivery_TableColumn, date_delivery_TableColumn;
    public TableColumn<Delivery, Circle> statut_delivery_TableColumn;

    //paquet columns
    public TableColumn<Package, String> id_package_TableColumn, address_package_TableColumn, date_arriver_package_TableColumn;

    public static ObservableList<Robot> getRobots_list() {
        return robots_list;
    }

    public static void setRobots_list(ObservableList<Robot> robots_list) {
        MainController.robots_list = robots_list;
    }

    public static String getSelected_house() {
        return selected_house;
    }

    public static void setSelected_house(String selected_house) {
        MainController.selected_house = selected_house;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //init
        webService = new WebService();
        map = new Map();

        //new Thread(refresh_thread()).start();

        //TODO for each robots add to observableArrayList
        robots_list = FXCollections.observableArrayList();
        for (int i = 0; i < 15; i++) {

            robots_list.add(new Robot("raoul" + i, "pret", 0, 0));
        }

        robot_ComboBox.valueProperty().addListener(observable -> {
            //System.out.println(observable);

        });


        //set tablecolumn
        nom_robot_TableColumn.setCellValueFactory(param -> param.getValue().idProperty());
        statut_robot_TableColumn.setCellValueFactory(param -> {
            Circle statut_circle = new Circle(10);
            switch (param.getValue().statutProperty().getValue()) {
                case "en course":
                    statut_circle.setFill(Color.YELLOW);
                    break;
                case "pret":
                    statut_circle.setFill(Color.GREEN);
                    break;
            }
            return new SimpleObjectProperty<>(statut_circle);
        });


        //style
        home_AnchorPane.setStyle("-fx-background-image: url('background.png');-fx-background-repeat:no-repeat;-fx-background-position: top-center;-fx-background-size: contain");


        //listener on tabpane
        main_TabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (observable.getValue().getText().toLowerCase()) {
                case "home":
                    break;
                case "paquets":
                    break;
                case "livraisons":
                    break;
                case "carte":
                    robot_ComboBox.setItems(robots_list);
                    robot_ComboBox.setConverter(new StringConverter<>() {
                        @Override
                        public String toString(Robot object) {
                            return object.getId();
                        }

                        @Override
                        public Robot fromString(String string) {
                            return null;
                        }
                    });
                    robot_ComboBox.getSelectionModel().selectFirst();

                    //graphic
                    map_AnchorPane.getChildren().add(map.display_map(true, false));
                    break;
                case "robots":
                    //graphic
                    robots_TabView.getItems().clear();
                    robots_TabView.setItems(robots_list);
                    robots_TabView.refresh();
                    break;
                case "admin":
                    admin_map_AnchorPane.setMinWidth(admin_AnchorPane.getWidth() / 2);
                    admin_map_AnchorPane.getChildren().add(map.display_map(false, true));
                    break;
                default:
                    break;
            }
        });
    }

    public void add_robot_Button_Action(ActionEvent actionEvent) {
        if (!id_robot_TextField.getText().isEmpty() && !id_robot_TextField.getText().isBlank()) {

        } else {
            id_robot_TextField.setStyle("-fx-background-color: rgba(255,0,0,0.32)");
        }
    }

    public void add_package_Button_Action(ActionEvent actionEvent) {
        //init
        Tab add_paquet_tab = new Tab("Ajouter paquet");
        AnchorPane add_paquet_Anchorpane = new AnchorPane();
        TextField identifiant_paquet_Textfield = new TextField();
        HBox hBox_button = new HBox();
        VBox vBox = new VBox();
        ScrollPane carte_scrollpane = map.display_map(false, false);
        Button ajouter_paquet_Button = new Button("Ajouter paquet");
        Button annuler_paquet_Button = new Button("Annuler");

        //style
        add_paquet_Anchorpane.getStyleClass().add("anchor_content");
        add_paquet_Anchorpane.setMinSize(packages_AnchorPane.getWidth(), packages_AnchorPane.getHeight());

        carte_scrollpane.setFitToHeight(true);
        carte_scrollpane.setFitToWidth(true);


        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxWidth(200);
        vBox.setSpacing(20);

        identifiant_paquet_Textfield.setMinWidth(200);
        identifiant_paquet_Textfield.setMaxWidth(200);
        identifiant_paquet_Textfield.setPromptText("Identifiant du paquet");

        hBox_button.setMaxWidth(200);
        hBox_button.setAlignment(Pos.CENTER);
        hBox_button.setSpacing(20);

        AnchorPane.setTopAnchor(carte_scrollpane, 50.0);
        AnchorPane.setBottomAnchor(carte_scrollpane, 150.0);
        AnchorPane.setRightAnchor(carte_scrollpane, 50.0);
        AnchorPane.setLeftAnchor(carte_scrollpane, 50.0);

        AnchorPane.setLeftAnchor(vBox, 50.0);
        AnchorPane.setRightAnchor(vBox, 50.0);
        AnchorPane.setBottomAnchor(vBox, 20.0);

        //event
        ajouter_paquet_Button.setOnAction(event -> {
            boolean id_valid = false, house_valid = false;
            if (!identifiant_paquet_Textfield.getText().isEmpty()) {
                identifiant_paquet_Textfield.setStyle("-fx-background-color: #77ACF2");
                id_valid = true;
            } else {
                identifiant_paquet_Textfield.setStyle("-fx-background-color: rgba(255,0,0,0.6)");
                identifiant_paquet_Textfield.setPromptText("Saisi invalide");
            }

            if (selected_house != null) {
                carte_scrollpane.setStyle("-fx-background-color: white");
                house_valid = true;
            } else {
                carte_scrollpane.setStyle("-fx-background-color: rgba(255,0,0,0.6)");
            }

            if (id_valid && house_valid) {
                JsonObject jsonObject = Json.createObjectBuilder().add("id_paquet", identifiant_paquet_Textfield.getText()).add("addr", selected_house).build();
                webService.setHttpRequest(ADDRESS, "croisement/", WebService.POST, jsonObject.toString(), TIMEOUT);

                main_TabPane.getTabs().remove(add_paquet_tab);
                main_TabPane.getSelectionModel().selectFirst();
            }
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

        if (main_TabPane.getTabs().contains(add_paquet_tab)) {
            main_TabPane.getSelectionModel().select(add_paquet_tab);
        } else {
            main_TabPane.getTabs().add(add_paquet_tab);
            main_TabPane.getSelectionModel().select(add_paquet_tab);
        }
    }

    public Task<Void> refresh_thread() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                //get
                webService.setHttpRequest(ADDRESS, "livraisons/", WebService.GET, null, TIMEOUT);
                webService.setHttpRequest(ADDRESS, "robots/", WebService.GET, null, TIMEOUT);
                webService.setHttpRequest(ADDRESS, "croisements/", WebService.GET, null, TIMEOUT);
                webService.setHttpRequest(ADDRESS, "maisons/", WebService.GET, null, TIMEOUT);
                webService.setHttpRequest(ADDRESS, "paquets/", WebService.GET, null, TIMEOUT);
                return null;
            }
        };
    }

    public void close_Button_Action(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void redirect_tab_Action(ActionEvent actionEvent) {
        switch (((Button) actionEvent.getSource()).getText().toLowerCase()) {
            case "livraisons":
                main_TabPane.getSelectionModel().select(delivery_Tab);
                break;
            case "robots":
                main_TabPane.getSelectionModel().select(robots_Tab);
                break;
            case "paquets":
                main_TabPane.getSelectionModel().select(package_Tab);
                break;
            case "carte":
                main_TabPane.getSelectionModel().select(map_Tab);
                break;
            default:
                break;
        }
    }

    public void add_map_Button_Action(ActionEvent actionEvent) {

    }
}