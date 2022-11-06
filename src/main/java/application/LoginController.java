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
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.*;

public class LoginController {

    @FXML
    private Button reCancel2;
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
    private Button resetPassword;
    @FXML
    private Button logoutButton;
    @FXML
    public String usernameSaved;
    @FXML
    public String passwordSaved;
    @FXML
    public TextField usernameReset;
    @FXML
    public TextField securityAnswer;
    @FXML
    public TextField brandNewPass;
    @FXML
    public Button reConfirm;
    @FXML
    public Label securityWRONG;
    @FXML
    public Label UserWRONG;

    /**
     * Action function for the login button.  Allows the button to run the validate functions and login success functions.
     * @param event
     * @throws FileNotFoundException
     */
    public void loginButtonAction(ActionEvent event) throws FileNotFoundException {
        if (usernameTextField.getText().isBlank() != false && enterPasswordField.getText().isBlank() != false) {
            loginMessageLabel.setText("Please enter username and password!");
        } else {
            if (validateLogin()) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                loginSuccess(event);
                usernameSaved = usernameTextField.getText();
                stage.close();
            } else {
                loginMessageLabel.setText("Username or Password does not exist.");
            }
        }
    }

    /**
     * Cancels stage/closes windows.
     * @param event
     */
    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Checks for the success of the login and also brings open the courses if it succeeds.
     * @param event
     */
    @FXML
    protected void loginSuccess(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("courseViewer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Course Viewer");
            stage.setScene(new Scene(root, 1024, 768));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes sure that the account exists
     * @return Boolean: If account exists then true, if not then false.
     * @throws FileNotFoundException
     */
    public boolean validateLogin() throws FileNotFoundException {
        File database = new File("users.TXT");
        Scanner readDatabase = new Scanner(database);

        while(readDatabase.hasNext())
        {
            usernameSaved = readDatabase.findInLine(usernameTextField.getText());
            passwordSaved = readDatabase.findInLine(enterPasswordField.getText());
            if (usernameTextField.getText().equals(usernameSaved) && enterPasswordField.getText().equals(passwordSaved))
            {
                return true;
            }
            else {
                readDatabase.nextLine();
            }
        }
        return false;
    }

    /**
     * Resets the password of an existing account
     */
    public void resetPassword()
    {


        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("resetPassword.fxml"));
            Stage stage2 = (Stage) resetPassword.getScene().getWindow();
            stage2.close();
            Stage stage = new Stage();
            stage.setTitle("Reset password");
            stage.setScene(new Scene(root, 650, 400));
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Performs the sign-up function which is currently saving into flat files.
     * @throws IOException
     */
    @FXML
    protected void signUp() throws IOException {
        FileWriter myWriter = new FileWriter("users.TXT", true);
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
                try {
                    myWriter.write(username + ", ");
                    myWriter.write(password + ", ");
                    myWriter.write(qAns + ",");
                    myWriter.write("\n");
                    myWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
    public void resetCancel() throws IOException {
            Stage stage2 = (Stage) reCancel2.getScene().getWindow();
            stage2.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 530, 400);
            stage.setTitle("Indexify");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
    }

    /**
     * Is the main function for the reset password button, writes in the new password into the file and replaces the old one.
     * @throws IOException
     */
    public void resetConfirm() throws IOException {

        removeLineFromFile();
        String username = usernameReset.getText();
        String password = brandNewPass.getText();
        String qAns = securityAnswer.getText();
        BufferedWriter myWriter = new BufferedWriter(new FileWriter("users.TXT"));
        try {
            myWriter.write(username + ", ");
            myWriter.write(password + ", ");
            myWriter.write(qAns + ",");
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(username + " " + password + " " + qAns);


        Stage stage2 = (Stage) reCancel2.getScene().getWindow();
        stage2.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 530, 400);
        stage.setTitle("Indexify");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Is used in order to delete data from the flat file so that new data can be written in its place
     */
    public void removeLineFromFile() {

        try {

            File inFile = new File("users.TXT");

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader("users.TXT"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (line.trim().indexOf(usernameReset.getText()) == -1) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}