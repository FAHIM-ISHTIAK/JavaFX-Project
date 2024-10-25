package Sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import stuffs.Menu;
import stuffs.food;
import stuffs.restaurant;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class searchFoodController{

    
    private Main main;

    // private restaurant selectedRestaurant;
    
    private static int mark;
    @FXML
    private Button back;

    @FXML
    private Button confirm;

    @FXML
    private Button searchByName;

    @FXML
    private Button searchInRes;

    @FXML
    private Button searchByCat;

    @FXML
    private Button searchCatInRes;

    @FXML
    private Button searchByPrice;

    @FXML
    private Button searchByPriceInRes;

    @FXML
    private Button costliestFood;

    @FXML
    private Button ResList;

    @FXML
    private TableView<food> table;

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TextField txtfield3;

    @FXML
    private TableColumn<food, String> columnCat;

    @FXML
    private TableColumn<food, String> columnName;

    @FXML
    private TableColumn<food, Double> columnPrice;

    @FXML
    private TableColumn<food, Integer> columnID;

    private Menu menu;

    
    public void setMenu(Menu menu)
    {
        this.menu=menu;
    }

    // public void initialize()
    // {
    //      selectedRestaurant=null;
    // }
    
    @FXML
    void searchByName(ActionEvent event)
    {
        mark=1;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Food Name");
    }

    @FXML
    void searchInRestaurant(ActionEvent event)
    {
        mark=2;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(true);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Food Name");
        txtfield2.setPromptText("Enter restaurant Name");
    }

    @FXML
    void searchByCategory(ActionEvent event)
    {
        mark=3;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Category ");
    }

    
    @FXML
    void searchByCatInRes(ActionEvent event) 
    {
        mark=4;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(true);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Category Name");
        txtfield2.setPromptText("Enter Restaurant Name");
    }

    @FXML
    void searchByPrice(ActionEvent event)
    {
        mark=5;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(true);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter lower value");
        txtfield2.setPromptText("Enter upper value");
    }

    @FXML
    void searchBypriceInRes(ActionEvent event) 
    {
        mark=6;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(true);
        txtfield3.setVisible(true);
        table.setVisible(false);

        txtfield1.setPromptText("Enter lower value");
        txtfield2.setPromptText("Enter upper value");
        txtfield3.setPromptText("Enter Restaurant Name");
    }

    @FXML
    void costly(ActionEvent event)
    {
        mark=7;
        txtfield1.clear();
        txtfield2.clear();
        txtfield3.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtfield3.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Restaurant Name");
    }

    @FXML
    void listOfres(ActionEvent event)
    {
        mark=8;
        txtfield1.setVisible(false);
        txtfield2.setVisible(false);
        txtfield3.setVisible(false);
        table.setVisible(false);
    }

    @FXML 
    void Back(ActionEvent event)
    {
        try {
            main.showCustomerHomePage(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void Confirm(ActionEvent event)
    {
        if(mark==1)
        {
            String s=txtfield1.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByName(s);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==2)
        {
            String foodName=txtfield1.getText();
            String resName=txtfield2.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByNameInRestaurant(resName, foodName);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==3)
        {
            String category=txtfield1.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByCategory(category);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==4)
        {
            String category=txtfield1.getText();
            String resName=txtfield2.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByCategoryInRestaurant(category, resName);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==5)
        {
            Double lower=Double.parseDouble(txtfield1.getText());
            Double upper=Double.parseDouble(txtfield2.getText());
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByPrice(lower,upper);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==6)
        {
            Double lower=Double.parseDouble(txtfield1.getText());
            Double upper=Double.parseDouble(txtfield2.getText());
            String resName=txtfield3.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchFoodByPriceInRestaurant(lower, upper, resName);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }

        if(mark==7)
        {
            String resName=txtfield1.getText();
            ArrayList<food> flist=new ArrayList<>();
            flist=this.menu.searchCostliestFoodInRestaurant(resName);
            if(flist.size()==0)
            {
                showNoFoundError("Ops!! No Food Found");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<food> list = FXCollections.observableArrayList(flist);
                columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
                columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));
                columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
                table.setItems(list);
            }
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    private void showNoFoundError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Not Found ");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void init()
    {
        table.setVisible(false);
        ResList.setVisible(false);
    }

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    // }

}
