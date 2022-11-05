package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController  {

    @FXML
    private Button cancelButton;
    @FXML
    public Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    public ImageView brandingImageView;
    @FXML
    public ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button logoutButton;
    @FXML
    public String usernameSaved;

    public void loginButtonAction(ActionEvent event)
    {
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false)
        {
            validateLogin(); // Change to if Statement if/when we change validateLogin to boolean
            Stage stage = (Stage) loginButton.getScene().getWindow();
            loginSuccess(event);
            stage.close();
        } else {
            loginMessageLabel.setText("Please enter username and password!");

        }
    }
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void loginSuccess(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Main.class.getResource("courseViewer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Course Viewer");
            stage.setScene(new Scene(root,1024, 768));
            stage.setResizable(false);
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Change to Boolean for Database i think
    public void validateLogin()
    {
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + enterPasswordField.getText());
    }
    public void resetPassword()
    {
        System.out.println("Reset Password");
    }
    protected void signUp()
    {

    }
    public void logoutButton()
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Main.class.getResource("login.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle("Indexify");
            stage.setScene(new Scene(root, 530, 400));
            stage.setResizable(false);
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}