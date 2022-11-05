package application;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("resetPassword.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Reset password");
            stage.setScene(new Scene(root, 530, 400));
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    protected void signUp()
    {
        Stage stage = new Stage();
        stage.setTitle("Sign Up");

        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 300);

        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();


        Label passwordLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();

        Label securityQuestion = new Label("Security Question: What city were you born in? ");
        TextField question = new TextField();
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String qAns = question.getText();
                System.out.println(username + " " + password + " " + qAns);

                stage.close();


            }
        });

        HBox userBox = new HBox();
        userBox.getChildren().addAll(usernameLabel, usernameField);
        userBox.setPadding(new Insets(10, 10, 10, 10));

        HBox passBox = new HBox();
        passBox.getChildren().addAll(passwordLabel, passwordField);
        passBox.setPadding(new Insets(10, 10, 10, 10));

        HBox quesBox = new HBox();
        quesBox.getChildren().addAll(securityQuestion, question);
        quesBox.setPadding(new Insets(10, 10, 10, 10));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(userBox, passBox, quesBox, submit);

        root.getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
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
    @FXML
    public void resetCancel() throws IOException
    {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 530, 400);
            stage.setTitle("Indexify");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
    }

}