package com.sandstrom;

import javafx.application.Application;
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
import javafx.stage.Stage;

import java.time.LocalDate;

import static com.sandstrom.Methods.login;

public class Main extends Application {
    Scene loginScene, staffScene, customerScene, storeScene, checkOutScene, filmScene,  registerNewStaffScene,
           updateStaffScene, registerNewCustomerScene, updateCustomerScene,  firstPageScene;
    BorderPane borderPaneLogin,borderPaneRegNewCustomer,borderPaneUpdateCustomer, borderPaneStore,
            borderPanecheckOut, borderPaneFilm, borderPaneUpdateStaff,borderPaneRegNewStaff, borderPaneFirstPage;
    MenuBar menuBarLogin, menuBarStaff, menuBarRegisterNewCustomer,menuBarUpdateCustomer, menuBarStore, menuBarCheckOut, menuBarFilm,  menuBarFirstPage;

    Label labelLogin, labelStaffChoice, labelErrorLogin, labelRegNewCustomer, labelUpdateCustomer;
    Button btnLogin,  btnRegisterNewStaff, btnUpdateStaff, btnRent, btnCashPay, btnCardPay;
    TextField textFieldUsername, textFieldPassword, textFieldRegCustomerFName, textFieldRegCustomerLName,textFieldRegCustomerEmail, textFieldUpdateCustomerFName,
    textFieldUpdateCustomerLName, textFieldUpdateCustomerEmail, textFieldInventoryId, textFieldStaffId,
            textFieldCustomerId, textFieldAmount;

    DatePicker datePickerRentalDate, datePickerReturnDate;

    HBox hBoxCheckOutDatePickers, hBoxPayMethod, hBoxId;
    VBox vBoxStaff, vBoxRegCustomer1, vBoxRegCustomer2, vBoxRegCustomer3, vBoxUpdateCustomer1, vBoxUpdateCustomer2,
    vBoxUpdateCustomer3, vBoxCheckOut ;
    HBox hBoxregCustomer, hBoxUpdateCustomer;
    StackPane stackPaneLogin;



    @Override
    public void start(Stage primaryStage) {

        // Sökväg till bildfil
        String imagePath = "C:\\Users\\helga\\IdeaProjects\\WigellVideo1\\0a967b9833ba12e18a75a87fd67a94f90bc8db0c.jpg";


        // Skapar en ImageView och laddar in bilden
        ImageView imageViewVideoStore = new ImageView(new Image("file:" + imagePath));
        imageViewVideoStore.setFitWidth(primaryStage.getWidth());
        imageViewVideoStore.setFitHeight(primaryStage.getHeight());

        imageViewVideoStore.setPreserveRatio(true);


        // BorderPanes för varje scen
        borderPaneLogin = new BorderPane();
        borderPaneRegNewCustomer = new BorderPane();
        borderPaneUpdateCustomer = new BorderPane();
        borderPaneStore = new BorderPane();
        borderPanecheckOut = new BorderPane();
        borderPaneFilm = new BorderPane();
        borderPaneRegNewStaff = new BorderPane();
        borderPaneUpdateStaff = new BorderPane();
        borderPaneFirstPage = new BorderPane();

        //StackPanes
        stackPaneLogin = new StackPane();

        // scener
        loginScene = new Scene(stackPaneLogin, 1000, 700);
        firstPageScene = new Scene (borderPaneFirstPage, 1000, 700);
        registerNewStaffScene = new Scene(borderPaneRegNewStaff, 1000, 700);
        updateStaffScene = new Scene(borderPaneUpdateStaff, 1000, 700);
        registerNewCustomerScene = new Scene(borderPaneRegNewCustomer, 1000, 700);
        updateCustomerScene = new Scene (borderPaneUpdateCustomer, 1000, 700);
        storeScene = new Scene(borderPaneStore, 1000, 700);
        checkOutScene = new Scene(borderPanecheckOut, 1000, 700);
        filmScene = new Scene(borderPaneFilm, 1000, 700);



                // menyer
        menuBarLogin = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarStaff = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarRegisterNewCustomer = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarUpdateCustomer = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarStore = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarCheckOut = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarFilm = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);
        menuBarFirstPage = new MenuBar(primaryStage, registerNewStaffScene,updateStaffScene,
                registerNewCustomerScene,updateCustomerScene, storeScene, checkOutScene, filmScene);

        // Lägg till menyer till varje BorderPane
      //  borderPaneLogin.setTop(menuBarLogin);

        borderPaneRegNewCustomer.setTop(menuBarRegisterNewCustomer);
        borderPaneUpdateCustomer.setTop(menuBarUpdateCustomer);
        borderPaneStore.setTop(menuBarStore);
        borderPanecheckOut.setTop(menuBarCheckOut);
        borderPaneFilm.setTop(menuBarFilm);
        borderPaneFirstPage.setTop(menuBarFirstPage);
        //borderPaneRegNewStaff.setTop()
       // borderPaneUpdateStaff.setTop();


        // Ställ in startscenen
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Wigell Video");
        primaryStage.show();


        //LOGIN-SIDAN

        labelLogin = new Label("Wigell Video");
        labelLogin.setMinSize(300,60);
        labelLogin.setMaxSize(300,60);
        labelLogin.setAlignment(Pos.CENTER);
        labelLogin.setStyle("-fx-font-family: Broadway;"+
                            "-fx-font-size: 40;" +
                            "-fx-background-color: #CE8DA7;" +
                            "-fx-border-color: #792A47;" +
                            "-fx-text-fill: #0C191D;" +
                            "-fx-border-width: 3px");

        borderPaneFirstPage.setStyle("-fx-background-color: #E3CAD3;");


        textFieldUsername = new TextField();
        textFieldUsername.setPromptText("Användarnamn");
        textFieldUsername.setMinSize(150, 30);
        textFieldUsername.setMaxSize(150, 30);
        textFieldUsername.setStyle(
                "-fx-background-color: #E3CAD3;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;");
     //
        textFieldPassword = new TextField();
        textFieldPassword.setPromptText("Lösenord");
        textFieldPassword.setMinSize(150, 30);
        textFieldPassword.setMaxSize(150, 30);
        textFieldPassword.setStyle(
                "-fx-background-color: #E3CAD3;" +
                        "-fx-border-color: #792A47;" +
                        "-fx-text-fill: #0C191D;" +
                        "-fx-font-size: 10pt;");
        //String passWord = textFieldPassword.getText();

        labelErrorLogin = new Label(" ");
        labelErrorLogin.setStyle("-fx-text-fill: #0C191D;");
        btnLogin = new Button("Logga in");
        btnLogin.setMinSize(150, 30);
        btnLogin.setMaxSize(150, 30);
        btnLogin.setStyle(
                "-fx-background-color: #E3CAD3;" +
                "-fx-border-color: #792A47;" +
                "-fx-text-fill: #0C191D;" +
                "-fx-font-size: 10pt;");

        btnLogin.setOnAction(e -> {
            String userName = textFieldUsername.getText();
            String passWord = textFieldPassword.getText();
            if (login(userName, passWord)) {
                primaryStage.setScene(firstPageScene);
            } else {
              //  primaryStage.setScene(firstPageScene);
                labelErrorLogin.setText("Fel inloggningsuppgifter. Försök igen.");
            }
        });

        //LOGGA IN GENOM ATT TRYCKA ENTER
        textFieldPassword.setOnKeyPressed(e -> {
            String userName = textFieldUsername.getText();
            String passWord = textFieldPassword.getText();
            if (e.getCode().equals(KeyCode.ENTER)) {
              //  e.consume();
                if (login(userName, passWord)) {
                    primaryStage.setScene(firstPageScene);
                } else {
                    labelErrorLogin.setText("Fel inloggningsuppgifter. Försök igen.");
                }
            }
        });


        VBox vBoxLogin1 = new VBox();
        vBoxLogin1.getChildren().add(imageViewVideoStore);
        vBoxLogin1.setAlignment(Pos.CENTER);
        borderPaneLogin.setCenter(vBoxLogin1);
        borderPaneLogin.setStyle("-fx-background-color: #BDF1FE");

        VBox vBoxLogin2 = new VBox();
        vBoxLogin2.getChildren().addAll(labelLogin, textFieldUsername, textFieldPassword, btnLogin, labelErrorLogin);
        vBoxLogin2.setAlignment(Pos.CENTER);
        vBoxLogin2.setSpacing(10);


        stackPaneLogin.getChildren().addAll(borderPaneLogin, vBoxLogin2);


        //PERSONAL-SIDA


        labelStaffChoice = new Label("Wigell Video: Personalsida");
        labelStaffChoice.setMinSize(150,30);
        labelStaffChoice.setMaxSize(150,30);
        labelStaffChoice.setAlignment(Pos.CENTER);
        labelStaffChoice.setStyle("-fx-font-family: Broadway;"+
                "-fx-font-size: 21;" +
                "-fx-text-fill: BLACK;"+
                "-fx-background-color: #FE8D01;"+
                "-fx-border-color: #AA327C;" +
                "-fx-border-width: 3px");

        btnRegisterNewStaff = new Button("Registrera ny personal");
        btnRegisterNewStaff.setMinSize(150, 30);
        btnRegisterNewStaff.setMaxSize(150, 30);

        btnUpdateStaff = new Button("Uppdatera befintlig personal");
        btnUpdateStaff.setMinSize(150, 30);
        btnUpdateStaff.setMaxSize(150, 30);

        vBoxStaff = new VBox();
        vBoxStaff.setSpacing(10);
        vBoxStaff.getChildren().addAll(labelStaffChoice, btnRegisterNewStaff, btnUpdateStaff);

    //REGISTRERA KUND-SIDA

        labelRegNewCustomer = new Label("Registrera ny kund");
        textFieldRegCustomerFName = new TextField();
        textFieldRegCustomerFName.setPromptText("Förnamn");

        textFieldRegCustomerLName = new TextField();
        textFieldRegCustomerLName.setPromptText("Efternamn");

        textFieldRegCustomerEmail = new TextField();
        textFieldRegCustomerEmail.setPromptText("Mailadress");
        RegistryAddress customerAddress = new RegistryAddress();


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
        vBoxRegCustomer3.getChildren().addAll(labelRegNewCustomer, hBoxregCustomer);
        vBoxRegCustomer3.setSpacing(10);
        vBoxRegCustomer3.setAlignment(Pos.CENTER);
        borderPaneRegNewCustomer.setCenter(vBoxRegCustomer3);


        //UPPDATERA BEFINTLIG KUND

       /* (customer_id - i uppdatera, men även dyka upp nånstans efter att en kund blivit reggad)
        first_name
                last_name
        email
        address_id - auto - skapas när kund reggas. Är sen kopplat till den kunden. ju.
        (active - slidebox - ska va default active när kund registreras - i uppdatera)
        create_date - autogenererar dagens datum (om det går att fixa :)))
        (last_update - klockslag auto lägg under setOnAction)

        Adress:
        Lösa på liknande sätt som menuBar?!
                address_id - auto - ingen ruta osv
        address - gata och gatunummer
        district - delstat?! typ län?!
                city_id - auto
        postal_code
                phone
        location ?? Kolla med databasgänget
                (last_update - klockslag auto lägg under setOnAction)*/
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

        // Skapa en etikett för att visa den aktuella aktivitetsstatusen
        Label statusLabel = new Label("Kunden är aktiv");
        Boolean buttonIsActive = true;


        // Lyssnare för att hantera ändringar i knappens tillstånd
        toggleButtonCustomerIsActive.setOnAction(event -> {
            if (toggleButtonCustomerIsActive.isSelected()) {
                toggleButtonCustomerIsActive.setText("Inaktiv");
                statusLabel.setText("Kunden är inaktiv");
                // Här kan du lägga till kod för att ändra kundens status i din databas
            } else {
                toggleButtonCustomerIsActive.setText("Aktiv");
                statusLabel.setText("Kunden är aktiv");
                // Här kan du lägga till kod för att ändra kundens status i din databas
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
        vBoxUpdateCustomer3.getChildren().addAll(labelUpdateCustomer, hBoxUpdateCustomer,statusLabel, toggleButtonCustomerIsActive);
        vBoxUpdateCustomer3.setSpacing(10);
        vBoxUpdateCustomer3.setAlignment(Pos.CENTER);
        borderPaneUpdateCustomer.setCenter(vBoxUpdateCustomer3);


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


    }

    public static void main(String[] args) {
        launch(args);
    }
}
