package com.example.superviseur.classe;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
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
    //TODO add map coord on admin
    private final int size_intersection = 20, size_road = 25, house_size_icon = 20, arrow_size_icon = 10;
    private final WebService webService;
    private final Data data;
    public ObservableList<Integer[]> map_admin_add = FXCollections.observableArrayList();
    private int la_poste_offset_x = 0, la_poste_offset_y = 0;


    public Map() {
        webService = new WebService();

        //webService.setHttpRequest(ADDRESS, "croisements", WebService.GET, null, TIMEOUT);
        //TODO add intersection in array
        data = new Data();
    }


    /**
     * returns a dynamic map with different mode
     *
     * @param poste
     * @param admin
     * @return ScrollPane
     */

    public ScrollPane display_map(boolean poste, boolean admin) {
        //init
        AnchorPane content_AnchorPane = new AnchorPane();

        //init quartier
        ScrollPane map_scrollPane = new ScrollPane();
        GridPane map_gridpane = new GridPane();

        content_AnchorPane.getChildren().add(map_gridpane);
        map_scrollPane.setContent(content_AnchorPane);

        //style
        map_gridpane.getStyleClass().add("map_gridpane");
        map_gridpane.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(map_scrollPane, 50.);
        AnchorPane.setLeftAnchor(map_scrollPane, 10.);
        AnchorPane.setRightAnchor(map_scrollPane, 10.);
        AnchorPane.setBottomAnchor(map_scrollPane, 10.);


        if (admin) {
            //init
            AnchorPane.setLeftAnchor(map_gridpane, 20.);
            AnchorPane.setTopAnchor(map_gridpane, 20.);
            GridPane intersection_GridPane = new GridPane();
            Button top_button_clip = new Button(), bottom_button_clip = new Button(), right_button_clip = new Button(), left_button_clip = new Button();

            //style
            intersection_GridPane.setMinSize(size_intersection, size_intersection);
            intersection_GridPane.setStyle("-fx-background-color: gray");

            Rectangle arrow_T = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                    arrow_B = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                    arrow_R = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                    arrow_L = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png")));

            arrow_L.setRotate(180);
            arrow_T.setRotate(270);
            arrow_B.setRotate(90);

            top_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
            bottom_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
            right_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
            left_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);

            top_button_clip.setGraphic(arrow_T);
            bottom_button_clip.setGraphic(arrow_B);
            right_button_clip.setGraphic(arrow_R);
            left_button_clip.setGraphic(arrow_L);

            intersection_GridPane.add(top_button_clip, 1, 0);
            intersection_GridPane.add(bottom_button_clip, 1, 2);
            intersection_GridPane.add(right_button_clip, 2, 1);
            intersection_GridPane.add(left_button_clip, 0, 1);


            top_button_clip.setOnAction(event -> {
                top_button_clip.setDisable(true);
                add_intersection(map_gridpane, intersection_GridPane, "top");
            });
            bottom_button_clip.setOnAction(event -> {
                bottom_button_clip.setDisable(true);
                add_intersection(map_gridpane, intersection_GridPane, "bottom");
            });
            right_button_clip.setOnAction(event -> {
                right_button_clip.setDisable(true);
                add_intersection(map_gridpane, intersection_GridPane, "right");
            });
            left_button_clip.setOnAction(event -> {
                left_button_clip.setDisable(true);
                add_intersection(map_gridpane, intersection_GridPane, "left");
            });


            Platform.runLater(() -> {
                //add
                map_gridpane.add(intersection_GridPane, map_gridpane.getColumnCount(), map_gridpane.getRowCount());
                if (GridPane.getRowIndex(intersection_GridPane) == 0) {
                    top_button_clip.setDisable(true);
                }
                if (GridPane.getColumnIndex(intersection_GridPane) == 0) {
                    left_button_clip.setDisable(true);
                }
            });
        } else {

            if (data.getIntersections().size() > 0) {
                for (Intersection intersection : data.getIntersections()) {
                    //init
                    Rectangle intersection_rect = new Rectangle(size_intersection, size_intersection, Color.BLACK), road_bottom = new Rectangle(size_road, 100 + (house_size_icon * 2), Color.GRAY), road_right = new Rectangle(100 + (house_size_icon * 2), size_road, Color.GRAY);
                    //style
                    GridPane.setValignment(intersection_rect, VPos.CENTER);
                    GridPane.setHalignment(intersection_rect, HPos.CENTER);

                    GridPane.setValignment(road_bottom, VPos.CENTER);
                    GridPane.setHalignment(road_bottom, HPos.CENTER);

                    GridPane.setValignment(road_right, VPos.CENTER);
                    GridPane.setHalignment(road_right, HPos.CENTER);

                    //add
                    if (intersection.getX() == 0 && intersection.getY() == 0) {
                        map_gridpane.add(intersection_rect, intersection.getX(), intersection.getY());
                    } else {
                        map_gridpane.add(intersection_rect, intersection.getX() * 2, intersection.getY() * 2);
                    }
                    map_gridpane.add(road_right, intersection.getX() + 1, intersection.getY());
                    map_gridpane.add(road_bottom, intersection.getX(), intersection.getY() + 1);

                    //houses
                    for (House house : intersection.getHouses_list()) {
                        System.out.println(house.getNumber());
                        //init
                        AnchorPane district_AnchorPane = new AnchorPane();
                        Button house_button = new Button();

                        //event
                        house_button.setOnAction(event -> {
                            //MainController.setSelected_house();
                        });

                        //style
                        Rectangle house_rect = new Rectangle(house_size_icon, house_size_icon, new ImagePattern(new Image("house.png")));

                        house_button.setGraphic(house_rect);

                        house_button.setWrapText(true);

                        GridPane.setValignment(district_AnchorPane, VPos.CENTER);
                        GridPane.setHalignment(district_AnchorPane, HPos.CENTER);
                        district_AnchorPane.setMinSize(100, 100);

                        switch (house.getNumber()) {
                            case 1:
                                //top
                                AnchorPane.setTopAnchor(house_button, 0.0);
                                AnchorPane.setRightAnchor(house_button, (district_AnchorPane.getMinWidth() / 2) - (house_button.getMinWidth() / 2));
                                AnchorPane.setLeftAnchor(house_button, (district_AnchorPane.getMinWidth() / 2) - (house_button.getMinWidth() / 2));
                                district_AnchorPane.getChildren().add(house_button);
                                break;
                            case 2:
                                //right
                                AnchorPane.setRightAnchor(house_button, 0.0);
                                AnchorPane.setTopAnchor(house_button, (district_AnchorPane.getMinHeight() / 2) - (house_button.getMinHeight() - 2));
                                AnchorPane.setBottomAnchor(house_button, (district_AnchorPane.getMinHeight() / 2) - (house_button.getMinHeight() / 2));
                                district_AnchorPane.getChildren().add(house_button);
                                break;
                            case 3:
                                //bottom
                                AnchorPane.setBottomAnchor(house_button, 0.0);
                                AnchorPane.setRightAnchor(house_button, (district_AnchorPane.getMinWidth() / 2) - (house_button.getMinWidth() / 2));
                                AnchorPane.setLeftAnchor(house_button, (district_AnchorPane.getMinWidth() / 2) - (house_button.getMinWidth() / 2));
                                district_AnchorPane.getChildren().add(house_button);
                                break;
                            case 4:
                                //left
                                AnchorPane.setLeftAnchor(house_button, 0.0);
                                AnchorPane.setTopAnchor(house_button, (district_AnchorPane.getMinHeight() / 2) - (house_button.getMinHeight() - 2));
                                AnchorPane.setBottomAnchor(house_button, (district_AnchorPane.getMinHeight() / 2) - (house_button.getMinHeight() / 2));
                                district_AnchorPane.getChildren().add(house_button);
                                break;
                            default:
                                break;
                        }

                        //district
                        map_gridpane.add(district_AnchorPane, intersection.getX() + 1, intersection.getY() + 1);
                    }
                }
            } else {
                //DEFAULT
                int size = 4, house_size_icon = 20;

                for (int y = 0; y < (size * 2); y += 2) {
                    for (int x = 0; x < (size * 2); x += 2) {
                        AnchorPane houses_AnchorPane = new AnchorPane();
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
                            Rectangle road = new Rectangle(100, size_road, Color.GRAY);
                            map_gridpane.add(road, x + 1, y);
                            GridPane.setValignment(road, VPos.CENTER);
                            GridPane.setHalignment(road, HPos.CENTER);
                        }
                        if (y + 1 < (size * 2) - 1) {
                            //bottom line
                            Rectangle road = new Rectangle(size_road, 100, Color.GRAY);
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
            }
            if (poste) {
                //init la poste
                double width_la_poste = 300, road_1_top = 50;
                AnchorPane.setTopAnchor(map_gridpane, (50. + road_1_top) - ((size_intersection - size_road) / 2));
                AnchorPane.setLeftAnchor(map_gridpane, width_la_poste);
                map_gridpane.setLayoutX(width_la_poste);
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
                if (data.getRobots() != null) {
                    if (!data.getRobots().isEmpty()) {
                        for (Robot robot : data.getRobots()) {
                            AnchorPane robot_anchorPane = new AnchorPane();
                            Rectangle robot_rect = new Rectangle();
                            robot_rect.setFill(new ImagePattern(new Image("robot_top_view.png")));
                            Rectangle package_rect = new Rectangle();

                            //style
                            int size_robot = 50;
                            robot_rect.setHeight(size_robot);
                            robot_rect.setWidth(size_robot);

                            robot_anchorPane.setMinSize(size_robot, size_robot);

                            AnchorPane.setTopAnchor(robot_rect, 0.);
                            AnchorPane.setRightAnchor(robot_rect, 0.);
                            AnchorPane.setLeftAnchor(robot_rect, 0.);
                            AnchorPane.setBottomAnchor(robot_rect, 0.);

                            robot_anchorPane.getChildren().add(robot_rect);
                            switch (robot.getIdentifiant()) {
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

            }
        }


        return map_scrollPane;
    }

    /**
     * Generate a new intersection with event in recursive
     *
     * @param map_gridpane
     * @param intersection_GridPane
     * @param direction
     */
    private void add_intersection(GridPane map_gridpane, GridPane intersection_GridPane, String direction) {
        //init
        GridPane new_intersection_GridPane = new GridPane();
        Button top_button_clip = new Button(), bottom_button_clip = new Button(), right_button_clip = new Button(), left_button_clip = new Button();

        //style
        new_intersection_GridPane.setMinSize(size_intersection, size_intersection);
        new_intersection_GridPane.setStyle("-fx-background-color: gray");


        Rectangle arrow_T = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                arrow_B = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                arrow_R = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png"))),
                arrow_L = new Rectangle(arrow_size_icon, arrow_size_icon, new ImagePattern(new Image("arrow.png")));

        arrow_L.setRotate(180);
        arrow_T.setRotate(270);
        arrow_B.setRotate(90);

        top_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
        bottom_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
        right_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);
        left_button_clip.setMinSize(arrow_size_icon, arrow_size_icon);

        top_button_clip.setGraphic(arrow_T);
        bottom_button_clip.setGraphic(arrow_B);
        right_button_clip.setGraphic(arrow_R);
        left_button_clip.setGraphic(arrow_L);

        new_intersection_GridPane.add(top_button_clip, 1, 0);
        new_intersection_GridPane.add(bottom_button_clip, 1, 2);
        new_intersection_GridPane.add(right_button_clip, 2, 1);
        new_intersection_GridPane.add(left_button_clip, 0, 1);


        top_button_clip.setOnAction(event -> {
            top_button_clip.setDisable(true);
            add_intersection(map_gridpane, new_intersection_GridPane, "top");
        });
        bottom_button_clip.setOnAction(event -> {
            bottom_button_clip.setDisable(true);
            add_intersection(map_gridpane, new_intersection_GridPane, "bottom");
        });
        right_button_clip.setOnAction(event -> {
            right_button_clip.setDisable(true);
            add_intersection(map_gridpane, new_intersection_GridPane, "right");
        });
        left_button_clip.setOnAction(event -> {
            left_button_clip.setDisable(true);
            add_intersection(map_gridpane, new_intersection_GridPane, "left");
        });
        Rectangle road_vertical_clip = new Rectangle(size_road, 100 + (house_size_icon * 2), Color.GREY), road_horizontal_clip = new Rectangle(100 + (house_size_icon * 2), size_road, Color.GREY);

        //event

        Platform.runLater(() -> {

            //style

            GridPane.setValignment(road_vertical_clip, VPos.CENTER);
            GridPane.setHalignment(road_vertical_clip, HPos.CENTER);

            GridPane.setValignment(road_horizontal_clip, VPos.CENTER);
            GridPane.setHalignment(road_horizontal_clip, HPos.CENTER);

            //add

            switch (direction) {
                case "top":
                    bottom_button_clip.setDisable(true);
                    if (GridPane.getRowIndex(intersection_GridPane) - 2 >= 0) {
                        if (check_empty_position(GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) - 2, map_gridpane)) {
                            map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) - 2);
                        }
                        map_gridpane.add(road_vertical_clip, GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) - 1);
                        map_admin_add.add(new Integer[]{GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) - 1});
                    }

                    break;
                case "bottom":
                    top_button_clip.setDisable(true);
                    if (GridPane.getRowIndex(intersection_GridPane) + 2 < map_gridpane.getRowCount()) {
                        if (check_empty_position(GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) + 2, map_gridpane)) {
                            map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) + 2);
                        }
                    } else {
                        map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) + 2);
                    }
                    map_gridpane.add(road_vertical_clip, GridPane.getColumnIndex(intersection_GridPane), GridPane.getRowIndex(intersection_GridPane) + 1);
                    break;
                case "left":
                    right_button_clip.setDisable(true);
                    if (GridPane.getColumnIndex(intersection_GridPane) - 2 >= 0) {
                        if (check_empty_position(GridPane.getColumnIndex(intersection_GridPane) - 2, GridPane.getRowIndex(intersection_GridPane), map_gridpane)) {
                            map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane) - 2, GridPane.getRowIndex(intersection_GridPane));
                        }
                        map_gridpane.add(road_horizontal_clip, GridPane.getColumnIndex(intersection_GridPane) - 1, GridPane.getRowIndex(intersection_GridPane));
                    }
                    break;
                case "right":
                    left_button_clip.setDisable(true);
                    if (GridPane.getColumnIndex(intersection_GridPane) + 2 < map_gridpane.getColumnCount()) {
                        if (check_empty_position(GridPane.getColumnIndex(intersection_GridPane) + 2, GridPane.getRowIndex(intersection_GridPane), map_gridpane)) {
                            map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane) + 2, GridPane.getRowIndex(intersection_GridPane));
                        }
                    } else {
                        map_gridpane.add(new_intersection_GridPane, GridPane.getColumnIndex(intersection_GridPane) + 2, GridPane.getRowIndex(intersection_GridPane));
                    }
                    map_gridpane.add(road_horizontal_clip, GridPane.getColumnIndex(intersection_GridPane) + 1, GridPane.getRowIndex(intersection_GridPane));
                    break;
                default:
                    break;
            }
            //check x = 0 && y = 0
            if (map_gridpane.getChildren().contains(new_intersection_GridPane)) {
                if (GridPane.getRowIndex(new_intersection_GridPane) == 0) {
                    top_button_clip.setDisable(true);
                }
                if (GridPane.getColumnIndex(new_intersection_GridPane) == 0) {
                    left_button_clip.setDisable(true);
                }
            }
        });
    }

    /**
     * Check if at x & y there is a node
     *
     * @param column
     * @param row
     * @param gridPane
     * @return boolean
     */
    public boolean check_empty_position(int column, int row, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return false;
            }
        }
        return true;
    }


}
