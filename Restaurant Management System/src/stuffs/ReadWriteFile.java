package stuffs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadWriteFile {

    private static final String INPUT_RESTAURANT_FILE_NAME = "restaurant.txt";
    private static final String INPUT_MENU_FILE_NAME= "menu.txt";
    private static final String INPUT_USER_FILE_NAME="user.txt";
    private static final String INPUT_RESUSER_FILE_NAME="resUser.txt";

    public static void readRestaurant(Menu menu) throws Exception
    {
        BufferedReader resReader = new BufferedReader(new FileReader(INPUT_RESTAURANT_FILE_NAME));
        //ArrayList<restaurant> rlist= new ArrayList<>();
        while(true)
        {
            String line = resReader.readLine();
            if (line == null) break;
            //System.out.println(line);
            String [] resArray = line.split(",", -1);
            String [] category={};
            if(resArray.length>=8)
                category=new String[]{resArray[5],resArray[6],resArray[7]};
            if(resArray.length==7)
                category=new String[]{resArray[5],resArray[6]};
            if(resArray.length==6)
                category=new String[]{resArray[5]};
            restaurant r=new restaurant(Integer.parseInt(resArray[0]),resArray[1],Double.parseDouble(resArray[2]),resArray[3],resArray[4],category);
            //rlist.add(r);
            for(String s:category)
            {
                menu.storeCategory(s);
            }
            menu.addRestaurant(r);
        }
        resReader.close();

        //return rlist;
    }

    public static void readFood(Menu menu) throws IOException
    {
        BufferedReader foodReader = new BufferedReader(new FileReader(INPUT_MENU_FILE_NAME));
        ArrayList<food> flist= new ArrayList<>();
        while(true)
        {
            String line = foodReader.readLine();
            if (line == null) break;
            //System.out.println(line);
            String [] foodArray = line.split(",", -1);
            food f=new food(Integer.parseInt(foodArray[0]),foodArray[1],foodArray[2],Double.parseDouble(foodArray[3]));
            //menu.storeCategory(foodArray[1]);
            menu.addFood(f);
            //flist.add(f);
        }
        foodReader.close();

        //return flist;
    }

    public static void readUser(HashMap<String,String> map) throws IOException
    {
        BufferedReader userReader = new BufferedReader(new FileReader(INPUT_USER_FILE_NAME));
        while(true)
        {
            String line = userReader.readLine();
            if(line == null) break;
            String [] array = line.split(",",-1);
            map.put(array[0], array[1]);
        }
        userReader.close();
    }

    public static void readResUser(HashMap<String,String> map) throws IOException
    {
        BufferedReader resUserReader = new BufferedReader(new FileReader(INPUT_RESUSER_FILE_NAME));
        while(true)
        {
            String line = resUserReader.readLine();
            if(line == null) break;
            String [] array = line.split(",",-1);
            map.put(array[0], array[1]);
        }
        resUserReader.close();
    }
}
