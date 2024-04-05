package com.sandstrom;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MenuBar extends HBox {

    private Scene registerNewtaffScene, updateStaffScene, registerNewCustomerScene,
            updateCustomerScene, registerNewStoreScene, updateStoreScene, checkOutScene, searchFilmScene,
            registerFilmScene;
    private Button   menuBtnCheckout;
    private MenuButton menuBtnCustomer, menuBtnStore, menuBtnStaff, menuBtnFilm;

    private MenuItem menuItemRegisterCustomer, menuItemUpdateCustomer,menuItemSeeCustomer, menuItemRegisterStore,
            menuItemUpdateStore, menuItemShowCustomers, menuItemShowStaff, menuItemShowStores, menuItemShowAllRentals;
    Label labelLogo;

    public MenuBar(Stage primaryStage, Scene registerNewStaffScene, Scene updateStaffScene,
                   Scene registerNewCustomerScene, Scene updateCustomerScene, Scene registerNewStoreScene,
                   Scene updateStoreScene, Scene checkOutScene, Scene searchFilmScene, Scene registerFilmScene,
                   Scene showCustomersScene, Scene showStaffScene, Scene showStoresScene, Scene showRentalScene) {
        this.registerNewtaffScene = registerNewtaffScene;
        this.updateStaffScene = updateStaffScene;
        this.registerNewCustomerScene = registerNewCustomerScene;
        this.updateCustomerScene = updateCustomerScene;
        this.registerNewStoreScene = registerNewStoreScene;
        this.updateStoreScene = updateStoreScene;
        this.checkOutScene = checkOutScene;
        this.searchFilmScene = searchFilmScene;
        this.registerFilmScene = registerFilmScene;

        // Sökväg till bildfil till logga
        String imagePathLogo = "C:\\Users\\annak\\IdeaProjects\\WigellVideo1\\WigellVideoLogo.png";

        Image image = new Image("file:" + imagePathLogo);


        // Skapa en ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // Ange önskad bredd
        imageView.setPreserveRatio(true); // Bevara bildens proportioner


        labelLogo = new Label("Wigell Video");
        labelLogo.setStyle("-fx-font-family: Broadway;" +
                "-fx-font-size: 30;" +
                "-fx-text-fill: #0C191D");

        menuBtnStaff = new MenuButton("Personal");
        MenuItem menuItemRegNewStaff = new MenuItem("Registrera ny personal");
        menuItemRegNewStaff.setOnAction(e->primaryStage.setScene(registerNewStaffScene));
        MenuItem menuItemUpdateStaff = new MenuItem("Uppdatera personal");
        menuItemUpdateStaff.setOnAction(e->primaryStage.setScene(updateStaffScene));
        menuItemShowStaff = new MenuItem("Se befintlig personal");
        menuItemShowStaff.setOnAction(e->primaryStage.setScene(showStaffScene));

        menuBtnStaff.getItems().addAll(menuItemRegNewStaff, menuItemUpdateStaff, menuItemShowStaff);



        // Dropdownmeny för kund
        menuBtnCustomer = new MenuButton("Kund");
        menuBtnCustomer.getStylesheets().add("style.css");
        menuItemRegisterCustomer = new MenuItem("Registrera ny kund");
        menuItemRegisterCustomer.setOnAction(e -> primaryStage.setScene(registerNewCustomerScene));
        menuItemUpdateCustomer = new MenuItem("Uppdatera befintlig kund");
        menuItemUpdateCustomer.setOnAction(e-> primaryStage.setScene(updateCustomerScene));
        menuItemShowCustomers= new MenuItem("Se befintliga kunder");
        menuItemShowCustomers.setOnAction(e->primaryStage.setScene(showCustomersScene));
        menuBtnCustomer.getItems().addAll(menuItemRegisterCustomer, menuItemUpdateCustomer, menuItemShowCustomers);



        //   Dropdownmeny för Store
        menuBtnStore = new MenuButton("Butik");
       menuItemRegisterStore = new MenuItem("Registrera ny butik");
        menuItemRegisterStore.setOnAction(e-> primaryStage.setScene(registerNewStoreScene));
        menuItemUpdateStore = new MenuItem("Uppdatera butik");
        menuItemUpdateStore.setOnAction(e-> primaryStage.setScene(updateStoreScene));
        menuItemShowStores = new MenuItem("Se butiker");
        menuItemShowStores.setOnAction(e-> primaryStage.setScene(showStoresScene));
        menuItemShowAllRentals = new MenuItem("Visa uthyrningshistorik");
        menuItemShowAllRentals.setOnAction(e-> primaryStage.setScene(showRentalScene));
        menuBtnStore.getItems().addAll(menuItemRegisterStore, menuItemUpdateStore, menuItemShowStores, menuItemShowAllRentals);



        //Menyknapp för kassa
        menuBtnCheckout = new Button("Kassa");
        menuBtnCheckout.setStyle("-fx-alignment: center-left;"+
                "-fx-text-fill: #F9F7DC;");
        menuBtnCheckout.setOnAction(e->primaryStage.setScene(checkOutScene));


        //  Menyknapp för film
        menuBtnFilm = new MenuButton("Film");

        MenuItem menuItemSearchFilm = new MenuItem("Sök film");
        menuItemSearchFilm.setOnAction(e-> primaryStage.setScene(searchFilmScene));
        MenuItem menuItemRegisterNewFilm = new MenuItem("Registrera ny film");
        menuItemRegisterNewFilm.setOnAction(e-> primaryStage.setScene(registerFilmScene));
        menuBtnFilm.getItems().addAll(menuItemSearchFilm, menuItemRegisterNewFilm);
        menuItemSearchFilm.getStyleClass().add("menu-item");

        menuBtnCustomer.getStyleClass().add("menu-button");
        menuBtnStore.getStyleClass().add("menu-button");
        menuBtnFilm.getStyleClass().add("menu-button");


        this.getChildren().addAll(imageView,menuBtnCheckout,menuBtnCustomer, menuBtnStaff,  menuBtnStore, menuBtnFilm);
        this.setStyle("-fx-alignment: center; " +
                "-fx-spacing: 15px;" +
                "-fx-padding:10;");
        this.getStylesheets().add("style.css");
    }
}