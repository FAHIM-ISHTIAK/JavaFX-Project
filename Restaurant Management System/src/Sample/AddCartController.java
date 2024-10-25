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
import javafx.scene.text.Text;
import stuffs.Message;
import stuffs.Quantity;
import stuffs.food;
import stuffs.restaurant;

public class AddCartController {

    private Main main;

    private ArrayList<food> flist;

    private ArrayList<Quantity> qlist;

    //private ArrayList<Integer> quantity;

    private Double price;

    private String resName;

    private restaurant res;
    
    @FXML
    private Button Back;

    @FXML
    private Button Confirm;

    @FXML
    private Text ConfirmText;

    @FXML
    private Text PriceText;

    @FXML
    private TableView<food> table;

      @FXML
    private TableView<Quantity> table2;

    @FXML
    private TableColumn<food, String> columnName;

     @FXML
    private TableColumn<food, Double> columnPrice;

    @FXML
    private TableColumn<Quantity,Integer> columnQuantity;

  

    void setQuanList(ArrayList<Quantity> qlist)
    {
        this.qlist=qlist;
    }

    void setFoodList(ArrayList<food> flist)
    {
        this.flist=flist;
    }

    void setPrice(Double price)
    {
        this.price=price;
    }

    void setResName(String resName)
    {
        this.resName=resName;
    }

    void setRestaurant(restaurant res)
    {
        this.res=res;
    }

    void init()
    {
        PriceText.setText("Total Price: "+this.price);
        ConfirmText.setVisible(false);
        table.setVisible(true);
        ObservableList<food> list = FXCollections.observableArrayList(flist);
        ObservableList<Quantity> list2 = FXCollections.observableArrayList(qlist);
        columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Quantity, Integer>("quantity"));
        table.setItems(list);
        table2.setItems(list2);
    }

    @FXML
    void Confirm(ActionEvent event) throws IOException
    {
        PriceText.setVisible(false);
        ConfirmText.setVisible(true);
        ConfirmText.setText("Order placed Successfully");
        table.setVisible(false);
        table2.setVisible(false);
        Message message = new Message(resName,flist,price);
        main.getSocketWrapper().write(message);
        
        //main.getSocketWrapper().write;
    }

    @FXML
    void Back(ActionEvent event) throws IOException
    {
        main.showResDetails(res);
    }

    void setMain(Main main)
    {
        this.main=main;
    }

    

}
