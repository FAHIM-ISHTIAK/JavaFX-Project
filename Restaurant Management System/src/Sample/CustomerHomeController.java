package Sample;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerHomeController {

    private Main main;
    
    @FXML
    private Button searchFood;

    @FXML
    private Button searchRes;

    @FXML
    private Button back;

    @FXML
    public void searchRestaurant(ActionEvent e) throws Exception
    {
        main.showSearchResPage();
    }
    
    @FXML
    public void searchFoods(ActionEvent e) throws Exception
    {
        main.showSearchFoodPage();
    }

    @FXML
    public void Back(ActionEvent e) throws Exception
    {
        main.showLoginPage();
    }

    void setMain(Main main) {
        this.main = main;
    }


}
