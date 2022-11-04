package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("src/main/resources/application/gradientPictogram.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("src/main/resources/application/padlock.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }


    public void loginButtonAction(ActionEvent event){
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() ==false){
            validateLogin(); // Change to if Statement if/when we change validateLogin to boolean
            Stage stage = (Stage) loginButton.getScene().getWindow();
            CreateClick(event);
            stage.close();
        }
        else{
            loginMessageLabel.setText("Please enter username and password!");

        }

    }
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void CreateClick(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Course Viewer");
            stage.setScene(new Scene(root,1024, 768));
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Change to Boolean for Database i think
    public void validateLogin(){
        System.out.println("Username: "+usernameTextField.getText());
        System.out.println("Password: " + enterPasswordField.getText());

    }

    public void resetPassword(){
        System.out.println("Reset Password");

    }



}