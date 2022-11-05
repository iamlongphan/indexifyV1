package application;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.event.*;
public class CourseController {
    @FXML
    Button addButton;
    @FXML
    Button renameConfirmB;
    @FXML
    Button RenameButton;
    @FXML
    Button courseBox;
    @FXML
    GridPane grid1Pane;
    @FXML
    Label renameLabel;
    @FXML
    Label warningLabel;
    @FXML
    TextField newName;
    @FXML
    Integer indexRow = 0;
    @FXML
    Integer indexCol = 0;
    @FXML
    int counter = 0;

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
    protected void CreateClick(ActionEvent event)
    {
        if(!addButton.isPressed())
        {
            counter++;
        }
        Button courseBox2 = new Button("Course " + counter);
        Button RenameButton2 = new Button("Rename");
        Button DeleteButton2 = new Button("Delete");
        ButtonBar buns = new ButtonBar();
        DeleteButton2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                indexCol = grid1Pane.getRowIndex(buns);
                indexRow = grid1Pane.getColumnIndex(buns);
                DeleteClick(buns);
                warningLabel.setVisible(false);
            }
        });
        buns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        if(getNode(grid1Pane, indexRow, indexCol) == null)
        {
            buns.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            ButtonBar.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2);
            grid1Pane.add(buns, indexRow, indexCol);
            warningLabel.setVisible(false);
        }
        else if(getNodeCount(grid1Pane) >= 7)
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


            buns.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2);
            grid1Pane.add(buns, indexRow, indexCol);
            warningLabel.setVisible(false);
        }
        else
        {
            if(indexRow == 1)
            {
                indexRow = 0;
            }
            else
            {
                indexRow++;
            }
            buns.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2);
            grid1Pane.add(buns, indexRow, indexCol);
            warningLabel.setVisible(false);
        }
    }

    @FXML
    /**
     * Delete function's main method
     */
    protected void DeleteClick(Node node)
    {
        grid1Pane.getChildren().remove(getNode(grid1Pane, grid1Pane.getColumnIndex(node), grid1Pane.getRowIndex(node)));
    }
    @FXML
    /**
     * Rename function's main method
     */
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

