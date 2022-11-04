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
    Button courseBox;
    @FXML
    Label renameLabel;
    @FXML
    TextField newName;
    @FXML
    protected void CreateClick(ActionEvent event)
    {
        courseBox.setVisible(true);
    }
    @FXML
    protected void DeleteClick(ActionEvent event)
    {
        courseBox.setVisible(false);
    }
    @FXML
    protected void RenameClick(ActionEvent event)
    {
        renameLabel.setVisible(true);
        newName.setVisible(true);
        courseBox.setText(newName.getText());
    }
}

