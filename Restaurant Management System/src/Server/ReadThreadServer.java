package Server;

import java.io.IOException;
import java.util.HashMap;

import util.LoginDTO;
import util.RestaurantLogin;
import util.SocketWrapper;
import stuffs.restaurant;
import stuffs.food;
import stuffs.Menu;
import stuffs.Message;



public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final SocketWrapper socketWrapper;
    public HashMap<String, String> userMap;
    public HashMap<String, String> resUserMap;
    public HashMap<String, SocketWrapper> clientMap;
    //private Menu menu;

    public ReadThreadServer(HashMap<String, String> map,HashMap<String, String> map2,HashMap<String, SocketWrapper> map3, SocketWrapper socketWrapper) {
        this.userMap = map;
        this.resUserMap = map2;
        this.clientMap = map3;
        this.socketWrapper = socketWrapper;
        //this.menu=menu;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO);
                    }

                    if (o instanceof RestaurantLogin) {
                        RestaurantLogin resLogin = (RestaurantLogin) o;
                        String password = resUserMap.get(resLogin.getResName());
                        resLogin.setStatus(resLogin.getPassword().equals(password));
                        //resLogin.setStatus(true);
                        socketWrapper.write(resLogin);
                        System.out.println("Checked");
                        if(resLogin.isStatus())
                        {
                            this.clientMap.put(resLogin.getResName(), socketWrapper);
                        }
                    }

                    if (o instanceof Message) {
                        
                        Message message =(Message) o;
                        if(clientMap.containsKey(message.getResName()))
                        {
                            clientMap.get(message.getResName()).write(message);
                            System.out.println("Message delivered");
                        }
                    }
                }

                }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
