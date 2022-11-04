package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
public class CourseController {
    private TextField courseName;
    @FXML
    protected void CreateClick(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Main.class.getResource("courseCreation.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create Course");
            stage.setScene(new Scene(root,320, 300));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void courseCreate(ActionEvent event)
    {
        courseName.getText();
    }
}
