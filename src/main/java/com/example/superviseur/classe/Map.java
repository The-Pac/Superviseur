package com.example.superviseur.classe;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map {


    public Map() {

    }


    /**
     * @return
     */

    public ScrollPane display(AnchorPane map_AnchorPane) {
        //init
        ScrollPane scrollPane = new ScrollPane();
        GridPane carte_gridpane = new GridPane();
        scrollPane.setContent(carte_gridpane);

        //style
        carte_gridpane.getStyleClass().add("map_gridpane");
        carte_gridpane.setAlignment(Pos.CENTER);
        carte_gridpane.setTranslateX(50);
        carte_gridpane.setTranslateY(50);
        carte_gridpane.setPrefSize(map_AnchorPane.getWidth() - 50, map_AnchorPane.getHeight() - 50);

        int size = 4, house_size_icon = 20;

        for (int y = 0; y < size * 2; y += 2) {
            for (int x = 0; x < size * 2; x += 2) {
                AnchorPane anchorPane = new AnchorPane();
                Button top_button = new Button("" + y), bottom_button = new Button("" + y), right_button = new Button("" + y), left_button = new Button("" + y);
                //style
                top_button.getStyleClass().add("district_button");
                bottom_button.getStyleClass().add("district_button");
                right_button.getStyleClass().add("district_button");
                left_button.getStyleClass().add("district_button");

                top_button.setMinWidth(house_size_icon);
                bottom_button.setMinWidth(house_size_icon);
                right_button.setMinWidth(house_size_icon);
                left_button.setMinWidth(house_size_icon);

                top_button.setMinHeight(house_size_icon);
                bottom_button.setMinHeight(house_size_icon);
                right_button.setMinHeight(house_size_icon);
                left_button.setMinHeight(house_size_icon);

                top_button.setWrapText(true);
                bottom_button.setWrapText(true);
                right_button.setWrapText(true);
                left_button.setWrapText(true);


                anchorPane.setMinSize(100, 100);
                //top
                AnchorPane.setTopAnchor(top_button, 0.0);
                AnchorPane.setRightAnchor(top_button, (anchorPane.getMinWidth() / 2) - (top_button.getMinWidth() / 2));
                AnchorPane.setLeftAnchor(top_button, (anchorPane.getMinWidth() / 2) - (top_button.getMinWidth() / 2));
                anchorPane.getChildren().add(top_button);
                //bottom
                AnchorPane.setBottomAnchor(bottom_button, 0.0);
                AnchorPane.setRightAnchor(bottom_button, (anchorPane.getMinWidth() / 2) - (bottom_button.getMinWidth() / 2));
                AnchorPane.setLeftAnchor(bottom_button, (anchorPane.getMinWidth() / 2) - (bottom_button.getMinWidth() / 2));
                anchorPane.getChildren().add(bottom_button);
                //right
                AnchorPane.setRightAnchor(right_button, 0.0);
                AnchorPane.setTopAnchor(right_button, (anchorPane.getMinHeight() / 2) - (right_button.getMinHeight() - 2));
                AnchorPane.setBottomAnchor(right_button, (anchorPane.getMinHeight() / 2) - (right_button.getMinHeight() / 2));
                anchorPane.getChildren().add(right_button);
                //left
                AnchorPane.setLeftAnchor(left_button, 0.0);
                AnchorPane.setTopAnchor(left_button, (anchorPane.getMinHeight() / 2) - (left_button.getMinHeight() - 2));
                AnchorPane.setBottomAnchor(left_button, (anchorPane.getMinHeight() / 2) - (left_button.getMinHeight() / 2));
                anchorPane.getChildren().add(left_button);


                //croisement
                if (x == 0 && y == 0) {
                    Rectangle rectangle = new Rectangle(30, 30, Color.BLACK);
                    carte_gridpane.add(rectangle, x, y);
                    GridPane.setValignment(rectangle, VPos.CENTER);
                    GridPane.setHalignment(rectangle, HPos.CENTER);
                } else {
                    Rectangle rectangle = new Rectangle(30, 30, Color.BLACK);
                    carte_gridpane.add(rectangle, x, y);
                    GridPane.setValignment(rectangle, VPos.CENTER);
                    GridPane.setHalignment(rectangle, HPos.CENTER);
                }

                if (x + 1 < (size * 2) - 1) {
                    //right line
                    Rectangle rectangle = new Rectangle(100, 25, Color.GRAY);
                    carte_gridpane.add(rectangle, x + 1, y);
                    GridPane.setValignment(rectangle, VPos.CENTER);
                    GridPane.setHalignment(rectangle, HPos.CENTER);
                }
                if (y + 1 < (size * 2) - 1) {
                    //bottom line
                    Rectangle rectangle = new Rectangle(25, 100, Color.GRAY);
                    carte_gridpane.add(rectangle, x, y + 1);
                    GridPane.setValignment(rectangle, VPos.CENTER);
                    GridPane.setHalignment(rectangle, HPos.CENTER);
                }
                if (y + 1 < (size * 2) - 1 && x + 1 < (size * 2) - 1) {
                    //maisons
                    carte_gridpane.add(anchorPane, x + 1, y + 1);
                    GridPane.setValignment(anchorPane, VPos.CENTER);
                    GridPane.setHalignment(anchorPane, HPos.CENTER);
                }
            }
        }
        //ajouter la poste
        //bottom line
        Rectangle rectangle = new Rectangle(25, 100, Color.GRAY);
        carte_gridpane.add(rectangle, 0, carte_gridpane.getRowCount());
        GridPane.setValignment(rectangle, VPos.CENTER);
        GridPane.setHalignment(rectangle, HPos.CENTER);
        Label label = new Label("Poste");
        label.setAlignment(Pos.CENTER);
        label.setMinSize(30, 30);
        label.setStyle("-fx-background-color: yellow");
        carte_gridpane.add(label, 0, carte_gridpane.getRowCount());

        return scrollPane;
    }
}
