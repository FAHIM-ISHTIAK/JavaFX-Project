package Sample;

import javafx.application.Platform;
import util.LoginDTO;
import util.RestaurantLogin;
import stuffs.Message;
import stuffs.Menu;

import java.io.IOException;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getSocketWrapper().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created
                        // threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showCustomerHomePage(loginDTO.getUserName());
                                        // Object obj=main.getSocketWrapper().read();
                                        // main.menu=(Menu) obj;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }

                    if (o instanceof RestaurantLogin) {
                        RestaurantLogin resLogin = (RestaurantLogin) o;
                        System.out.println(resLogin.getResName());
                        System.out.println(resLogin.isStatus());
                        // the following is necessary to update JavaFX UI components from user created
                        // threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (resLogin.isStatus()) {
                                    try {
                                        //main.showCustomerHomePage(loginDTO.getUserName());
                                        // Object obj=main.getSocketWrapper().read();
                                        // main.menu=(Menu) obj;
                                        main.showRestaurantHomePage(resLogin.getResName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }

                    if (o instanceof Message) {
                        Message message = (Message) o;
                        // the following is necessary to update JavaFX UI components from user created
                        // threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                // if (resLogin.isStatus()) {
                                //     try {
                                //         //main.showCustomerHomePage(loginDTO.getUserName());
                                //         // Object obj=main.getSocketWrapper().read();
                                //         // main.menu=(Menu) obj;
                                //         main.showRestaurantHomePage(resLogin.getResName());
                                //     } catch (Exception e) {
                                //         e.printStackTrace();
                                //     }
                                // } else {
                                //     main.showAlert();
                                // }

                                try {
                                        main.showFinalizeOrderPage(message);
                                        //main.setMessage(message);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getSocketWrapper().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
