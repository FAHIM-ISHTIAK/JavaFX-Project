package Sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import util.LoginDTO;
import util.RestaurantLogin;

public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField userText2;

    @FXML
    private PasswordField passwordText2;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button resetButton2;

    @FXML
    private Button loginButton2;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            main.getSocketWrapper().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    @FXML
    void loginAction2(ActionEvent event)
    {
        String resName = userText2.getText();
        String password = passwordText2.getText();
        RestaurantLogin resLogin = new RestaurantLogin();
        resLogin.setResName(resName);
        resLogin.setPassword(password);
        try {
            main.getSocketWrapper().write(resLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction2(ActionEvent event)
    {
        userText2.setText(null);
        passwordText2.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

}
