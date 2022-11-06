package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.NodeOrientation;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.event.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CourseController extends LoginController {
    @FXML
    Button addButton;
    @FXML
    Button renameConfirmB;
    @FXML
    Button RenameButton;
    @FXML
    Label renameLabel;
    @FXML
    Button courseBox;
    @FXML
    GridPane grid1Pane;
    @FXML
    Label warningLabel;
    @FXML
    TextField newName;
    @FXML
    TextField textField1;
    @FXML
    Integer indexRow = 0;
    @FXML
    Integer indexCol = 0;
    @FXML
    int counter = 0;
    @FXML
    Button logoutButton;




    /**
     * This class is used in order to grab nodes from the gridPane to make deletion possible, as GridPane doesn't have a delete method
     * inherently.
     * @param gridPane1 Any GridPane object
     * @param col The column that is iterated by Integer indexCol
     * @param row The row that is iterated by Integer indexRow
     * @return Returns the node(in this case a button/button bar) of the particular cell of GridPane
     */

    public Node getNode(GridPane gridPane1, Integer col, Integer row)
    {
        for (Node node : gridPane1.getChildren())
        {
            if (gridPane1.getColumnIndex(node) == col && gridPane1.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * This getter is used mainly to figure out the total number of nodes that are filled in the GridPane,
     * the reason for this is because we have a max limit on the number of courses.
     * @param gridPane1 Any GridPane object
     * @return Number of objects in GridPane
     */
    public int getNodeCount(GridPane gridPane1)
    {
        int count = 0;
        for(Node node: gridPane1.getChildren())
        {
            if(getNode(gridPane1, gridPane1.getColumnIndex(node), gridPane1.getRowIndex(node)) != null)
            {
                count++;
            }
        }
        return count;
    }
    @FXML
    /**
     * Method for the creation of courses, additionaly creates the buttons that provide deleting,
     * and renaming.  This method also gives functionality to the delete button and rename buttons.
     */
    protected void CreateClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        File courses = new File("userCourses.TXT");
        BufferedWriter myWriter = new BufferedWriter(new FileWriter("userCourses.TXT"));
        if(!addButton.isPressed())
        {
            counter++;
        }
        Button courseBox2 = new Button("Course " + counter);
        Button RenameButton2 = new Button("Rename");
        Button DeleteButton2 = new Button("Delete");
        TextField newName2 = new TextField("Enter new course name");
        Button ConfirmButton = new Button("Confirm");
        ButtonBar buns = new ButtonBar();
        DeleteButton2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                indexCol = grid1Pane.getRowIndex(buns);
                indexRow = grid1Pane.getColumnIndex(buns);
                DeleteClick(buns);
                DeleteClick(newName2);
                warningLabel.setVisible(false);
            }
        });
        RenameButton2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                ConfirmButton.setVisible(true);
                newName2.setVisible(true);
            }
        });
        ConfirmButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                courseBox2.setText(newName2.getText());
                newName2.setVisible(false);
                ConfirmButton.setVisible(false);
            }
        });
        buns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        if(getNode(grid1Pane, indexRow, indexCol) == null)
        {
            ButtonBar.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(ConfirmButton, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2, ConfirmButton);
            grid1Pane.add(buns, indexRow, indexCol);
            grid1Pane.add(newName2, indexRow, indexCol);
            grid1Pane.setHalignment(newName2, HPos.CENTER);
            grid1Pane.setValignment(newName2, VPos.TOP);
            myWriter.write(courseBox2.getText());
            myWriter.write("\n");
            ConfirmButton.setVisible(false);
            newName2.setVisible(false);
            warningLabel.setVisible(false);
        }
        else if(getNodeCount(grid1Pane) >= 12)
        {
            warningLabel.setVisible(true);

        }
        else if(getNode(grid1Pane, indexRow, indexCol) != null && counter%2 != 0)
        {
            indexRow = 0;
            if(indexCol == 2)
            {
                indexCol = 0;
            }
            else
            {
                indexCol++;
            }
            ButtonBar.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(ConfirmButton, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2, ConfirmButton);
            grid1Pane.add(buns, indexRow, indexCol);
            grid1Pane.add(newName2, indexRow, indexCol);
            grid1Pane.setHalignment(newName2, HPos.CENTER);
            grid1Pane.setValignment(newName2, VPos.TOP);
            myWriter.write(courseBox2.getText());
            myWriter.write("\n");
            ConfirmButton.setVisible(false);
            newName2.setVisible(false);
            warningLabel.setVisible(false);
        }
        else
        {

            indexRow++;
            ButtonBar.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(ConfirmButton, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2, ConfirmButton);
            grid1Pane.add(buns, indexRow, indexCol);
            grid1Pane.add(newName2, indexRow, indexCol);
            grid1Pane.setHalignment(newName2, HPos.CENTER);
            grid1Pane.setValignment(newName2, VPos.TOP);
            myWriter.write(String.valueOf(courseBox2));
            myWriter.write("\n");
            ConfirmButton.setVisible(false);
            newName2.setVisible(false);
            warningLabel.setVisible(false);
        }
    }

    @FXML
    /**
     * Delete function's main method
     * ******DISCLAIMER******
     * DELETE ONLY ONE COURSE AT A TIME, DELETING MULTIPLE COURSES BREAKS THE PROGRAM AT THIS TIME
     */
    protected void DeleteClick(Node node)
    {
        grid1Pane.getChildren().remove(getNode(grid1Pane, grid1Pane.getColumnIndex(node), grid1Pane.getRowIndex(node)));
    }
    @FXML
    public void logoutButton() {
        Parent root;
        try
        {
            usernameSaved = "";
            passwordSaved = "";
            root = FXMLLoader.load(Main.class.getResource("login.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle("Indexify");
            stage.setScene(new Scene(root,530, 400));
            stage.setResizable(false);
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}

