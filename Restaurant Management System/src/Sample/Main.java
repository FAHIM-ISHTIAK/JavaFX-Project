package Sample;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.SocketWrapper;
import util.LoginDTO;
import stuffs.restaurant;
import stuffs.food;
import stuffs.Menu;
import stuffs.Message;
import stuffs.Quantity;
import stuffs.ReadWriteFile;
import stuffs.Message;

public class Main extends Application{

    // @Override
    // public void start(Stage primaryStage) throws Exception{
    //     Parent root=FXMLLoader.load(getClass().getResource("hellofx.fxml"));
    //     primaryStage.setTitle("Hello World");
    //     primaryStage.setScene(new Scene(root));
    //     primaryStage.show();
    // } 
    public static Menu menu=new Menu();
    public ArrayList<restaurant> rlist=new ArrayList<>();
    public ArrayList<food> flist=new ArrayList<>();

    private Stage stage;
    private SocketWrapper socketWrapper;
    private Message message;

    
    // Main()
    // {
    //     this.menu=new Menu();
    //     this.rlist= new ArrayList<>();
    //     this.flist = new ArrayList<>();
    // }
    
    public Stage getStage() {
        return stage;
    }

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // public void showHomePage(String userName) throws Exception {

    //     FXMLLoader loader = new FXMLLoader();
    //     loader.setLocation(getClass().getResource("home.fxml"));
    //     Parent root = loader.load();

    //     // Loading the controller
    //     HomeController controller = loader.getController();
    //     controller.init(userName);
    //     controller.setMain(this);

    //     // Set the primary stage
    //     stage.setTitle("Home");
    //     stage.setScene(new Scene(root, 400, 300));
    //     stage.show();
    // }

    public void showCustomerHomePage(String userName) throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("customerhome.fxml"));
        Parent root=loader.load();

        CustomerHomeController controller=loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Customer Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showSearchResPage() throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("searchRestaurant.fxml"));
        Parent root=loader.load();

        SearchRestaurantController controller=loader.getController();
        controller.setMain(this);
        controller.setMenu(this.menu);
        controller.init();
        // Set the primary stage
        stage.setTitle("Restaurant Searching Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showSearchFoodPage() throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("searchFood.fxml"));
        Parent root=loader.load();

        searchFoodController controller=loader.getController();
        controller.setMain(this);
        controller.setMenu(this.menu);
        controller.init();

        // Set the primary stage
        stage.setTitle("Food Searching Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showResDetails(restaurant res) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("resDetails.fxml"));
        Parent root=loader.load();

        ResDetailController controller=loader.getController();
        controller.setMain(this);
        controller.setRestaurant(res);
        controller.init();

        stage.setTitle("Restaurant Details");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showRestaurantHomePage(String resName) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("resHome.fxml"));
        Parent root=loader.load();

        RestaurantHomeController controller=loader.getController();
        controller.setMain(this);
        controller.setResName(resName);
        controller.init();
        // Set the primary stage
        stage.setTitle("Restaurant Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showCartPage(ArrayList<food> flist,ArrayList<Quantity> qlist,restaurant res,Double price) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("addCart.fxml"));
        Parent root=loader.load();

        AddCartController controller=loader.getController();
        controller.setMain(this);
        controller.setFoodList(flist);
        controller.setQuanList(qlist);
        controller.setPrice(price);
        controller.setRestaurant(res);
        controller.setResName(res.getName());
        controller.init();
        

        // Set the primary stage
        stage.setTitle("Add Cart Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showFinalizeOrderPage(Message message) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("finalize.fxml"));
        Parent root=loader.load();

        OrderConfirmController controller=loader.getController();
        controller.setMain(this);
        controller.setMessage(message);
        controller.setResName(message.getResName());
        controller.init();

        stage.setTitle("Add Cart Page");
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    public void setMenu(Menu menu)
    {
        this.menu=menu;
    }

    public Menu getMenu()
    {
        return this.menu;
    }

    public void setMessage(Message message)
    {
        this.message=message;
    }

    public Message getMessage()
    {
        return this.message;
    }

    public static void main(String[] args) throws Exception {
        // This will launch the JavaFX application
        ArrayList<String> list=new ArrayList<>();
        list=menu.getCategories();
        if(list.size()==0)
        {
            System.out.println("Something went wrong");
        }
        ReadWriteFile.readRestaurant(menu);
        ReadWriteFile.readFood(menu);
        launch(args);
    }
}
