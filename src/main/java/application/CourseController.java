package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
public class CourseController {
    @FXML
    Button addButton;
    @FXML
    Button renameConfirmB;
    @FXML
    Button RenameButton;
    @FXML
    Button DeleteButton;
    @FXML
    Button courseBox;
    @FXML
    GridPane grid1Pane;
    @FXML
    Label renameLabel;
    @FXML
    TextField newName;
    @FXML
    int indexRow = 0;
    @FXML
    int indexCol = 0;
    @FXML
    int counter = 0;
    public Node getNode(GridPane gridPane1, Integer col, Integer row)
    {
        for (Node node : gridPane1.getChildren())
        {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
            else
            {
                return null;
            }
        }
        return null;
    }
    @FXML
    protected void CreateClick()
    {
        if(!addButton.isPressed())
        {
            counter++;
        }
        Button courseBox2 = new Button("Course " + counter);
        if(getNode(grid1Pane, indexRow, indexCol) == null)
        {
            grid1Pane.add(courseBox2, indexRow, indexCol);
            grid1Pane.add(RenameButton, indexRow, indexCol);
            grid1Pane.add(DeleteButton, indexRow, indexCol);
        }
        else if(indexRow == 2)
        {
            indexRow = 0;
            indexCol++;
            grid1Pane.add(courseBox2, indexRow, indexCol);

        }
        else
        {
            indexRow++;
            grid1Pane.add(courseBox2, indexRow, indexCol);
        }
    }
    @FXML
    protected void DeleteClick(ActionEvent event)
    {
        courseBox.setVisible(false);

    }
    @FXML
    protected void RenameClick(ActionEvent event)
    {

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

