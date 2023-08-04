package cu.edu.cujae.bd.visual.controller;

import cu.edu.cujae.bd.dto.RolDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddUserController {
    @FXML
    private Button cancelButton;

    @FXML
    private TextField passField;

    @FXML
    private ChoiceBox<RolDto> rolMenu;

    @FXML
    private Button saveButton;

    @FXML
    private TextField userField;
}
