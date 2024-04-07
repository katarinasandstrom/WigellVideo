package com.sandstrom;

import com.sandstrom.crudOperations.CrudOfCustomer;
import com.sandstrom.crudOperations.CrudOfStore;
import com.sandstrom.entities.*;
import jakarta.persistence.criteria.CriteriaQuery;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;


import static com.sandstrom.Methods.login;


public class Main extends Application {
    Scene loginScene, staffScene, customerScene, registerNewStoreScene,updateStoreScene, checkOutScene,
            searchFilmScene,registerFilmScene,  registerNewStaffScene, updateStaffScene, registerNewCustomerScene,
            updateCustomerScene,  firstPageScene, showCustomersScene, showStaffScene, showStoresScene, showRentalScene;
    BorderPane borderPaneLogin,borderPaneRegNewCustomer,borderPaneUpdateCustomer, borderPaneRegisterStore,
            borderPanecheckOut, borderPaneRegisterFilm,borderPaneSearchFilm, borderPaneUpdateStaff,
            borderPaneRegNewStaff, borderPaneFirstPage, borderPaneUpdateStore, borderPaneShowCustomers,
            borderPaneShowStaff, borderPaneShowStores, borderPaneShowRentals;
    MenuBar menuBarLogin, menuBarRegNewStaff,menuBarUpdateStaff, menuBarRegisterNewCustomer,menuBarUpdateCustomer,
            menuBarRegStore, menuBarCheckOut, menuBarFilm,  menuBarFirstPage, menuBarUpdateStore,menuBarSearchFilm,
            menuBarRegisterFilm,  menuBarShowCustomers, menuBarShowStaff, menuBarShowStores,  menuBarShowAllRentals;

    Label labelLogin, labelStaffChoice, labelErrorLogin, labelRegNewCustomer, labelUpdateCustomer, labelRegNewStore,
            labelShowCustomers, labelEmpty, labelUpdateStore, labelEmpty2, labelDuplicateCustomer, labelRegNewStaff,
            labelUpdateStaff,  labelShowStaff, labelRegNewFilm, labelSpecialFeatures;
    Button btnLogin, btnUpdateStaff, btnRent, btnCardPay, btnCashPay, btnRegStore, btnRegisterNewCustomer,
            btnSearchCustomer, btnSearchCustomerNr, btnFetchStoreInfo, btnUpdateStore,  btnRegisterNewStaff,
            btnSearchFilm, btnSearchStaff, btnUpdateCustomer, btnDeleteCustomer, BTN, btnRegisterNewFilm;
    MenuButton menuButtonStore, menuButtonStoreUpdate, menuButtonStore1, menuButtonRating;
    MenuItem  menuItemStore1Update, menuItemStore2Update, menuItemSt1, menuItemSt2, menuItemRating1, menuItemRating2,
            menuItemRating3, menuItemRating4, menuItemRating5;

    CheckBox checkBoxDeletedScenes, checkboxBehindTheScenes, checkBoxTrailers, checkBoxCommentaries;

    TextArea textAreaAllCustomers;

    private ObservableList<Customer> customerList;
    private ObservableList<Film> filmList;

    TableView tableViewCustomers, tableViewStaff, tableViewFilms;
    TableColumn <Customer, Short> columnCustomerId, columnAddressId;
    TableColumn<Customer, String>  columnFirstName, columnLastName, columnEmail;
    TableColumn <Customer, Byte> columnActive;
    TableColumn <Customer, Timestamp> columnCustomerCreateDate, columnLastUpdateCustomer;

    TableColumn <Address, Short>  columnStaffAddressId;
    TableColumn <Address, String> columnAddress, columnDistrict, columnPostalCode, columnPhone, columnLocation,
    columnStaffAddress, columnStaffDistrict, columnStaffPostalCode,columnStaffPhone;
    TableColumn <Address, Timestamp> columnLastUpdateAddress, columnStaffLastUpdateAddress;

    TableColumn <City, Short> columnCityId, columnStaffCityId;
    TableColumn <City, String> columnCity, columnStaffCity;
    TableColumn<City, Timestamp> columnLastUpdateCity, columnStaffLastUpdateCity ;
    TableColumn <Country, Short> columnCountryId,columnStaffCountryId;
    TableColumn <Country, String> columnCountry,columnStaffCountry ;
    TableColumn<Country, Timestamp> columnLastUpdateCountry,  columnStaffLastUpdateCountry;

   TableColumn<Staff, Short>   columnStoreId;
   TableColumn <Staff, String> columnStaffFirstName, columnStaffLastName,  columnStaffEmail,
           columnStaffUserName, columnStaffPassword;
   TableColumn <Staff, Byte> columnStaffId,columnStaffActive;
   TableColumn <Staff, Timestamp> columnStaffLastUpdate;

    TableColumn<Film, Short> columnFilmId;
    TableColumn<Film, String> columnTitle, columnDescription,
            columnRating, columnSpecialFeatures;
    TableColumn<Film, Integer> columnReleaseYear, columnLength,
            columnRentalDuration;
    TableColumn<Film, BigDecimal> columnRentalRate, columnReplacementCost;
    TableColumn<Film, Timestamp> columnLastUpdateFilm;

    TableColumn<Language, String> columnNameLanguage;
    TableColumn<Language, Timestamp> columnLastUpdateLanguage;

    TableColumn<Inventory, Integer> columnInventoryId;
    TableColumn<Inventory, Timestamp> columnLastUpdateInventory;

    TableColumn<Store, Short> columnStoreIdFilm;

    TableColumn<Actor, String> columnFirstNameActor, columnLastNameActor;
    TableColumn<Actor, Timestamp> columnLastUpdateActor;

    TableColumn<Category, String> columnCategory;
    TableColumn<Category, Timestamp> columnLastUpdateCategory;


    DatePicker datePickerRentalDate, datePickerReturnDate;

    TextField textFieldUsername, textFieldPassword, textFieldRegCustomerFName, textFieldRegCustomerLName,
            textFieldRegCustomerEmail, textFieldUpdateCustomerFName, textFieldUpdateCustomerLName,
            textFieldUpdateCustomerEmail,textFieldInventoryId, textFieldStaffId, textFieldCustomerId,
            textFieldAmount, textFieldManagerId, textFieldSearchCustomer,textFieldUpdateManagerId,
            textFieldSearchCustomerNr , textFieldStaffFName, textFieldStaffLName, textFieldStaffEmail,
            textFieldStaffUserName, textFieldStaffPassword,  textFieldUpdateStaffFName, textFieldUpdateStaffLName,
            textFieldUpdateStaffEmail, textFieldUpdateStaffUserName, textFieldUpdateStaffPassword,
            textFieldSearchStaff, textFieldSearchFilm, textFieldTitle, textFieldReleaseYear,
            textFieldRentalDuration, textFieldRentalRate, textFieldLength, textFieldReplacementCost,
            textFieldLanguage, textFieldActorFirstName, textFieldActorLastName;

    VBox vBoxStaff, vBoxRegCustomer1, vBoxRegCustomer2, vBoxRegCustomer3, vBoxUpdateCustomer1, vBoxUpdateCustomer2,
            vBoxRegStore1, vBoxRegStore2, vBoxUpdateCustomer3, vBoxCheckOut, vBoxRegStore3, vBoxShowCustomers,
            vBoxUpdateStore1, vBoxUpdateStore2, vBoxUpdateStore3,  vBoxUpdateCustomer4, vBoxRegStaff1, vBoxRegStaff2,
            vBoxRegStaff3, vBoxRegStaff4,  vBoxUpdateStaff1, vBoxUpdateStaff2, vBoxUpdateStaff3, vBoxUpdateStaff4,
            vBoxCheckout1, vBoxShowStaff, vBoxShowFilms, vBoxRegFilm, vBoxCheckBoxes;

    HBox hBoxregCustomer, hBoxUpdateCustomer2,  hBoxCheckOutDatePickers, hBoxPayMethod,  hBoxId, hBoxRegStore,
            hBoxShowCustomers, hBoxUpdateCustomer1, hBoxUpdateStore, hBoxRegStaff, hBoxUpdateStaff,hBoxShowStaff,
            hBoxSearchFilm, hBoxCheckOutPage, hBoxRegFilm1, hBoxRegFilm2, hBoxRegFilm3, hBoxRegFilm4, hBoxCheckBoxes;
    StackPane stackPaneLogin;

    TextArea textAreaCheckOut, textAreaDescription;

    //Comment for push
    boolean isSearchMode = true;
    boolean isSearchMode1 = true;
    CrudOfCustomer crudOfCustomers = new CrudOfCustomer();
    CrudOfStore crudOfStore = new CrudOfStore();
    @Override
    public void start(Stage primaryStage) {

        // Sökväg till bildfil
        String imagePath = "C:\\Users\\helga\\OneDrive\\Skrivbord\\Skola\\Software Development Process\\9a1fbf03-a849-4816-a517-18e3a68d8724.png";


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
        firstPageScene = new Scene (borderPaneFirstPage, 1000, 700);
        firstPageScene.getStylesheets().add("style.css");
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
        checkOutScene.getStylesheets().add("style.css");
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

        labelRegNewStaff = new Label("Registrera ny personal");

        textFieldStaffFName = new TextField();
        textFieldStaffLName= new TextField();
        textFieldStaffEmail= new TextField();
        textFieldStaffUserName= new TextField();
        textFieldStaffPassword= new TextField();

        textFieldStaffFName.setPromptText("Förnamn");
        textFieldStaffLName.setPromptText("Efternamn");
        textFieldStaffEmail.setPromptText("Email");
        textFieldStaffUserName.setPromptText("Användarnamn");
        textFieldStaffPassword.setPromptText("Lösenord");

        RegistryAddress regStaffAddress = new RegistryAddress();

        menuButtonStore = new MenuButton("Butik");
        menuButtonStore.setTextFill(Color.rgb(249, 247, 220));
        MenuItem menuItemStore1 = new MenuItem("Butik 1");
        MenuItem menuItemStore2 = new MenuItem("Butik 2");
        menuButtonStore.getItems().addAll(menuItemStore1, menuItemStore2);

        btnRegisterNewStaff = new Button("Registrera");

        vBoxRegStaff1 = new VBox();
        vBoxRegStaff2 = new VBox();
        vBoxRegStaff3 = new VBox();
        vBoxRegStaff4 = new VBox();
        hBoxRegStaff = new HBox();

        vBoxRegStaff1.getChildren().addAll(textFieldStaffFName, textFieldStaffLName, textFieldStaffEmail);
        vBoxRegStaff2.getChildren().addAll(regStaffAddress.getAddressView());
        vBoxRegStaff3.getChildren().addAll(textFieldStaffUserName, textFieldStaffPassword, menuButtonStore);

        vBoxRegStaff1.setAlignment(Pos.CENTER);
        vBoxRegStaff2.setAlignment(Pos.CENTER);
        vBoxRegStaff3.setAlignment(Pos.CENTER);
        vBoxRegStaff4.setAlignment(Pos.CENTER);
        hBoxRegStaff.setAlignment(Pos.CENTER);

        vBoxRegStaff1.setSpacing(10);
        vBoxRegStaff2.setSpacing(10);
        vBoxRegStaff3.setSpacing(10);
        vBoxRegStaff4.setSpacing(10);
        hBoxRegStaff.setSpacing(10);

        hBoxRegStaff.getChildren().addAll(vBoxRegStaff1, vBoxRegStaff2, vBoxRegStaff3);
        hBoxRegStaff.setSpacing(10);
        hBoxRegStaff.setAlignment(Pos.CENTER);

        vBoxRegStaff4.getChildren().addAll(hBoxRegStaff, btnRegisterNewStaff);

        borderPaneRegNewStaff.setCenter(vBoxRegStaff4);





        //UPPDATERA PERSONAL
        labelUpdateStaff = new Label("Registrera ny personal");

        textFieldUpdateStaffFName = new TextField();
        textFieldUpdateStaffLName= new TextField();
        textFieldUpdateStaffEmail= new TextField();
        textFieldUpdateStaffUserName= new TextField();
        textFieldUpdateStaffPassword= new TextField();

        textFieldUpdateStaffFName.setPromptText("Förnamn");
        textFieldUpdateStaffLName.setPromptText("Efternamn");
        textFieldUpdateStaffEmail.setPromptText("Email");
        textFieldUpdateStaffUserName.setPromptText("Användarnamn");
        textFieldUpdateStaffPassword.setPromptText("Lösenord");

        RegistryAddress updateStaffAddress = new RegistryAddress();

        menuButtonStoreUpdate = new MenuButton("Butik");
        menuButtonStoreUpdate.setTextFill(Color.rgb(249, 247, 220));
        menuItemStore1Update = new MenuItem("Butik 1");
        menuItemStore2Update = new MenuItem("Butik 2");
        menuButtonStoreUpdate.getItems().addAll(menuItemStore1Update, menuItemStore2Update);

        btnUpdateStaff = new Button("Uppdatera");

        vBoxUpdateStaff1 = new VBox();
        vBoxUpdateStaff2 = new VBox();
        vBoxUpdateStaff3 = new VBox();
        vBoxUpdateStaff4 = new VBox();
        hBoxUpdateStaff = new HBox();

        vBoxUpdateStaff1.getChildren().addAll(textFieldUpdateStaffFName, textFieldUpdateStaffLName, textFieldUpdateStaffEmail);
        vBoxUpdateStaff2.getChildren().addAll(updateStaffAddress.getAddressView());
        vBoxUpdateStaff3.getChildren().addAll(textFieldUpdateStaffUserName, textFieldUpdateStaffPassword, menuButtonStoreUpdate);

        vBoxUpdateStaff1.setAlignment(Pos.CENTER);
        vBoxUpdateStaff2.setAlignment(Pos.CENTER);
        vBoxUpdateStaff3.setAlignment(Pos.CENTER);
        vBoxUpdateStaff4.setAlignment(Pos.CENTER);
        hBoxUpdateStaff.setAlignment(Pos.CENTER);

        vBoxUpdateStaff1.setSpacing(10);
        vBoxUpdateStaff2.setSpacing(10);
        vBoxUpdateStaff3.setSpacing(10);
        vBoxUpdateStaff4.setSpacing(10);
        hBoxUpdateStaff.setSpacing(10);

        hBoxUpdateStaff.getChildren().addAll(vBoxUpdateStaff1, vBoxUpdateStaff2, vBoxUpdateStaff3);
        hBoxUpdateStaff.setSpacing(10);
        hBoxUpdateStaff.setAlignment(Pos.CENTER);

        vBoxUpdateStaff4.getChildren().addAll(hBoxUpdateStaff, btnUpdateStaff);

        borderPaneUpdateStaff.setCenter(vBoxUpdateStaff4);


        // SIDA FÖR ATT SE ALL PERSONAL

        labelShowStaff = new Label("Personallista");
        tableViewStaff = new TableView<>();
        tableViewStaff.setMinSize(800,400);
        tableViewStaff.setMaxSize(800,400);
        tableViewStaff.setStyle("-fx-background-color: #F9F7DC;");
        tableViewStaff.getItems().clear();

        labelShowStaff = new Label("Personallista");

        //Delen av tableView som hör till Staff
        columnStaffId = new TableColumn<>("Id");
        columnStaffFirstName= new TableColumn<>("Förnamn");
        columnStaffLastName= new TableColumn<>("Efternamn");
        columnStaffEmail = new TableColumn<>("Email");
        columnStaffActive = new TableColumn<>("Aktiv");
        columnStaffUserName = new TableColumn<>("Användarnamn");
        columnStaffPassword = new TableColumn<>("Lösenord");
        columnStaffLastUpdate = new TableColumn<>("Uppdaterad");

        columnStaffId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStaffId()));
        columnStaffFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        columnStaffLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        columnStaffEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnStaffActive.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getActive()));
        columnStaffUserName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        columnStaffPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        columnStaffLastUpdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));

        //Del av tableView som hör till Address
        columnStaffAddressId = new TableColumn<>("Adressid");
        columnStaffAddress = new TableColumn<>("Adress");
        columnStaffDistrict = new TableColumn<>("Distrikt");
        columnStaffPostalCode= new TableColumn<>("Postnr");
        columnStaffPhone = new TableColumn<>("Telefon");
        columnStaffLastUpdateAddress= new TableColumn<>("Uppdaterad");

        columnStaffAddressId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAddressId()));
        columnStaffAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        columnStaffDistrict.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDistrict()));
        columnStaffPostalCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        columnStaffPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        columnStaffLastUpdateAddress.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));


        //Del av tableView som hör till City
        columnStaffCityId = new TableColumn<>("Ortsid");
        columnStaffCity = new TableColumn<>("Ort");
        columnStaffLastUpdateCity = new TableColumn<>("Uppdaterad");

        columnStaffCityId.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getCityId()));
        columnStaffCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        columnStaffLastUpdateCity.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));

        //Del av TableView som hör till Country
        columnStaffCountryId= new TableColumn<>("Landsid");
        columnStaffCountry = new TableColumn<>("Land");
        columnLastUpdateCountry= new TableColumn<>("Uppdaterad");

        columnStaffCountryId.setCellValueFactory(cellData-> new SimpleObjectProperty<>(cellData.getValue().getCountryId()));
        columnStaffCountry.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountry()));
        columnLastUpdateCountry.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));

        tableViewStaff.getColumns().addAll(  columnStaffId, columnStaffFirstName, columnStaffLastName,columnStaffEmail,
        columnStaffActive, columnStaffUserName, columnStaffPassword, columnStaffLastUpdate, columnStaffAddressId,
        columnStaffAddress, columnStaffDistrict, columnStaffPostalCode, columnStaffPhone, columnStaffLastUpdateAddress, columnStaffCityId,
        columnStaffCity, columnStaffLastUpdateCity, columnStaffCountryId, columnStaffCountry, columnLastUpdateCountry);

        //tableViewStaff.setItems(Staff);
        tableViewStaff.refresh();

        textFieldSearchStaff = new TextField();
        textFieldSearchStaff.setPromptText("Anställningsnummer");
        btnSearchStaff = new Button("Sök personal");

        hBoxShowStaff = new HBox();
        hBoxShowStaff.getChildren().addAll(textFieldSearchStaff, btnSearchStaff);
        hBoxShowStaff.setSpacing(10);
        hBoxShowStaff.setAlignment(Pos.CENTER);

        vBoxShowStaff = new VBox();
        vBoxShowStaff.setAlignment(Pos.CENTER);
        vBoxShowStaff.setSpacing(10);
        vBoxShowStaff.getChildren().addAll(labelShowStaff,tableViewStaff, hBoxShowStaff);

        borderPaneShowStaff.setCenter(vBoxShowStaff);


/*


        vBoxShowCustomers = new VBox();
        vBoxShowCustomers.setAlignment(Pos.CENTER);
        vBoxShowCustomers.setSpacing(10);
        vBoxShowCustomers.getChildren().addAll(labelShowCustomers,tableViewCustomers, hBoxShowCustomers);

        borderPaneShowCustomers.setCenter(vBoxShowCustomers)


 */
        //REGISTRERA KUND-SIDA
        labelDuplicateCustomer = new Label("");
        labelRegNewCustomer = new Label("Registrera ny kund");
        textFieldRegCustomerFName = new TextField();
        textFieldRegCustomerFName.setPromptText("Förnamn");

        textFieldRegCustomerLName = new TextField();
        textFieldRegCustomerLName.setPromptText("Efternamn");

        textFieldRegCustomerEmail = new TextField();
        textFieldRegCustomerEmail.setPromptText("Mailadress");

        RegistryAddress customerAddress = new RegistryAddress();

        btnRegisterNewCustomer = new Button("Registrera kund");
        btnRegisterNewCustomer.setOnAction(e-> {
            CrudOfCustomer crudOfCustomer = new CrudOfCustomer();
            String firstName = textFieldRegCustomerFName.getText();
            String lastName= textFieldRegCustomerLName.getText();
            String email = textFieldRegCustomerEmail.getText();
            String country = customerAddress.textFieldRegCountry.getText();
            String city = customerAddress.textFieldRegCity.getText();
            String address = customerAddress.textFieldRegAddress.getText();
            String district = customerAddress.textFieldRegDistrict.getText();
            String postalCode = customerAddress.textFieldRegPostalCode.getText();
            String phone = customerAddress.textFieldRegPhone.getText();


                 crudOfCustomer.registerNewCustomer  (labelDuplicateCustomer, firstName,  lastName, email, country,  city,
                    address, district, postalCode,  phone);
                });
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

        textFieldSearchCustomer = new TextField();
        textFieldSearchCustomer.setPromptText("Kundnummer");
        btnSearchCustomerNr = new Button("Sök kund");

        btnUpdateCustomer = new Button ("Uppdatera info");
       // btnUpdateCustomer.setOnAction(e-> KOD FÖR ATT UPPDATERA KUNDINFO);
        btnDeleteCustomer = new Button ("Ta bort kund");
     //   btnDeleteCustomer.setOnAction(e-> KOD FÖR ATT DELETA KUND);


        // Lyssnare för att hantera ändringar i knappens tillstånd
        toggleButtonCustomerIsActive.setOnAction(event -> {
            if (toggleButtonCustomerIsActive.isSelected()) {
                toggleButtonCustomerIsActive.setText("Inaktiv");

                // KOD FÖR ATT UPPDATERA OM KUNDEN HAR AVSLUTAT SITT MEDLEMSKAP
            } else {
                toggleButtonCustomerIsActive.setText("Aktiv");
                toggleButtonCustomerIsActive.setStyle("    -fx-background-color: #C57C07;\n" +
                        "    -fx-text-fill: #F9F7DC;\n" +
                        "    -fx-font-size: 12px;\n" +
                        "    -fx-border-width: 2;\n" +
                        "    -fx-border-radius: 3;\n" +
                        "    -fx-background-color: linear-gradient(to bottom, #F69F13, #C57C07);");
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

        vBoxUpdateCustomer4 = new VBox();
        vBoxUpdateCustomer4.getChildren().addAll(textFieldSearchCustomer, btnSearchCustomerNr, toggleButtonCustomerIsActive);
        vBoxUpdateCustomer4.setSpacing(10);
        vBoxUpdateCustomer4.setAlignment(Pos.CENTER);

        hBoxUpdateCustomer1 = new HBox();
        hBoxUpdateCustomer1.setAlignment(Pos.CENTER);
        hBoxUpdateCustomer1.setSpacing(10);
        hBoxUpdateCustomer1.getChildren().addAll(btnUpdateCustomer, btnDeleteCustomer);

        hBoxUpdateCustomer2 = new HBox();
        hBoxUpdateCustomer2.getChildren().addAll(vBoxUpdateCustomer1, vBoxUpdateCustomer2, vBoxUpdateCustomer4);
        hBoxUpdateCustomer2.setAlignment(Pos.CENTER);
        hBoxUpdateCustomer2.setSpacing(10);

        vBoxUpdateCustomer3 = new VBox();
        vBoxUpdateCustomer3.getChildren().addAll(labelUpdateCustomer, hBoxUpdateCustomer2, hBoxUpdateCustomer1);
        vBoxUpdateCustomer3.setSpacing(10);
        vBoxUpdateCustomer3.setAlignment(Pos.CENTER);
        borderPaneUpdateCustomer.setCenter(vBoxUpdateCustomer3);




        // SIDA FÖR ATT SE ALLA KUNDER
        customerList = FXCollections.observableArrayList();
        labelDuplicateCustomer= new Label();


        labelShowCustomers = new Label("Kundöversikt");

        tableViewCustomers = new TableView<>();
        tableViewCustomers.setStyle("-fx-background-color: #F9F7DC;");

      //  List<Customer> customers = crudOfCustomers.loadCustomersFromDatabase(List<Customer>customerList);



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
     /*  columnLastUpdateAddress = new TableColumn<>("Uppdaterad");
       columnCityId = new TableColumn<>("Postortsid");
       columnCity = new TableColumn<>("Postort");
       columnLastUpdateCity = new TableColumn<>("Uppdaterad");
       columnCountryId = new TableColumn<>("Landsid");
       columnCountry = new TableColumn<>("Land");
       columnLastUpdateCountry = new TableColumn<>("Uppdaterad");


       */


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
        /*columnAddressId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAddressId()));


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


 */ tableViewCustomers.getItems().clear();
        tableViewCustomers.getColumns().addAll(columnCustomerId, columnFirstName, columnLastName, columnEmail, columnActive, columnCustomerCreateDate,
                columnLastUpdateCustomer);
/*, columnAddressId, columnAddress, columnDistrict, columnPostalCode, columnPhone, columnLocation,
                columnLastUpdateAddress, columnCityId, columnCity, columnLastUpdateCity, columnCountryId, columnCountry, columnLastUpdateCountry

 */

      /*  tableViewCustomers.getColumns().addAll( columnCustomerId,columnFirstName,columnLastName, columnEmail, columnActive, columnCustomerCreateDate,
        columnLastUpdateCustomer, columnAddressId, columnAddress, columnDistrict, columnPostalCode, columnPhone, columnLocation,
        columnLastUpdateAddress, columnCityId, columnCity, columnLastUpdateCity, columnCountryId, columnCountry, columnLastUpdateCountry);


       */

        crudOfCustomers.loadCustomersFromDatabase(customerList);
        tableViewCustomers.refresh();
        tableViewCustomers.setItems(customerList);

        textFieldSearchCustomer = new TextField();
        textFieldSearchCustomer.setPromptText("Email");
        btnSearchCustomer= new Button("Sök kund");



        btnSearchCustomer.setOnAction(e -> {
            String customerEmail = textFieldSearchCustomer.getText();
            if (isSearchMode) {
                if (!customerEmail.isEmpty()) {
                    crudOfCustomers.readFromCustomer2(customerEmail, customerList);
                } else {
                    reloadAllCustomers(customerList);
                }
            } else {
                reloadAllCustomers(customerList);
                textFieldSearchCustomer.clear(); // Tömmer sökfältet
            }
            tableViewCustomers.refresh();
            tableViewCustomers.setItems(customerList);

            // Toggle button state
            isSearchMode = !isSearchMode;
            updateSearchButton(); // Uppdaterar sökknappen
        });


        hBoxShowCustomers = new HBox();
        hBoxShowCustomers.getChildren().addAll(textFieldSearchCustomer, btnSearchCustomer);
        hBoxShowCustomers.setSpacing(10);
        hBoxShowCustomers.setAlignment(Pos.CENTER);

        vBoxShowCustomers = new VBox();
        vBoxShowCustomers.setAlignment(Pos.CENTER);
        vBoxShowCustomers.setSpacing(10);
        vBoxShowCustomers.getChildren().addAll(labelShowCustomers,tableViewCustomers, hBoxShowCustomers);


        borderPaneShowCustomers.setCenter(vBoxShowCustomers);




// KASSA
        textAreaCheckOut = new TextArea();
        textAreaCheckOut.setMaxSize(390, 270);
        textAreaCheckOut.setMinSize(390, 270);
        textAreaCheckOut.setStyle("-fx-background-color: #F9F7DC;" +
                "-fx-text-fill: #303538;" +
                "-fx-control-inner-background: #F9F7DC;" +
                "-fx-font-size: 10pt");

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
                "        -fx-text-fill: #303538;" +
                "-fx-min-width: 390;" +
                "-fx-max-width: 390;" +
                "-fx-min-height: 40;" +
                "-fx-max-height: 40;");
        textFieldStaffId.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;" +
                "-fx-min-width: 188;" +
                "-fx-max-width: 188;" +
                "-fx-min-height: 40;" +
                "-fx-max-height: 40;");
        textFieldCustomerId.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;" +
                "-fx-min-width: 188;" +
                "-fx-max-width: 188;" +
                "-fx-min-height: 40;" +
                "-fx-max-height: 40;");
        textFieldAmount.setStyle(" -fx-background-color:#F9F7DC ;\n" +
                        "        -fx-text-fill: #303538;" +
                "-fx-min-width: 120;" +
                "-fx-max-width: 120;" +
                "-fx-min-height: 40;" +
                "-fx-max-height: 40;");



        textFieldInventoryId.setMinSize(390, 40);
        textFieldInventoryId.setMaxSize(390, 40);
        textFieldStaffId.setMinSize(188, 40);
        textFieldStaffId.setMaxSize(188, 40);
        textFieldCustomerId.setMinSize(188, 40);
        textFieldCustomerId.setMaxSize(188, 40);
        textFieldAmount.setMinSize(120, 40);
        textFieldAmount.setMaxSize(120, 40);



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

     //   datePickerRentalDate.setStyle("-fx-control-inner-background: #F9F7DC;" + "-fx-text-fill: #303538;");
      //  datePickerReturnDate.setStyle("-fx-control-inner-background: #F9F7DC;" + "-fx-text-fill: #303538;");

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

        vBoxCheckout1 = new VBox();
        vBoxCheckout1.setAlignment(Pos.CENTER);
        vBoxCheckout1.getChildren().addAll(textAreaCheckOut);

        hBoxCheckOutPage = new HBox();
        hBoxCheckOutPage.setAlignment(Pos.CENTER);
        hBoxCheckOutPage.setSpacing(-25);
        hBoxCheckOutPage.getChildren().addAll(vBoxCheckout1, vBoxCheckOut);

        borderPanecheckOut.setCenter(hBoxCheckOutPage);
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

       btnRegStore.setOnAction(e-> {
           Byte managerStaffId = Byte.valueOf(textFieldManagerId.getText());
           String address = addressStore.textFieldRegAddress.getText();
           String postalCode = addressStore.textFieldRegPostalCode.getText();
           String district = addressStore.textFieldRegDistrict.getText();
           String city = addressStore.textFieldRegCity.getText();
           String country =  addressStore.textFieldRegCountry.getText();
           String  phone = addressStore.textFieldRegPhone.getText();
           String location = "test";
           Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
           crudOfStore.registerNewStore(managerStaffId,  address,  district,  city,  postalCode,
                   phone, location,  country,  lastUpdate);
               }
               );

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

        // UPPDATERA BUTIK


        labelUpdateStore = new Label("Uppdatera butiksinformation");


        RegistryAddress addressStoreUpdate = new RegistryAddress();
        textFieldUpdateManagerId = new TextField();
        textFieldUpdateManagerId.setMinSize(140,40);
        textFieldUpdateManagerId.setMaxSize(140,40);
        textFieldUpdateManagerId.setPromptText("Butikschefens id");


        btnFetchStoreInfo = new Button("Se butiksinfo");
        //Hämta butiksinfo från databasen baserat på managerId (unikt för butik)


        btnUpdateStore = new Button("Uppdatera");
        // btnUpdateStore.setOnAction(); Kod för att lägga till ny butik, popup med butiksnr et, lastUpdate








        vBoxUpdateStore1 = new VBox();
        vBoxUpdateStore1.getChildren().addAll(addressStoreUpdate.getAddressView());
        vBoxUpdateStore1.setSpacing(10);
        vBoxUpdateStore1.setAlignment(Pos.CENTER);
        vBoxUpdateStore2 = new VBox();
        vBoxUpdateStore2.getChildren().addAll(textFieldUpdateManagerId, btnFetchStoreInfo, btnUpdateStore);
        vBoxUpdateStore2.setSpacing(10);
        vBoxUpdateStore2.setAlignment(Pos.CENTER);


        hBoxUpdateStore = new HBox();
        hBoxUpdateStore.getChildren().addAll(vBoxUpdateStore1, vBoxUpdateStore2);
        hBoxUpdateStore.setAlignment(Pos.CENTER);
        hBoxUpdateStore.setSpacing(10);
        vBoxUpdateStore3 = new VBox();
        vBoxUpdateStore3.getChildren().addAll(labelUpdateStore, hBoxUpdateStore);
        vBoxUpdateStore3.setAlignment(Pos.CENTER);
        borderPaneUpdateStore.setCenter(vBoxUpdateStore3);



    //FILMSIDAN SÖK OCH SE FILMER

        filmList = FXCollections.observableArrayList();

        tableViewFilms = new TableView<>();
        tableViewFilms.setMinSize(800, 500);
        tableViewFilms.setMaxSize(800, 500);
        tableViewFilms.setStyle("-fx-background-color: #F9F7DC;");

        Label customPlaceholder = new Label(" ");
        tableViewFilms.setPlaceholder(customPlaceholder);

        textFieldSearchFilm = new TextField();
        textFieldSearchFilm.setPromptText("Filmtitel");
        btnSearchFilm = new Button("Sök film");

        columnFilmId = new TableColumn<>("Film-id");
        columnTitle = new TableColumn<>("Filmtitel");
        columnDescription = new TableColumn<>("Handling");
        columnRating = new TableColumn<>("Åldersgräns");
        columnSpecialFeatures = new TableColumn<>("Extra");
        columnReleaseYear = new TableColumn<>("År");
        columnLength = new TableColumn<>("Längd");
        columnRentalDuration = new TableColumn<>("Hyrestid");
        columnRentalRate = new TableColumn<>("Pris");
        columnReplacementCost = new TableColumn<>("Ersättningskostnad");
        columnLastUpdateFilm = new TableColumn<>("Filmer senast uppdaterat");
        columnNameLanguage = new TableColumn<>("Språk");
        columnLastUpdateLanguage = new TableColumn<>("Språk senast uppdaterat");
        columnInventoryId = new TableColumn<>("Lager-id");
        columnLastUpdateInventory = new TableColumn<>("Lager senast uppdaterat");
        columnStoreId = new TableColumn<>("Butiks-id");
        columnFirstNameActor = new TableColumn<>("Skådespelare förnamn");
        columnLastNameActor = new TableColumn<>("Skådespelare efternamn");
        columnLastUpdateActor = new TableColumn<>("Skådespelare senast uppdaterat");
        columnCategory = new TableColumn<>("Kategori");
        columnLastUpdateCategory = new TableColumn<>("Kategori senast uppdaterat");

        columnFilmId.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFilmId()));
        columnTitle.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTitle()));
        columnDescription.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        columnRating.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getRating()));
        columnSpecialFeatures.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getSpecialFeatures()));
        columnReleaseYear.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getReleaseYear()));
        columnLength.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLength()));
        columnRentalDuration.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getRentalDuration()));
        columnRentalRate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getRentalRate()));
        columnReplacementCost.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getReplacementCost()));
        columnLastUpdateFilm.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnNameLanguage.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getName()));
        columnLastUpdateLanguage.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnInventoryId.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getInventoryId()));
        columnLastUpdateInventory.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnStoreIdFilm = new TableColumn<>("Butiks-Id");
      /*  columnStoreIdFilm.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getStoreId()));

       */
        columnFirstNameActor.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFirstName()));
        columnLastNameActor.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastName()));
        columnLastUpdateActor.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));
        columnCategory.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getName()));
        columnLastUpdateCategory.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLastUpdate()));


        tableViewFilms.getItems().clear();
        tableViewFilms.getColumns().addAll(columnStoreIdFilm, columnInventoryId, columnFilmId, columnTitle,
                columnDescription, columnRentalRate, columnReplacementCost, columnRentalDuration, columnFirstNameActor,
                columnLastNameActor, columnCategory, columnNameLanguage, columnRating, columnReleaseYear,
                columnLength, columnSpecialFeatures, columnLastUpdateFilm, columnLastUpdateInventory,
                columnLastUpdateCategory, columnLastUpdateActor, columnLastUpdateLanguage);


        /* //Början på att få upp alla filmer i tableviewn. Kopierat från customer sålänge därav kladdigt. /Helga

        tableViewFilms.refresh();
        tableViewFilms.setItems(filmList);

        btnSearchFilm.setOnAction(e -> {
            String filmTitle = textFieldSearchFilm.getText();
            if (isSearchMode1) {
                if (!filmTitle.isEmpty()) {
                    crudOfCustomers.readFromCustomer2(filmTitle, filmList);
                } else {
                    reloadAllCustomers(filmList);
                }
            } else {
                reloadAllCustomers(filmList);
                textFieldSearchFilm.clear(); // Tömmer sökfältet
            }
            tableViewFilms.refresh();
            tableViewFilms.setItems(filmList);

            // Toggle button state
            isSearchMode1 = !isSearchMode1;
            updateSearchButtonFilms(); // Uppdaterar sökknappen
        });

         */

        hBoxSearchFilm = new HBox();
        hBoxSearchFilm.getChildren().addAll(textFieldSearchFilm, btnSearchFilm);
        hBoxSearchFilm.setAlignment(Pos.CENTER);
        hBoxSearchFilm.setSpacing(10);

        vBoxShowFilms = new VBox();
        vBoxShowFilms.getChildren().addAll(tableViewFilms, hBoxSearchFilm);
        vBoxShowFilms.setAlignment(Pos.CENTER);
        vBoxShowFilms.setSpacing(10);

        borderPaneSearchFilm.setCenter(vBoxShowFilms);



        //REGISTRERA FILM
        labelRegNewFilm = new Label("Registrera ny film");
        labelSpecialFeatures = new Label("Extra innehåll");

        textAreaDescription = new TextArea();
        textAreaDescription.setStyle("-fx-max-height: 90;" +
                "-fx-min-height: 90;" +
                "-fx-max-width: 290;" +
                "-fx-min-width: 290;" +
                "-fx-text-fill: #303538;" +
                "-fx-background-color: #F9F7DC;" +
                "-fx-control-inner-background: #F9F7DC"
        );

        textFieldTitle = new TextField();
        textFieldReleaseYear= new TextField();
        textFieldRentalDuration= new TextField();
        textFieldRentalRate= new TextField();
        textFieldLength= new TextField();
        textFieldReplacementCost= new TextField();
        textFieldLanguage= new TextField();
        textFieldActorFirstName= new TextField();
        textFieldActorLastName= new TextField();

        textFieldTitle.setPromptText("Titel");
        textAreaDescription.setPromptText("Beskrivning");
        textFieldReleaseYear.setPromptText("År");
        textFieldRentalDuration.setPromptText("Hyrestid");
        textFieldRentalRate.setPromptText("Hyreskostnad");
        textFieldLength.setPromptText("Längd");
        textFieldReplacementCost.setPromptText("Ersättningskostnad");
        textFieldLanguage.setPromptText("Språk");
        textFieldActorFirstName.setPromptText("Skådespelare förnamn");
        textFieldActorLastName.setPromptText("Skådespelare efternamn");

        menuButtonStore1 = new MenuButton("Butik");
        menuButtonStore1.setTextFill(Color.rgb(249, 247, 220));
        menuButtonStore1.setStyle("-fx-mark-color: #F9F7DC;");
        menuItemSt1 = new MenuItem("Butik 1");
        menuItemSt2 = new MenuItem("Butik 2");

        menuItemSt1.setOnAction(event -> menuButtonStore1.setText(menuItemSt1.getText()));
        menuItemSt2.setOnAction(event -> menuButtonStore1.setText(menuItemSt2.getText()));

        menuButtonStore1.getItems().addAll(menuItemSt1, menuItemSt2);

        menuButtonRating = new MenuButton("Åldersgräns");
        menuButtonRating.setTextFill(Color.rgb(249, 247, 220));
        menuButtonRating.setStyle("-fx-mark-color: #F9F7DC;");
        menuItemRating1 = new MenuItem("R - Restricted");
        menuItemRating2 = new MenuItem("G - General audiences");
        menuItemRating3 = new MenuItem("PG - Parental Guidance");
        menuItemRating4 = new MenuItem("PG-13 - Parents Strongly Cautioned");
        menuItemRating5 = new MenuItem("NC-17 - No One 17 and Under Admitted");

        menuItemRating1.setOnAction(event -> menuButtonRating.setText(menuItemRating1.getText()));
        menuItemRating2.setOnAction(event -> menuButtonRating.setText(menuItemRating2.getText()));
        menuItemRating3.setOnAction(event -> menuButtonRating.setText(menuItemRating3.getText()));
        menuItemRating4.setOnAction(event -> menuButtonRating.setText(menuItemRating4.getText()));
        menuItemRating5.setOnAction(event -> menuButtonRating.setText(menuItemRating5.getText()));

        menuButtonRating.getItems().addAll(menuItemRating1, menuItemRating2, menuItemRating3, menuItemRating4, menuItemRating5);

        btnRegisterNewFilm = new Button("Registrera");

        checkBoxDeletedScenes = new CheckBox("Bortklippta scener");
        checkboxBehindTheScenes = new CheckBox("Bakom kulisserna");
        checkBoxTrailers = new CheckBox("Trailers");
        checkBoxCommentaries = new CheckBox("Skaparens kommentarer");

        VBox vBoxTest = new VBox();
        vBoxTest.setAlignment(Pos.CENTER);
        vBoxTest.setSpacing(10);
        vBoxTest.getChildren().addAll(textFieldTitle, textFieldReleaseYear);

        vBoxCheckBoxes = new VBox();
        vBoxCheckBoxes.setSpacing(5);
        vBoxCheckBoxes.getChildren().addAll(checkBoxDeletedScenes, checkboxBehindTheScenes,
                checkBoxTrailers, checkBoxCommentaries);

        hBoxCheckBoxes = new HBox();
        hBoxCheckBoxes.setAlignment(Pos.CENTER);
        hBoxCheckBoxes.setSpacing(150);
        hBoxCheckBoxes.getChildren().addAll(vBoxCheckBoxes, btnRegisterNewFilm);

        hBoxRegFilm1 = new HBox();
        hBoxRegFilm2 = new HBox();
        hBoxRegFilm3 = new HBox();
        hBoxRegFilm4 = new HBox();

        hBoxRegFilm1.setAlignment(Pos.CENTER);
        hBoxRegFilm2.setAlignment(Pos.CENTER);
        hBoxRegFilm3.setAlignment(Pos.CENTER);
        hBoxRegFilm4.setAlignment(Pos.CENTER);

        hBoxRegFilm1.setSpacing(10);
        hBoxRegFilm2.setSpacing(10);
        hBoxRegFilm3.setSpacing(10);
        hBoxRegFilm4.setSpacing(10);

        hBoxRegFilm1.getChildren().addAll(vBoxTest, textAreaDescription);
        hBoxRegFilm2.getChildren().addAll(textFieldRentalDuration, textFieldRentalRate, textFieldReplacementCost);
        hBoxRegFilm3.getChildren().addAll(textFieldLength, textFieldLanguage, menuButtonRating);
        hBoxRegFilm4.getChildren().addAll(textFieldActorFirstName, textFieldActorLastName, menuButtonStore1);

        vBoxRegFilm = new VBox();
        vBoxRegFilm.setAlignment(Pos.CENTER);
        vBoxRegFilm.setSpacing(10);
        vBoxRegFilm.getChildren().addAll(labelRegNewFilm, hBoxRegFilm1, hBoxRegFilm2, hBoxRegFilm4, hBoxRegFilm3,
                hBoxCheckBoxes);

        borderPaneRegisterFilm.setCenter(vBoxRegFilm);



/*
        vBoxUpdateStore1 = new VBox();
        vBoxUpdateStore1.getChildren().addAll(addressStoreUpdate.getAddressView());
        vBoxUpdateStore1.setSpacing(10);
        vBoxUpdateStore1.setAlignment(Pos.CENTER);
        vBoxUpdateStore2 = new VBox();
        vBoxUpdateStore2.getChildren().addAll(textFieldUpdateManagerId, btnFetchStoreInfo, btnUpdateStore);
        vBoxUpdateStore2.setSpacing(10);
        vBoxUpdateStore2.setAlignment(Pos.CENTER);

        hBoxUpdateStore = new HBox();
        hBoxUpdateStore.getChildren().addAll(vBoxUpdateStore1, vBoxUpdateStore2);
        hBoxUpdateStore.setAlignment(Pos.CENTER);
        hBoxUpdateStore.setSpacing(10);
        vBoxUpdateStore3 = new VBox();
        vBoxUpdateStore3.getChildren().addAll(labelUpdateStore, hBoxUpdateStore);
        vBoxUpdateStore3.setAlignment(Pos.CENTER);
        borderPaneUpdateStore.setCenter(vBoxUpdateStore3);

 */
    }
    public static void reloadAllCustomers(List<Customer> customerList) {
        CrudOfCustomer.loadCustomersFromDatabase(customerList);
    }
    private void updateSearchButton() {
        if (isSearchMode) {
            btnSearchCustomer.setText("Sök kund");
        } else {
            btnSearchCustomer.setText("Visa hela listan");
        }
    }

    private void updateSearchButtonFilms() { //Kopierat Katarinas och tänker att den kanske kan användas liknande för filmsidan
        if (isSearchMode1) {
            btnSearchFilm.setText("Sök film");
        } else {
            btnSearchFilm.setText("Visa hela listan");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
