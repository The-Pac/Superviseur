package com.example.superviseur.classe;

import com.example.superviseur.MainController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Map {


    public Map() {

    }


    /**
     * returns a dynamic map
     *
     * @param poste
     * @return ScrollPane
     */

    public ScrollPane display_map(boolean poste) {
        //init
        final int size_intersection = 30, size_road = 25, size_robot = 50;
        final double width_la_poste = 300, road_1_top = 50;

        int la_poste_offset_x = 0, la_poste_offset_y = 0;
        AnchorPane content_AnchorPane = new AnchorPane();

        //init quartier
        ScrollPane map_scrollPane = new ScrollPane();
        GridPane map_gridpane = new GridPane();


        AnchorPane.setLeftAnchor(map_gridpane, width_la_poste);
        AnchorPane.setTopAnchor(map_gridpane, (50. + road_1_top) - ((size_intersection - size_road) / 2));

        content_AnchorPane.getChildren().add(map_gridpane);
        map_scrollPane.setContent(content_AnchorPane);

        //style
        map_gridpane.setLayoutX(width_la_poste);
        map_gridpane.getStyleClass().add("map_gridpane");
        map_gridpane.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(map_scrollPane, 50.);
        AnchorPane.setLeftAnchor(map_scrollPane, 10.);
        AnchorPane.setRightAnchor(map_scrollPane, 10.);
        AnchorPane.setBottomAnchor(map_scrollPane, 10.);

        int size = 4, house_size_icon = 20;

        for (int y = 0; y < (size * 2); y += 2) {
            for (int x = 0; x < (size * 2); x += 2) {
                AnchorPane houses_AnchorPane = new AnchorPane();
                Button top_button = new Button("" + y), bottom_button = new Button("" + y), right_button = new Button("" + y), left_button = new Button("" + y);
                //style
                Rectangle house_1 = new Rectangle(house_size_icon, house_size_icon, new ImagePattern(new Image("house.png"))),
                        house_2 = new Rectangle(house_size_icon, house_size_icon, new ImagePattern(new Image("house.png"))),
                        house_3 = new Rectangle(house_size_icon, house_size_icon, new ImagePattern(new Image("house.png"))),
                        house_4 = new Rectangle(house_size_icon, house_size_icon, new ImagePattern(new Image("house.png")));

                top_button.setGraphic(house_1);
                bottom_button.setGraphic(house_2);
                right_button.setGraphic(house_3);
                left_button.setGraphic(house_4);

                top_button.setWrapText(true);
                bottom_button.setWrapText(true);
                right_button.setWrapText(true);
                left_button.setWrapText(true);


                houses_AnchorPane.setMinSize(100, 100);
                //top
                AnchorPane.setTopAnchor(top_button, 0.0);
                AnchorPane.setRightAnchor(top_button, (houses_AnchorPane.getMinWidth() / 2) - (top_button.getMinWidth() / 2));
                AnchorPane.setLeftAnchor(top_button, (houses_AnchorPane.getMinWidth() / 2) - (top_button.getMinWidth() / 2));
                houses_AnchorPane.getChildren().add(top_button);
                //bottom
                AnchorPane.setBottomAnchor(bottom_button, 0.0);
                AnchorPane.setRightAnchor(bottom_button, (houses_AnchorPane.getMinWidth() / 2) - (bottom_button.getMinWidth() / 2));
                AnchorPane.setLeftAnchor(bottom_button, (houses_AnchorPane.getMinWidth() / 2) - (bottom_button.getMinWidth() / 2));
                houses_AnchorPane.getChildren().add(bottom_button);
                //right
                AnchorPane.setRightAnchor(right_button, 0.0);
                AnchorPane.setTopAnchor(right_button, (houses_AnchorPane.getMinHeight() / 2) - (right_button.getMinHeight() - 2));
                AnchorPane.setBottomAnchor(right_button, (houses_AnchorPane.getMinHeight() / 2) - (right_button.getMinHeight() / 2));
                houses_AnchorPane.getChildren().add(right_button);
                //left
                AnchorPane.setLeftAnchor(left_button, 0.0);
                AnchorPane.setTopAnchor(left_button, (houses_AnchorPane.getMinHeight() / 2) - (left_button.getMinHeight() - 2));
                AnchorPane.setBottomAnchor(left_button, (houses_AnchorPane.getMinHeight() / 2) - (left_button.getMinHeight() / 2));
                houses_AnchorPane.getChildren().add(left_button);


                //intersection
                if (x == 0 && y == 2) {
                    Rectangle intersection = new Rectangle(size_intersection, size_intersection, Color.BLACK);
                    map_gridpane.add(intersection, x, y);
                    GridPane.setValignment(intersection, VPos.CENTER);
                    GridPane.setHalignment(intersection, HPos.CENTER);
                } else {
                    Rectangle intersection = new Rectangle(size_intersection, size_intersection, Color.BLACK);
                    map_gridpane.add(intersection, x, y);
                    GridPane.setValignment(intersection, VPos.CENTER);
                    GridPane.setHalignment(intersection, HPos.CENTER);
                }

                if (x + 1 < (size * 2) - 1) {
                    //right line
                    Rectangle road = new Rectangle(100 + (house_size_icon * 2), size_road, Color.GRAY);
                    map_gridpane.add(road, x + 1, y);
                    GridPane.setValignment(road, VPos.CENTER);
                    GridPane.setHalignment(road, HPos.CENTER);
                }
                if (y + 1 < (size * 2) - 1) {
                    //bottom line
                    Rectangle road = new Rectangle(size_road, 100 + (house_size_icon * 2), Color.GRAY);
                    map_gridpane.add(road, x, y + 1);
                    GridPane.setValignment(road, VPos.CENTER);
                    GridPane.setHalignment(road, HPos.CENTER);
                }
                if (y + 1 < (size * 2) - 1 && x + 1 < (size * 2) - 1) {
                    //maisons
                    map_gridpane.add(houses_AnchorPane, x + 1, y + 1);
                    GridPane.setValignment(houses_AnchorPane, VPos.CENTER);
                    GridPane.setHalignment(houses_AnchorPane, HPos.CENTER);
                }
            }
        }

        if (poste) {
            //init la poste
            AnchorPane la_poste_AnchorPane = new AnchorPane();
            ScrollPane la_poste_scrollPane = new ScrollPane();
            GridPane la_poste_GridPane = new GridPane();
            Label la_poste_Label = new Label("La poste");

            Rectangle road_1 = new Rectangle(), road_2 = new Rectangle();

            //style

            AnchorPane.setLeftAnchor(la_poste_AnchorPane, 0.);
            AnchorPane.setTopAnchor(la_poste_AnchorPane, 50.);

            la_poste_AnchorPane.setMinSize(width_la_poste, 400);
            la_poste_AnchorPane.setStyle("-fx-background-color: Yellow");

            AnchorPane.setBottomAnchor(la_poste_scrollPane, 10.);
            AnchorPane.setRightAnchor(la_poste_scrollPane, 10.);
            AnchorPane.setLeftAnchor(la_poste_scrollPane, 10.);
            AnchorPane.setTopAnchor(la_poste_scrollPane, 100.);

            AnchorPane.setTopAnchor(la_poste_Label, 10.);
            AnchorPane.setLeftAnchor(la_poste_Label, 10.);

            la_poste_Label.setStyle("-fx-font-weight: bold;-fx-font-size: 20");

            road_1.setFill(Color.GREY);
            road_2.setFill(Color.GREY);


            road_1.setX((la_poste_AnchorPane.getMinWidth() / 2) - (road_1.getHeight() / 2));
            road_2.setX((la_poste_AnchorPane.getMinWidth() / 2) - (road_2.getHeight() / 2));

            road_1.setHeight(size_road);
            road_1.setWidth((la_poste_AnchorPane.getMinWidth() / 2));

            road_2.setWidth(size_road);
            road_2.setHeight((AnchorPane.getTopAnchor(la_poste_scrollPane) / 2));


            AnchorPane.setTopAnchor(road_1, road_1_top);
            AnchorPane.setRightAnchor(road_1, 0.);

            AnchorPane.setTopAnchor(road_2, 50.);
            AnchorPane.setRightAnchor(road_2, (la_poste_AnchorPane.getMinWidth() / 2) - (road_2.getWidth() / 2));

            //add
            la_poste_scrollPane.setContent(la_poste_GridPane);
            la_poste_AnchorPane.getChildren().addAll(la_poste_Label, road_1, road_2, la_poste_scrollPane);
            content_AnchorPane.getChildren().add(la_poste_AnchorPane);
            if (!MainController.getRobots_list().isEmpty()) {
                for (Robot robot : MainController.getRobots_list()) {
                    AnchorPane robot_anchorPane = new AnchorPane();
                    Rectangle robot_rect = new Rectangle();
                    robot_rect.setFill(new ImagePattern(new Image("robot_top_view.png")));
                    Rectangle package_rect = new Rectangle();

                    //style
                    robot_rect.setHeight(size_robot);
                    robot_rect.setWidth(size_robot);

                    robot_anchorPane.setMinSize(size_robot, size_robot);

                    AnchorPane.setTopAnchor(robot_rect, 0.);
                    AnchorPane.setRightAnchor(robot_rect, 0.);
                    AnchorPane.setLeftAnchor(robot_rect, 0.);
                    AnchorPane.setBottomAnchor(robot_rect, 0.);

                    robot_anchorPane.getChildren().add(robot_rect);
                    switch (robot.getStatut()) {
                        case "en course":
                            package_rect.setFill(new ImagePattern(new Image("box_top_view.png")));

                            //style
                            package_rect.setHeight(size_robot - 20);
                            package_rect.setWidth(size_robot - 20);

                            AnchorPane.setTopAnchor(package_rect, 10.);
                            AnchorPane.setLeftAnchor(package_rect, 10.);

                            robot_anchorPane.getChildren().add(package_rect);
                            map_gridpane.add(robot_anchorPane, robot.getX(), robot.getY());
                            break;
                        case "pret":
                            robot_anchorPane.getChildren().remove(package_rect);
                            if (la_poste_offset_x < 5) {
                                la_poste_GridPane.add(robot_anchorPane, la_poste_offset_x, la_poste_offset_y);
                                la_poste_offset_x++;
                            } else {
                                la_poste_offset_y++;
                                la_poste_offset_x = 0;
                            }
                            break;
                        case "retour":
                            robot_anchorPane.getChildren().remove(package_rect);
                            map_gridpane.add(robot_anchorPane, robot.getX(), robot.getY());
                            break;
                    }
                }
            }
        }

        return map_scrollPane;
    }
}
