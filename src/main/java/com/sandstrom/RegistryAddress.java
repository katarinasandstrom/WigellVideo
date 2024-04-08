package com.sandstrom;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegistryAddress extends VBox {
    TextField textFieldRegAddress, textFieldRegDistrict, textFieldRegPostalCode, textFieldRegPhone,
            textFieldRegCity, textFieldRegCountry;

    public RegistryAddress() {



        textFieldRegAddress = new TextField();
        textFieldRegAddress.setPromptText("Gatuadress");

        textFieldRegDistrict = new TextField();
        textFieldRegDistrict.setPromptText("Distrikt");

        textFieldRegPostalCode = new TextField();
        textFieldRegPostalCode.setPromptText("Postnummer");

        textFieldRegPhone = new TextField();
        textFieldRegPhone.setPromptText("Telefonnummer");

        textFieldRegCity = new TextField();
        textFieldRegCity.setPromptText("Postort");

        textFieldRegCountry = new TextField();
        textFieldRegCountry.setPromptText("Land");

        VBox vBoxRegAddress1 = new VBox();
        vBoxRegAddress1.getChildren().addAll(textFieldRegAddress, textFieldRegCity, textFieldRegCountry);
        vBoxRegAddress1.setAlignment(Pos.CENTER);
        vBoxRegAddress1.setSpacing(10);

        VBox vboxRegAddress2 = new VBox();
        vboxRegAddress2.getChildren().addAll(textFieldRegPostalCode, textFieldRegDistrict, textFieldRegPhone);
        vboxRegAddress2.setAlignment(Pos.CENTER);
        vboxRegAddress2.setSpacing(10);

        HBox hBoxRegAddress = new HBox();
        hBoxRegAddress.getChildren().addAll(vBoxRegAddress1, vboxRegAddress2);
        hBoxRegAddress.setAlignment(Pos.CENTER);
        hBoxRegAddress.setSpacing(10);


        getChildren().add(hBoxRegAddress); // Lägg till hbox i vbox
    }
    public VBox getAddressView() {
        return this;
    }
}
