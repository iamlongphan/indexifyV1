package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
public class CourseController {
    @FXML
    Button renameConfirmB;
    @FXML
    Button RenameButton;
    @FXML
    Button courseBox;
    @FXML
    Label renameLabel;
    @FXML
    TextField newName;
    @FXML
    TextField setcourseName;
    @FXML
    protected void CreateClick(ActionEvent event)
    {
        Button courseBox2 = new Button("HeyImtheNewCourse");
        courseBox2.setVisible(true);
    }
    @FXML
    protected void DeleteClick(ActionEvent event)
    {
        courseBox.setVisible(false);
    }
    @FXML
    protected void RenameClick(ActionEvent event)
    {
        RenameButton.setVisible(false);
        renameLabel.setVisible(true);
        newName.setVisible(true);
        renameConfirmB.setVisible(true);
    }
    @FXML
    protected void renameConfirm(ActionEvent event)
    {
        renameConfirmB.isDefaultButton();
        renameConfirmB.setVisible(true);
        if(!renameConfirmB.isPressed())
        {
            courseBox.setText(newName.getText());
            renameConfirmB.setVisible(false);
            renameLabel.setVisible(false);
            newName.setVisible(false);
        }
        RenameButton.setVisible(true);
    }
}

