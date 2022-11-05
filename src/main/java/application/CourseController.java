package application;

import javafx.collections.ObservableList;
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
    public Node getNodeByRowColumnIndex(Integer row,Integer column,GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
    @FXML
    /**
     * Method for the creation of courses, additionaly creates the buttons that provide deleting,
     * and renaming.
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
            System.out.println(indexCol);
            System.out.println(indexRow);
        }
        else if(getNodeCount(grid1Pane) >= 7)
        {
            warningLabel.setVisible(true);
            System.out.println(indexCol);
            System.out.println(indexRow);
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

            System.out.println(indexCol);
            System.out.println(indexRow);
            buns.setButtonData(courseBox2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(RenameButton2, ButtonBar.ButtonData.LEFT);
            buns.setButtonData(DeleteButton2, ButtonBar.ButtonData.LEFT);
            buns.getButtons().addAll(courseBox2, RenameButton2, DeleteButton2);
            grid1Pane.add(buns, indexRow, indexCol);
            warningLabel.setVisible(false);
        }
        else
        {
            System.out.println(indexCol);
            System.out.println(indexRow);
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
    protected void DeleteClick(Node node)
    {
        grid1Pane.getChildren().remove(getNodeByRowColumnIndex(grid1Pane.getRowIndex(node), grid1Pane.getColumnIndex(node), grid1Pane));
    }
    @FXML
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

