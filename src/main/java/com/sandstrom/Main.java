package com.sandstrom;

import com.sandstrom.entities.Address;
import com.sandstrom.entities.City;
import com.sandstrom.entities.Country;
import com.sandstrom.entities.Customer;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;


import static com.sandstrom.Methods.login;

public class Main extends Application {
    Scene loginScene, staffScene, customerScene, registerNewStoreScene,updateStoreScene, checkOutScene, searchFilmScene,registerFilmScene,  registerNewStaffScene,
           updateStaffScene, registerNewCustomerScene, updateCustomerScene,  firstPageScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene;
    BorderPane borderPaneLogin,borderPaneRegNewCustomer,borderPaneUpdateCustomer, borderPaneRegisterStore,
            borderPanecheckOut, borderPaneRegisterFilm,borderPaneSearchFilm, borderPaneUpdateStaff,borderPaneRegNewStaff, borderPaneFirstPage, borderPaneUpdateStore,
    borderPaneShowCustomers, borderPaneShowStaff, borderPaneShowStores, borderPaneShowRentals;
    MenuBar menuBarLogin, menuBarRegNewStaff,menuBarUpdateStaff, menuBarRegisterNewCustomer,menuBarUpdateCustomer, menuBarRegStore, menuBarCheckOut, menuBarFilm,  menuBarFirstPage,
            menuBarUpdateStore,menuBarSearchFilm, menuBarRegisterFilm,  menuBarShowCustomers, menuBarShowStaff, menuBarShowStores,  menuBarShowAllRentals;

    Label labelLogin, labelStaffChoice, labelErrorLogin, labelRegNewCustomer, labelUpdateCustomer, labelRegNewStore, labelShowCustomers;
    Button btnLogin, btnUpdateStaff, btnRent, btnCardPay, btnCashPay, btnRegStore, btnRegisterNewCustomer,  btnSearchCustomer;

    TextArea textAreaAllCustomers;
    TableView tableViewCustomers;
    TableColumn <Customer, Short> columnCustomerId;
    TableColumn<Customer, String>  columnFirstName, columnLastName, columnEmail;
    TableColumn <Customer, Byte> columnActive;
    TableColumn <Customer, Timestamp> columnCustomerCreateDate, columnLastUpdateCustomer;

    TableColumn <Address, Short> columnAddressId;
    TableColumn <Address, String> columnAddress, columnDistrict,
                     columnPostalCode, columnPhone, columnLocation;
    TableColumn <Address, Timestamp> columnLastUpdateAddress ;

    TableColumn <City, Short> columnCityId;
    TableColumn <City, String> columnCity;
    TableColumn<City, Timestamp> columnLastUpdateCity;
    TableColumn <Country, Short> columnCountryId;
    TableColumn <Country, String> columnCountry;
    TableColumn<Country, Timestamp> columnLastUpdateCountry;
    DatePicker datePickerRentalDate, datePickerReturnDate;

    TextField textFieldUsername, textFieldPassword, textFieldRegCustomerFName, textFieldRegCustomerLName,textFieldRegCustomerEmail, textFieldUpdateCustomerFName,
    textFieldUpdateCustomerLName, textFieldUpdateCustomerEmail,textFieldInventoryId, textFieldStaffId, textFieldCustomerId, textFieldAmount, textFieldManagerId,
            textFieldSearchCustomer ;

    VBox vBoxStaff, vBoxRegCustomer1, vBoxRegCustomer2, vBoxRegCustomer3, vBoxUpdateCustomer1, vBoxUpdateCustomer2, vBoxRegStore1, vBoxRegStore2,
    vBoxUpdateCustomer3, vBoxCheckOut, vBoxRegStore3, vBoxShowCustomers ;
    HBox hBoxregCustomer, hBoxUpdateCustomer,  hBoxCheckOutDatePickers, hBoxPayMethod,  hBoxId, hBoxRegStore, hBoxShowCustomers;
    StackPane stackPaneLogin;

    //Comment for push

    @Override
    public void start(Stage primaryStage) {

        // Sökväg till bildfil
        String imagePath = "C:\\Users\\annak\\IdeaProjects\\WigellVideo1\\9a1fbf03-a849-4816-a517-18e3a68d8724.png";


        // Skapar en ImageView och laddar in bilden
        ImageView imageViewVideoStore = new ImageView(new Image("file:" + imagePath));
        imageViewVideoStore.setFitWidth(primaryStage.getWidth());
        imageViewVideoStore.setFitHeight(primaryStage.getHeight());

        imageViewVideoStore.setPreserveRatio(true);


        // BorderPanes för varje scen
        borderPaneLogin = new BorderPane();
        borderPaneRegNewCustomer = new BorderPane();
        borderPaneUpdateCustomer = new BorderPane();
        borderPaneRegisterStore = new BorderPane();
        borderPaneUpdateStore = new BorderPane();
        borderPaneRegisterFilm = new BorderPane();
        borderPaneRegNewStaff = new BorderPane();
        borderPaneUpdateStaff = new BorderPane();
        borderPanecheckOut = new BorderPane();
        borderPaneSearchFilm = new BorderPane();
        borderPaneFirstPage = new BorderPane();
        borderPaneShowCustomers = new BorderPane();
        borderPaneShowStaff = new BorderPane();
        borderPaneShowStores = new BorderPane();
        borderPaneShowRentals = new BorderPane();


        //StackPanes
        stackPaneLogin = new StackPane();

        // scener
        loginScene = new Scene(stackPaneLogin, 1000, 700);
        loginScene.getStylesheets().add("style.css");
       /* firstPageScene = new Scene (borderPaneFirstPage, 1000, 700);
        firstPageScene.getStylesheets().add("style.css");
        Används inte längre
        */
        registerNewStaffScene = new Scene(borderPaneRegNewStaff, 1000, 700);
        registerNewStaffScene.getStylesheets().add("style.css");
        updateStaffScene = new Scene(borderPaneUpdateStaff, 1000, 700);
        updateStaffScene.getStylesheets().add("style.css");
        registerNewCustomerScene = new Scene(borderPaneRegNewCustomer, 1000, 700);
        registerNewCustomerScene.getStylesheets().add("style.css");
        updateCustomerScene = new Scene (borderPaneUpdateCustomer, 1000, 700);
        updateCustomerScene.getStylesheets().add("style.css");
        registerNewStoreScene = new Scene(borderPaneRegisterStore, 1000, 700);
        registerNewStoreScene.getStylesheets().add("style.css");
        updateStoreScene = new Scene( borderPaneUpdateStore, 1000, 700);
        updateStoreScene.getStylesheets().add("style.css");
        checkOutScene = new Scene(borderPanecheckOut, 1000, 700);

        searchFilmScene = new Scene(borderPaneSearchFilm, 1000, 700);
        searchFilmScene.getStylesheets().add("style.css");
        registerFilmScene = new Scene(borderPaneRegisterFilm, 1000, 700);
        registerFilmScene.getStylesheets().add("style.css");
        showCustomersScene= new Scene(borderPaneShowCustomers, 1000, 700);
        showCustomersScene.getStylesheets().add("style.css");
        showStaffScene = new Scene(borderPaneShowStaff, 1000, 700);
        showStaffScene.getStylesheets().add("style.css");
        showStoresScene = new Scene(borderPaneShowStores, 1000, 700);
        showStoresScene.getStylesheets().add("style.css");
        showRentalScene = new Scene(borderPaneShowRentals, 1000, 700);
        showRentalScene.getStylesheets().add("style.css");




                // menyer
        menuBarLogin = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarRegNewStaff = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarUpdateStaff = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarRegisterNewCustomer = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarUpdateCustomer = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarRegStore = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarUpdateStore = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarCheckOut =new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarSearchFilm = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarRegisterFilm = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarFirstPage =new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarShowCustomers= new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarShowStaff= new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarShowStores= new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);
        menuBarShowAllRentals = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, registerNewStoreScene, updateStoreScene,
                checkOutScene, searchFilmScene, registerFilmScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene);

        // Lägg till menyer till varje BorderPane
      //  borderPaneLogin.setTop(menuBarLogin);

        borderPaneRegNewCustomer.setTop(menuBarRegisterNewCustomer);
        borderPaneUpdateCustomer.setTop(menuBarUpdateCustomer);
        borderPaneRegNewStaff.setTop(menuBarRegNewStaff);
        borderPaneUpdateStaff.setTop(menuBarUpdateStaff);
        borderPaneRegisterStore.setTop(menuBarRegStore);
        borderPaneUpdateStore.setTop(menuBarUpdateStore);
        borderPanecheckOut.setTop(menuBarCheckOut);
        borderPaneSearchFilm.setTop(menuBarSearchFilm);
        borderPaneRegisterFilm.setTop(menuBarRegisterFilm);
        borderPaneFirstPage.setTop(menuBarFirstPage);
        borderPaneShowCustomers.setTop(menuBarShowCustomers);
        borderPaneShowStaff.setTop(menuBarShowStaff);
        borderPaneShowStores.setTop(menuBarShowStores);
        borderPaneShowRentals.setTop(menuBarShowAllRentals);



        // Ställ in startscenen
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Wigell Video");
        primaryStage.show();


        //LOGIN-SIDAN

      /*  labelLogin = new Label("Wigell Video");
        labelLogin.setMinSize(150,30);
        labelLogin.setMaxSize(150,30);
        labelLogin.setAlignment(Pos.CENTER);
        labelLogin.setStyle("-fx-font-family: Broadway;"+
                            "-fx-font-size: 21;" +
                            "-fx-text-fill: #303538;"+
                            "-fx-background-color: #f9f7dc;"+
                            "-fx-border-color: #303538;" +
                            "-fx-border-width: 3px");
*/


        textFieldUsername = new TextField();
        textFieldUsername.setPromptText("Användarnamn");
        textFieldUsername.setMinSize(150, 30);
        textFieldUsername.setMaxSize(150, 30);
     //
        textFieldPassword = new TextField();
        textFieldPassword.setPromptText("Lösenord");
        textFieldPassword.setMinSize(150, 30);
        textFieldPassword.setMaxSize(150, 30);
        textFieldPassword.setOnKeyPressed(e -> {
            String userName = textFieldUsername.getText();
            String passWord = textFieldPassword.getText();
            if (e.getCode().equals(KeyCode.ENTER)) {
                //  e.consume();
                if (login(userName, passWord)) {
                    primaryStage.setScene(checkOutScene);
                } else {
                    labelErrorLogin.setText("Fel inloggningsuppgifter. Försök igen.");
                }
            }
        });
        //String passWord = textFieldPassword.getText();

        labelErrorLogin = new Label(" ");
        btnLogin = new Button("Logga in");
        btnLogin.getStyleClass().add("btnLogin");
        btnLogin.getStylesheets().add("style.css");
        btnLogin.setMinSize(150, 30);
        btnLogin.setMaxSize(150, 30);
        btnLogin.setOnAction(e -> {
            String userName = textFieldUsername.getText();
            String passWord = textFieldPassword.getText();
            if (login(userName, passWord)) {
                primaryStage.setScene(checkOutScene);
            } else {
              //  primaryStage.setScene(firstPageScene);
                labelErrorLogin.setText("Fel inloggningsuppgifter. Försök igen.");
            }
        });


        VBox vBoxLogin1 = new VBox();
        vBoxLogin1.getChildren().add(imageViewVideoStore);
        vBoxLogin1.setAlignment(Pos.CENTER);
        borderPaneLogin.setCenter(vBoxLogin1);
        borderPaneLogin.setStyle("-fx-background-color: #BDF1FE");

        VBox vBoxLogin2 = new VBox();
        vBoxLogin2.getChildren().addAll( textFieldUsername, textFieldPassword, btnLogin, labelErrorLogin);
        vBoxLogin2.setAlignment(Pos.CENTER);
        vBoxLogin2.setSpacing(10);



        stackPaneLogin.getChildren().addAll(borderPaneLogin, vBoxLogin2);


        //REGISTRERA PERSONAL-SIDA






        btnUpdateStaff = new Button("Uppdatera befintlig personal");


        vBoxStaff = new VBox();

        borderPaneRegNewStaff.setCenter(vBoxStaff);

    //REGISTRERA KUND-SIDA

        labelRegNewCustomer = new Label("Registrera ny kund");
        textFieldRegCustomerFName = new TextField();
        textFieldRegCustomerFName.setPromptText("Förnamn");

        textFieldRegCustomerLName = new TextField();
        textFieldRegCustomerLName.setPromptText("Efternamn");

        textFieldRegCustomerEmail = new TextField();
        textFieldRegCustomerEmail.setPromptText("Mailadress");

        RegistryAddress customerAddress = new RegistryAddress();

        btnRegisterNewCustomer = new Button("Registrera kund");
        vBoxRegCustomer1 = new VBox();
        vBoxRegCustomer1.getChildren().addAll(textFieldRegCustomerFName,
                textFieldRegCustomerLName, textFieldRegCustomerEmail);
        vBoxRegCustomer1.setAlignment(Pos.CENTER);
        vBoxRegCustomer1.setSpacing(10);

        vBoxRegCustomer2 = new VBox();
        vBoxRegCustomer2 = new VBox();
        vBoxRegCustomer2.getChildren().addAll(customerAddress.getAddressView());
        vBoxRegCustomer2.setAlignment(Pos.CENTER);
        vBoxRegCustomer2.setSpacing(10);

        hBoxregCustomer = new HBox();
        hBoxregCustomer.getChildren().addAll(vBoxRegCustomer1, vBoxRegCustomer2);
        hBoxregCustomer.setAlignment(Pos.CENTER);
        hBoxregCustomer.setSpacing(10);

        vBoxRegCustomer3 = new VBox();
        vBoxRegCustomer3.getChildren().addAll(labelRegNewCustomer, hBoxregCustomer, btnRegisterNewCustomer );
        vBoxRegCustomer3.setSpacing(10);
        vBoxRegCustomer3.setAlignment(Pos.CENTER);
        borderPaneRegNewCustomer.setCenter(vBoxRegCustomer3);


        //UPPDATERA BEFINTLIG KUND


        labelUpdateCustomer = new Label ("Uppdatera befintlig kund");
        textFieldUpdateCustomerFName = new TextField();
        textFieldUpdateCustomerFName.setPromptText("Förnamn");

        textFieldUpdateCustomerLName = new TextField();
        textFieldUpdateCustomerLName.setPromptText("Efternamn");

        textFieldUpdateCustomerEmail = new TextField();
         textFieldUpdateCustomerEmail.setPromptText("Mailadress");

        RegistryAddress updateCustomerAddress = new RegistryAddress();

        ToggleButton toggleButtonCustomerIsActive = new ToggleButton("Aktiv");
        toggleButtonCustomerIsActive.setStyle("-fx-background-color: #A5A5A5, #737373; " +
                "-fx-background-insets: 0, 1; " +
                "-fx-background-radius: 3, 2; " +
                "-fx-text-fill: black;");

        //



        // Lyssnare för att hantera ändringar i knappens tillstånd
        toggleButtonCustomerIsActive.setOnAction(event -> {
            if (toggleButtonCustomerIsActive.isSelected()) {
                toggleButtonCustomerIsActive.setText("Inaktiv");

                // KOD FÖR ATT UPPDATERA OM KUNDEN HAR AVSLUTAT SITT MEDLEMSKAP
            } else {
                toggleButtonCustomerIsActive.setText("Aktiv");

                // KOD FÖR ATT UPPDATERA OM KUNDEN ÄR AKTIV
            }
        });
        vBoxUpdateCustomer1 = new VBox();
        vBoxUpdateCustomer1.getChildren().addAll(textFieldUpdateCustomerFName,
                textFieldUpdateCustomerLName, textFieldUpdateCustomerEmail);
        vBoxUpdateCustomer1.setAlignment(Pos.CENTER);
        vBoxUpdateCustomer1.setSpacing(10);

        vBoxUpdateCustomer2 = new VBox();
        vBoxUpdateCustomer2.getChildren().addAll(updateCustomerAddress.getAddressView());

        vBoxUpdateCustomer2.setAlignment(Pos.CENTER);
        vBoxUpdateCustomer2.setSpacing(10);

        hBoxUpdateCustomer = new HBox();
        hBoxUpdateCustomer.getChildren().addAll(vBoxUpdateCustomer1, vBoxUpdateCustomer2);
        hBoxUpdateCustomer.setAlignment(Pos.CENTER);
        hBoxUpdateCustomer.setSpacing(10);

        vBoxUpdateCustomer3 = new VBox();
        vBoxUpdateCustomer3.getChildren().addAll(labelUpdateCustomer, hBoxUpdateCustomer, toggleButtonCustomerIsActive);
        vBoxUpdateCustomer3.setSpacing(10);
        vBoxUpdateCustomer3.setAlignment(Pos.CENTER);
        borderPaneUpdateCustomer.setCenter(vBoxUpdateCustomer3);


        // SIDA FÖR ATT SE ALLA KUNDER

        labelShowCustomers = new Label("Kundöversikt");

        /* textAreaAllCustomers = new TextArea();
        textAreaAllCustomers.setMinSize(500, 500);
        textAreaAllCustomers.setMaxSize(500, 500);


         */
        tableViewCustomers = new TableView<>();
        tableViewCustomers.setMinSize(800, 500);
        tableViewCustomers.setMaxSize(800, 500);
        tableViewCustomers.setStyle("-fx-background-color: #F9F7DC;");


        tableViewCustomers.getItems().clear();

       columnCustomerId = new TableColumn<>("Kundid");
       columnFirstName = new TableColumn<>("Förnamn");
       columnLastName  = new TableColumn<>("Efternamn");
       columnEmail = new TableColumn<>("Email");
       columnActive = new TableColumn<>("Inaktiv");
       columnCustomerCreateDate = new TableColumn<>("Kund sedan");
       columnLastUpdateCustomer = new TableColumn<>("Senast uppdaterad");
       columnAddressId = new TableColumn<>("Addressid");
       columnAddress = new TableColumn<>("Adress");
       columnDistrict = new TableColumn<>("Distrikt");
       columnPostalCode = new TableColumn<>("Postnr");
       columnPhone = new TableColumn<>("Telefonnr");
       columnLocation = new TableColumn<>("Location");
       columnLastUpdateAddress = new TableColumn<>("Uppdaterad");
       columnCityId = new TableColumn<>("Postortsid");
       columnCity = new TableColumn<>("Postort");
       columnLastUpdateCity = new TableColumn<>("Uppdaterad");
       columnCountryId = new TableColumn<>("Landsid");
       columnCountry = new TableColumn<>("Land");
       columnLastUpdateCountry = new TableColumn<>("Uppdaterad");

        columnCustomerId.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCustomerId()));
        columnFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        columnEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnActive.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getActive()));
        columnCustomerCreateDate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCreateDate()));
        columnLastUpdateCustomer.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnAddressId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAddressId()));
        columnAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        columnDistrict.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDistrict()));
        columnPostalCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        columnPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        columnLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        columnLastUpdateAddress.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnCityId.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCityId()));
        columnCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        columnLastUpdateCity.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnCountryId.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCountryId()));
        columnCountry.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountry()));
        columnLastUpdateCountry.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));

        tableViewCustomers.getColumns().addAll( columnCustomerId,columnFirstName,columnLastName, columnEmail, columnActive, columnCustomerCreateDate,
        columnLastUpdateCustomer, columnAddressId, columnAddress, columnDistrict, columnPostalCode, columnPhone, columnLocation,
        columnLastUpdateAddress, columnCityId, columnCity, columnLastUpdateCity, columnCountryId, columnCountry, columnLastUpdateCountry);

      //  tableViewCustomers.setItems(customerList);
      //  tableViewCustomers.refresh();

        textFieldSearchCustomer = new TextField();
        textFieldSearchCustomer.setPromptText("Kundnummer");
        btnSearchCustomer= new Button("Sök kund");

        hBoxShowCustomers = new HBox();
        hBoxShowCustomers.getChildren().addAll(textFieldSearchCustomer, btnSearchCustomer);
        hBoxShowCustomers.setSpacing(10);
        hBoxShowCustomers.setAlignment(Pos.CENTER);

        vBoxShowCustomers = new VBox();
        vBoxShowCustomers.setAlignment(Pos.CENTER);
        vBoxShowCustomers.getChildren().addAll(labelShowCustomers,tableViewCustomers, hBoxShowCustomers);

        borderPaneShowCustomers.setCenter(vBoxShowCustomers);



// KASSA
        textFieldInventoryId = new TextField();
        textFieldStaffId = new TextField();
        textFieldCustomerId = new TextField();
        textFieldAmount = new TextField();

        btnRent = new Button("Lägg till film");
        btnCardPay = new Button("Kortbetalning");
        btnCashPay = new Button("Kontant betalning");

        datePickerRentalDate = new DatePicker();
        datePickerReturnDate = new DatePicker();


        datePickerRentalDate.setValue(LocalDate.now());

        textFieldInventoryId.setPromptText("Streckkod");
        textFieldStaffId.setPromptText("PersonalID");
        textFieldCustomerId.setPromptText("Kundnummer");
        textFieldAmount.setPromptText("Totalsumma");
        datePickerReturnDate.setPromptText("Återlämnas");

        textFieldInventoryId.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                "        -fx-text-fill: #303538;");
        textFieldStaffId.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;");
        textFieldCustomerId.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;");
        textFieldAmount.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;");
        datePickerReturnDate.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;");



        datePickerRentalDate.setMinSize(120, 40);
        datePickerRentalDate.setMaxSize(120, 40);
        datePickerReturnDate.setMinSize(120, 40);
        datePickerReturnDate.setMaxSize(120, 40);


        btnRent.setMinSize(120, 40);
        btnRent.setMaxSize(120, 40);
        btnCardPay.setMinSize(120, 40);
        btnCardPay.setMaxSize(120, 40);
        btnCashPay.setMinSize(120, 40);
        btnCashPay.setMaxSize(120, 40);

        btnRent.setStyle    ( "-fx-background-color: #C57C07;"+
        "-fx-text-fill: #F9F7DC;"+
        "-fx-font-size: 12px;"+
        "-fx-border-width: 2;"+
        "-fx-border-radius: 3;"+
        "-fx-max-height: 40;"+
        "-fx-min-height: 40;"+
        "-fx-min-width: 120;"+
        "-fx-max-width: 120;"+
        "-fx-background-color: linear-gradient(to bottom,#F69F13, #C57C07 );");
        btnCardPay.setStyle ( "-fx-background-color: #C57C07;"+
                "-fx-text-fill: #F9F7DC;"+
                "-fx-font-size: 12px;"+
                "-fx-border-width: 2;"+
                "-fx-border-radius: 3;"+
                "-fx-max-height: 40;"+
                "-fx-min-height: 40;"+
                "-fx-min-width: 120;"+
                "-fx-max-width: 120;"+
                "-fx-background-color: linear-gradient(to bottom,#F69F13, #C57C07 );");
        btnCashPay.setStyle ( "-fx-background-color: #C57C07;"+
                "-fx-text-fill: #F9F7DC;"+
                "-fx-font-size: 12px;"+
                "-fx-border-width: 2;"+
                "-fx-border-radius: 3;"+
                "-fx-max-height: 40;"+
                "-fx-min-height: 40;"+
                "-fx-min-width: 120;"+
                "-fx-max-width: 120;"+
                "-fx-background-color: linear-gradient(to bottom,#F69F13, #C57C07 );");

        textFieldInventoryId.setMinSize(390, 40);
        textFieldInventoryId.setMaxSize(390, 40);
        textFieldStaffId.setMinSize(188, 40);
        textFieldStaffId.setMaxSize(188, 40);
        textFieldCustomerId.setMinSize(188, 40);
        textFieldCustomerId.setMaxSize(188, 40);
        textFieldAmount.setMinSize(120, 40);
        textFieldAmount.setMaxSize(120, 40);

        hBoxCheckOutDatePickers = new HBox();
        hBoxCheckOutDatePickers.setAlignment(Pos.CENTER);
        hBoxCheckOutDatePickers.setSpacing(15);
        hBoxCheckOutDatePickers.getChildren().addAll(datePickerRentalDate, datePickerReturnDate, btnRent);

        hBoxPayMethod = new HBox();
        hBoxPayMethod.setAlignment(Pos.CENTER);
        hBoxPayMethod.setSpacing(15);
        hBoxPayMethod.setPadding(new Insets(40));
        hBoxPayMethod.getChildren().addAll(textFieldAmount, btnCardPay, btnCashPay);

        hBoxId = new HBox();
        hBoxId.setAlignment(Pos.CENTER);
        hBoxId.setSpacing(15);
        hBoxId.setPadding(new Insets(40));
        hBoxId.getChildren().addAll(textFieldCustomerId, textFieldStaffId);

        vBoxCheckOut = new VBox();
        vBoxCheckOut.setAlignment(Pos.CENTER);
        vBoxCheckOut.setSpacing(10);
        vBoxCheckOut.getChildren().addAll(hBoxId, textFieldInventoryId, hBoxCheckOutDatePickers, hBoxPayMethod);

        borderPanecheckOut.setCenter(vBoxCheckOut);
        borderPanecheckOut.setStyle("-fx-background-color: #94B1B3;");

        btnRent.setOnAction(e -> {
            System.out.println("Film tillagd\nHyresperiod: " + datePickerRentalDate.getValue() +
                    " - " + datePickerReturnDate.getValue());
        });

        //Kort och kontant betalning ska ha samma funktion, finns en av varje för syns skull
        btnCardPay.setOnAction(e -> {
            System.out.println("Kortbetalning utförd\nTotalsumma: " + textFieldAmount.getText() + "kr");
        });

        btnCashPay.setOnAction(e -> {
            System.out.println("Kontant betalning utförd\nTotalsumma: " + textFieldAmount.getText() + "kr");
        });


        //REGISTRERA NY BUTIK
        labelRegNewStore = new Label("Registrera ny butik");

        RegistryAddress addressStore = new RegistryAddress();
        textFieldManagerId = new TextField();
        textFieldManagerId.setMinSize(140,40);
        textFieldManagerId.setMaxSize(140,40);

        btnRegStore = new Button("Registrera butik");
        btnRegStore.setMinSize(140,40);
        btnRegStore.setMaxSize(140,40);

       // btnRegStore.setOnAction(); Kod för att lägga till ny butik, popup med butiksnr et, lastUpdate

        vBoxRegStore1 = new VBox();
        vBoxRegStore1.getChildren().addAll(addressStore.getAddressView());
        vBoxRegStore1.setSpacing(10);
        vBoxRegStore1.setAlignment(Pos.CENTER);
        vBoxRegStore2 = new VBox();
        vBoxRegStore2.getChildren().addAll(textFieldManagerId, btnRegStore);
        vBoxRegStore2.setSpacing(10);
        vBoxRegStore2.setAlignment(Pos.CENTER);

        hBoxRegStore = new HBox();
        hBoxRegStore.getChildren().addAll(vBoxRegStore1, vBoxRegStore2);
        hBoxRegStore.setAlignment(Pos.CENTER);
        hBoxRegStore.setSpacing(10);
        vBoxRegStore3 = new VBox();
        vBoxRegStore3.getChildren().addAll(labelRegNewStore, hBoxRegStore);
        vBoxRegStore3.setAlignment(Pos.CENTER);
        borderPaneRegisterStore.setCenter(vBoxRegStore3);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
