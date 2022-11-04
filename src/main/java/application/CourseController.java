package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
public class CourseController {
    @FXML
    protected void CreateClick(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Main.class.getResource("secondStage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create Course");
            stage.setScene(new Scene(root,450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
