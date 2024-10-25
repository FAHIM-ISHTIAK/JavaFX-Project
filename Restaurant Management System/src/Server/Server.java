package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import util.SocketWrapper;
import stuffs.Menu;
import stuffs.ReadWriteFile;
import stuffs.restaurant;
import stuffs.food;


public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public HashMap<String, String> resUserMap;
    public HashMap<String, SocketWrapper> clientMap;

    public Menu menu;

    Server() throws Exception {
        userMap = new HashMap<>();
        // userMap.put("a", "a");
        // userMap.put("b", "b");
        // userMap.put("c", "c");
        // userMap.put("d", "d");
        // userMap.put("e", "e");

        resUserMap = new HashMap<>();
        // resUserMap.put("KFC", "a");
        // resUserMap.put("BFC", "b");
        // resUserMap.put("CFC", "c");
        // resUserMap.put("DFC", "d");
        // resUserMap.put("EFC", "e");

        clientMap = new HashMap<>();

        ReadWriteFile.readUser(userMap);
        ReadWriteFile.readResUser(resUserMap);

        menu = new Menu();
        ReadWriteFile.readRestaurant(menu);
        ReadWriteFile.readFood(menu);
        
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ReadThreadServer(userMap, resUserMap, clientMap, socketWrapper);
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
