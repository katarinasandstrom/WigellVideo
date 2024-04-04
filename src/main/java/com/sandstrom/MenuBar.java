package com.sandstrom;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuBar extends HBox {

    private Scene registerNewtaffScene, updateStaffScene, registerNewCustomerScene,
            updateCustomerScene, storeScene, checkOutScene, filmScene;
    private Button menuBtnStaff,  menuBtnStore, menuBtnCheckout, menuBtnFilm;
    private MenuButton menuBtnCustomer;

    private MenuItem menuItemRegisterCustomer, menuItemUpdateCustomer;
    Label labelLogo;

    public MenuBar(Stage primaryStage, Scene registerNewStaffScene,Scene updateStaffScene,
                   Scene registerNewCustomerScene, Scene updateCustomerScene, Scene storeScene,
                   Scene checkOutScene, Scene filmScene) {
        this.registerNewtaffScene = registerNewtaffScene;
        this.updateStaffScene = updateStaffScene;
        this.registerNewCustomerScene = registerNewCustomerScene;
        this.updateCustomerScene = updateCustomerScene;
        this.storeScene = storeScene;
        this.checkOutScene = checkOutScene;
        this.filmScene = filmScene;

        labelLogo = new Label("Wigell Video");
        labelLogo.setStyle("-fx-font-family: Broadway;" +
                "-fx-font-size: 40;" +
                "-fx-padding: 10;" +
                "-fx-text-fill: #0C191D;");

        menuBtnStaff = new Button("Personal");
        menuBtnStaff.setStyle("-fx-max-width: 95; " +
                "-fx-min-width: 95; " +
                "-fx-max-height: 35; " +
                "-fx-min-height: 35;" +
                "-fx-background-color: #CE8DA7;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;" +
                "-fx-border-width: 2px");



        // DropdownKnapp för Kund
        menuBtnCustomer = new MenuButton("Kund");
        menuItemRegisterCustomer = new MenuItem("Registrera ny kund");
        menuItemRegisterCustomer.setOnAction(e -> primaryStage.setScene(registerNewCustomerScene));
        menuItemUpdateCustomer = new MenuItem("Uppdatera befintlig kund");
        menuItemUpdateCustomer.setOnAction(e-> primaryStage.setScene(updateCustomerScene));
        menuBtnCustomer.getItems().addAll(menuItemRegisterCustomer, menuItemUpdateCustomer);


        menuBtnCustomer.setStyle("-fx-max-width: 95; " +
                "-fx-min-width: 95; " +
                "-fx-max-height: 35; " +
                "-fx-min-height: 35;" +
                "-fx-background-color: #CE8DA7;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;" +
                "-fx-border-width: 2px");
       /* menuBtnCustomer.setOnAction(e -> {
            primaryStage.setScene(registerNewCustomerScene);
            System.out.println("Nu är vi på Kund-sidan");
        });*/

        menuBtnStore = new Button("Butik");
        menuBtnStore.setStyle("-fx-max-width: 95; " +
                "-fx-min-width: 95; " +
                "-fx-max-height: 35; " +
                "-fx-min-height: 35;" +
                "-fx-background-color: #CE8DA7;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;" +
                "-fx-border-width: 2px");

        menuBtnStore.setOnAction(e -> {
            primaryStage.setScene(storeScene);
            System.out.println("Nu är vi på Butik-sidan");
        });

        menuBtnCheckout = new Button("Kassa");
        menuBtnCheckout.setStyle("-fx-max-width: 95; " +
                "-fx-min-width: 95; " +
                "-fx-max-height: 35; " +
                "-fx-min-height: 35;" +
                "-fx-background-color: #CE8DA7;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;" +
                "-fx-border-width: 2px");

        menuBtnCheckout.setOnAction(e -> {
            primaryStage.setScene(checkOutScene);
            System.out.println("Nu är vi på Kassa-sidan");
        });

        menuBtnFilm = new Button("Film");
        menuBtnFilm.setStyle("-fx-max-width: 95; " +
                "-fx-min-width: 95; " +
                "-fx-max-height: 35; " +
                "-fx-min-height: 35;" +
                "-fx-background-color: #CE8DA7;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;" +
                "-fx-border-width: 2px");

        menuBtnFilm.setOnAction(e -> {
            primaryStage.setScene(filmScene);
            System.out.println("Nu är vi på Film-sidan");
        });

        this.getChildren().addAll(labelLogo,menuBtnCheckout,menuBtnCustomer, menuBtnStaff,  menuBtnStore, menuBtnFilm);
        this.setStyle("-fx-alignment: center; " +
                "-fx-spacing: 10px; ");
    }
}