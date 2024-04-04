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
            updateCustomerScene, registerNewStoreScene, updateStoreScene, checkOutScene, searchFilmScene,
            registerFilmScene;
    private Button   menuBtnCheckout;
    private MenuButton menuBtnCustomer, menuBtnStore, menuBtnStaff, menuBtnFilm;

    private MenuItem menuItemRegisterCustomer, menuItemUpdateCustomer,menuItemSeeCustomer, menuItemRegisterStore,
            menuItemUpdateStore;
    Label labelLogo;

    public MenuBar(Stage primaryStage, Scene registerNewStaffScene,Scene updateStaffScene,
                   Scene registerNewCustomerScene, Scene updateCustomerScene, Scene registerNewStoreScene,
                   Scene updateStoreScene, Scene checkOutScene, Scene searchFilmScene, Scene registerFilmScene) {
        this.registerNewtaffScene = registerNewtaffScene;
        this.updateStaffScene = updateStaffScene;
        this.registerNewCustomerScene = registerNewCustomerScene;
        this.updateCustomerScene = updateCustomerScene;
        this.registerNewStoreScene = registerNewStoreScene;
        this.updateStoreScene = updateStoreScene;
        this.checkOutScene = checkOutScene;
        this.searchFilmScene = searchFilmScene;
        this.registerFilmScene = registerFilmScene;

        labelLogo = new Label("Wigell Video");
        labelLogo.setStyle("-fx-font-family: Broadway;" +
                "-fx-font-size: 30;" +
                "-fx-text-fill: #0C191D");

        menuBtnStaff = new MenuButton("Personal");
        MenuItem menuItemRegNewStaff = new MenuItem("Registrera ny personal");
        menuItemRegNewStaff.setOnAction(e->primaryStage.setScene(registerNewStaffScene));
        MenuItem menuItemUpdateStaff = new MenuItem("Uppdatera personal");
        menuItemUpdateStaff.setOnAction(e->primaryStage.setScene(updateStaffScene));

        menuBtnStaff.getItems().addAll(menuItemRegNewStaff, menuItemUpdateStaff);



        // Dropdownmeny för kund
        menuBtnCustomer = new MenuButton("Kund");
        menuBtnCustomer.getStylesheets().add("style.css");
        menuItemRegisterCustomer = new MenuItem("Registrera ny kund");
        menuItemRegisterCustomer.setOnAction(e -> primaryStage.setScene(registerNewCustomerScene));
        menuItemUpdateCustomer = new MenuItem("Uppdatera befintlig kund");
        menuItemUpdateCustomer.setOnAction(e-> primaryStage.setScene(updateCustomerScene));
        menuItemSeeCustomer = new MenuItem();
        menuBtnCustomer.getItems().addAll(menuItemRegisterCustomer, menuItemUpdateCustomer);




        //   Dropdownmeny för Store
        menuBtnStore = new MenuButton("Butik");
       menuItemRegisterStore = new MenuItem("Registrera ny butik");
        menuItemRegisterStore.setOnAction(e-> primaryStage.setScene(registerNewStoreScene));
        menuItemUpdateStore = new MenuItem("Uppdatera butik");
        menuItemUpdateStore.setOnAction(e-> primaryStage.setScene(updateStoreScene));
        menuBtnStore.getItems().addAll(menuItemRegisterStore, menuItemUpdateStore);
      /*  menuBtnStore.setStyle("-fx-max-width: 80; " +
                "-fx-min-width: 80; " +
                "-fx-max-height: 30; " +
                "-fx-min-height: 30;");*/


        //Menyknapp för kassa
        menuBtnCheckout = new Button("Kassa");
        menuBtnCheckout.setStyle("-fx-alignment: LEFT");




        //  Menyknapp för film
        menuBtnFilm = new MenuButton("Film");
       /* menuBtnFilm.setStyle("-fx-max-width: 80; " +
                "-fx-min-width: 80; " +
                "-fx-max-height: 30; " +
                "-fx-min-height: 30;");*/
        MenuItem menuItemSearchFilm = new MenuItem("Sök film");
        menuItemSearchFilm.setOnAction(e-> primaryStage.setScene(searchFilmScene));
        MenuItem menuItemRegisterNewFilm = new MenuItem("Registrera ny film");
        menuItemRegisterNewFilm.setOnAction(e-> primaryStage.setScene(registerFilmScene));
        menuBtnFilm.getItems().addAll(menuItemSearchFilm, menuItemRegisterNewFilm);

        this.getChildren().addAll(labelLogo,menuBtnCheckout,menuBtnCustomer, menuBtnStaff,  menuBtnStore, menuBtnFilm);
        this.setStyle("-fx-alignment: center; " +
                "-fx-spacing: 10px;" +
                "-fx-padding:10;");
        this.getStylesheets().add("style.css");
    }
}