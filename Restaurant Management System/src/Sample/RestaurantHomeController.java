package Sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import stuffs.Message;

public class RestaurantHomeController {

    private Main main;

    private Message message;
    
    @FXML
    private Button Order;

    @FXML
    private Button Back;

    @FXML
    private Button Confirm;

    // @FXML
    // private TableColumn<food, String> columnName;

    // @FXML
    // private TableView<food> table;

    @FXML
    private Text textName;
    
    private String resName;
    
    @FXML
    void order(ActionEvent e) throws IOException
    {
        //main.showFinalizeOrderPage(this.message);
    }
    
    @FXML
    void Back(ActionEvent e) throws Exception
    {
        main.showLoginPage();
    }
    

    void init()
    {
        //table.setVisible(false);
        textName.setText("Restaurant Name: "+resName);
        Order.setVisible(false);
        Confirm.setVisible(false);
    }
    
    void setMain(Main main)
    {
        this.main=main;
    }
    
    public void setMessage()
    {
        message=main.getMessage();
    }

    Message getMessage()
    {
        return this.message;
    }

    void setResName(String resName)
    {
        this.resName=resName;
    }

    void setMessage(Message message)
    {
        this.message=message;
    }

    

}
