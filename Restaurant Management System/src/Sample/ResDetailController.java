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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import stuffs.restaurant;
import stuffs.food;
import stuffs.Quantity;

public class ResDetailController implements Initializable{

    private restaurant res;

    private Main main;

    private food currFood;

    private Double price=0.0;

    private ArrayList<food> foodList = new ArrayList<>();

    //private ArrayList<Integer> quantity=new ArrayList<>();

     private ArrayList<Quantity> qlist=new ArrayList<>();

    private Integer quan;

    @FXML
    private TableColumn<food,String > columnCat;

    @FXML
    private TableColumn<food,Integer > columnID;

    @FXML
    private TableColumn<food,String > columnName;

    @FXML
    private TableColumn<food,Double > columnPrice;

    @FXML
    private Text resCat;

    @FXML
    private Text resName;

    @FXML
    private Text resPrice;

    @FXML
    private Text resZipcode;

    @FXML
    private Button back;

    @FXML
    private Button addCart;

    @FXML
    private Button placeOrder;

    @FXML
    private TableView<food> table;

    @FXML
    private TextField txtfield;

    public void setMain(Main main)
    {
        this.main=main;
    }

    public void init()
    {
        String str="";
        for(String s:res.getCategory())
        {
            str=str+s+"  ";
        }
        resCat.setText("Category :"+str);
        resName.setText("Restaurant Name: "+res.getName());
        resPrice.setText("Price: "+res.getPrice());
        resZipcode.setText("ZipCode: "+res.getZipcode());

        table.setVisible(true);
        txtfield.setVisible(false);
        //System.out.println(rlist);
        ObservableList<food> list = FXCollections.observableArrayList(res.getFoods());
        columnCat.setCellValueFactory(new PropertyValueFactory<food, String>("category"));
        columnName.setCellValueFactory(new PropertyValueFactory<food, String>("name"));
        columnID.setCellValueFactory(new PropertyValueFactory<food, Integer>("resId"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<food, Double>("price"));

        table.setItems(list);
    }

        public void initialize(URL arg0, ResourceBundle arg1) {

        TableView.TableViewSelectionModel<food> selectionModel = table.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<food>() {

            public void changed(ObservableValue<? extends food> observable, food oldValue,
                    food newValue) {
                if (newValue != null) {
                    // try {
                    //     //main.showResDetails(newValue);
                    // } catch (IOException e) {
                    //     // TODO Auto-generated catch block
                    //     e.printStackTrace();
                    // }
                    // System.out.println("selected");
                    currFood=newValue;
                    txtfield.setVisible(true);
                    txtfield.clear();
                    txtfield.setPromptText("Enter quantity");
                }
            }
        });

    }
    
    @FXML
    public void addCart(ActionEvent e)
    {
        foodList.add(currFood);
        quan=Integer.parseInt(txtfield.getText());
        Quantity q=new Quantity();
        q.setQuantity(quan);
        qlist.add(q);
        price+=currFood.getPrice()*quan;
    }

    @FXML
    public void placeOrder(ActionEvent e) throws IOException
    {
        main.showCartPage(foodList,qlist,res,price);
    }

    public void setRestaurant(restaurant res)
    {
        this.res=res;
    }

    @FXML
    public void Back(ActionEvent e) throws Exception
    {
        main.showSearchResPage();
    }

}
