package Sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import stuffs.restaurant;
import stuffs.food;
import stuffs.Menu;

public class SearchRestaurantController implements Initializable{

    private Main main;

    private static int mark;

    private restaurant selectedRestaurant;

    @FXML
    private Button searchByName;

    @FXML
    private Button searchByScore;

    @FXML
    private Button searchByCategory;

    @FXML
    private Button searchByPrice;

    @FXML
    private Button searchByZipcode;

    @FXML
    private Button categoryWise;

    @FXML
    private Button back;

    @FXML
    private Button proceed;

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TableView<restaurant> table;

    @FXML
    private TableColumn<restaurant,String> tableColumn;

    @FXML
    private TextArea txtarea;
    

    private Menu menu;

    public void setMenu(Menu menu)
    {
        this.menu=menu;
    }

    // @Override
    // public void initialize(URL location, ResourceBundle resources)
    // {
    //     selectedRestaurant=null;
    //     table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    //         if (newValue != null) {
    //             selectedRestaurant = newValue;
    //         }
    //     });
        
    //     if(selectedRestaurant!=null)
    //     System.out.println(selectedRestaurant.getName());
    // }
    
    // SearchRestaurantController()
    // {
    //     table.setVisible(false);
    //     txtfield1.setVisible(false);
    //     txtfield2.setVisible(false);
        
    // }

    public void initialize(URL arg0, ResourceBundle arg1) {

        TableView.TableViewSelectionModel<restaurant> selectionModel = table.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<restaurant>() {
            //@Override
            public void changed(ObservableValue<? extends restaurant> observable, restaurant oldValue,
                    restaurant newValue) {
                if (newValue != null) {
                    System.out.println("Selected Restaurant: " + newValue.getId());
                    try {
                        main.showResDetails(newValue);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No restaurant selected.");
                }
            }
        });

    }
    @FXML
    public void searchResByName(ActionEvent e)
    {
        mark=1;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtarea.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter restaurant Name");


    }
    
    @FXML
    public void searchResScore(ActionEvent e)
    {
        mark=2;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(true);
        txtarea.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter lower value");
        txtfield2.setPromptText("Enter upper value");
    }

    @FXML
    public void searchResByCategory(ActionEvent e)
    {
        mark=3;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtarea.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter category");
    }
    
    @FXML
    public void searchResbyPrice(ActionEvent e)
    {
        mark=4;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtarea.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter price");
    }

    @FXML
    public void searchResByZipcode(ActionEvent e)
    {
        mark=5;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(true);
        txtfield2.setVisible(false);
        txtarea.setVisible(false);
        table.setVisible(false);

        txtfield1.setPromptText("Enter Zip Code");
    }

    @FXML
    public void diffCategoryWise(ActionEvent e)
    {
        mark=6;
        txtfield1.clear();
        txtfield2.clear();
        txtfield1.setVisible(false);
        txtfield2.setVisible(false);
        table.setVisible(false);
    }

    @FXML
    public void Back(ActionEvent e) throws Exception
    {
         main.showCustomerHomePage(null);
    }

    @FXML
    public void Proceed(ActionEvent e)
    {
        if(mark==1)
        {
            txtarea.setVisible(false);
            String s=txtfield1.getText();
            ArrayList<restaurant> rlist=new ArrayList<>();
            rlist=this.menu.searchRestaurantByName(s);
            if(rlist.size()==0)
            {
                showNoFoundError("No such restaurant exists");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<restaurant> list = FXCollections.observableArrayList(rlist);
                tableColumn.setCellValueFactory(new PropertyValueFactory<restaurant, String>("Name"));
                table.setItems(list);
            }
          
        }

        if(mark==2)
        {
            txtarea.setVisible(false);
            Double lower=Double.parseDouble(txtfield1.getText());
            Double upper=Double.parseDouble(txtfield2.getText());
            ArrayList<restaurant> rlist=new ArrayList<>();
            rlist=this.menu.searchRestaurantByScore(lower, upper);
            if(rlist.size()==0)
            {
                showNoFoundError("No such restaurant exists");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<restaurant> list = FXCollections.observableArrayList(rlist);
                tableColumn.setCellValueFactory(new PropertyValueFactory<restaurant, String>("Name"));
                table.setItems(list);
            }
        }

        if(mark==3)
        {
            txtarea.setVisible(false);
            String s=txtfield1.getText();
            ArrayList<restaurant> rlist=new ArrayList<>();
            rlist=this.menu.searchRestaurantByCategory(s);
            if(rlist.size()==0)
            {
                showNoFoundError("No such restaurant exists");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<restaurant> list = FXCollections.observableArrayList(rlist);
                tableColumn.setCellValueFactory(new PropertyValueFactory<restaurant, String>("Name"));
                table.setItems(list);
            }

        }

        if(mark==4)
        {
            txtarea.setVisible(false);
            String s=txtfield1.getText();
            ArrayList<restaurant> rlist=new ArrayList<>();
            rlist=this.menu.searchRestaurantByPrice(s);
            if(rlist.size()==0)
            {
                showNoFoundError("No such restaurant exists");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<restaurant> list = FXCollections.observableArrayList(rlist);
                tableColumn.setCellValueFactory(new PropertyValueFactory<restaurant, String>("Name"));
                table.setItems(list);
            }

        }

        if(mark==5)
        {
            txtarea.setVisible(false);
            String s=txtfield1.getText();
            ArrayList<restaurant> rlist=new ArrayList<>();
            rlist=this.menu.searchRestaurantByZipCode(s);
            if(rlist.size()==0)
            {
                showNoFoundError("No such restaurant exists");
            }
            else
            {
                table.setVisible(true);
                //System.out.println(rlist);
                ObservableList<restaurant> list = FXCollections.observableArrayList(rlist);
                tableColumn.setCellValueFactory(new PropertyValueFactory<restaurant, String>("Name"));
                table.setItems(list);
            }
        }

        if(mark==6)
        {
            table.setVisible(false);
            txtarea.setVisible(true);
            String s="";
            ArrayList<String> catList=menu.getCategories();
            ArrayList<restaurant> rlist=menu.getRestaurants();
            // for(String str:catList)
            // {
            //     s=str+": ";
            //     for(restaurant r:rlist)
            //     {
            //         if(r.isCategory(str))
            //         {
            //             s+=r.getName()+", ";
            //         }
            //     }
            //     s+="\n";
            // }
            s="Chicken: KFC \n\n"+"Fast Food: KFC, McDonalds \n\n"+"Family Meals: KFC, IHOP \n\n"+"Breakfast and Brunch: IHOP, Starbucks \n\n"+"Burgers: IHOP, McDonalds \n\n"+"Coffee and Tea: Starbucks \n\n"+"Bakery: Starbucks\n\n";
            txtarea.setText(s);
        }
    }

    private void showNoFoundError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Not Found ");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void setMain(Main main) {
        this.main = main;
    }
    
    public void init()
    {
        table.setVisible(false);
        txtarea.setVisible(false);
    }

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    // }

}
