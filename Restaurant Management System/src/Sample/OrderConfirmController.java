package Sample;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Message;
import stuffs.food;

public class OrderConfirmController {

    private Main main;

    private Message message;

    private String resName;

    private ArrayList<food> flist;
    
    @FXML
    private Button Back;

    @FXML
    private TableColumn<food, String> columnName;

    @FXML
    private Button finalize;

    @FXML
    private TableView<food> table;


    @FXML
    void Back(ActionEvent e) throws IOException
    {
        main.showRestaurantHomePage(resName);
    }

    @FXML
    void finalise(ActionEvent e)
    {
        table.setVisible(false);
        finalize.setVisible(false);
    }

    void setMessage(Message message)
    {
        this.message=message;
    }

    void setMain(Main main)
    {
        this.main=main;
    }

    void setResName(String resName)
    {
        this.resName=resName;
    }

    void init()
    {
        flist=message.getFoodList();
        table.setVisible(true);
        ObservableList<food> list = FXCollections.observableArrayList(flist);
        columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        table.setItems(list);
    }



}
