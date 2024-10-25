package stuffs;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Scanner;

//import javax.crypto.AEADBadTagException;

public class Menu implements Serializable{
    
    private ArrayList<restaurant> restaurants;
    private ArrayList<food> foods;
    private ArrayList<String> categories;
    
    //Scanner scn=new Scanner(System.in);

    public Menu()
    {
        restaurants=new ArrayList<>();
        foods=new ArrayList<>();
        categories=new ArrayList<>();
    }

    public void storeCategory(String cat)
    {
        for(String s:categories)
        {
            if(cat.equalsIgnoreCase(s))
            return;
        }
        categories.add(cat);
    }

    public int giveResId(String s)
    {
        for(restaurant r:restaurants)
        {
            if(r.getName().equalsIgnoreCase(s))
            return r.getId();
        }
        return -1;
    }

    public int restaurantCount()
    {
        return restaurants.size();
    }
    public Boolean hasRestaurant(String s)
    {
        for(restaurant r:restaurants)
        {
            if(s.equalsIgnoreCase(r.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public void addRestaurant(restaurant r)
    {
        restaurants.add(r);
    }

    public void addFood(food f)
    {
        foods.add(f);
        for(restaurant r:restaurants)
        {
            if(r.getId()==f.getResId())
            {
                r.addFood(f);
                break;
            }
        }
    }

    public ArrayList<restaurant> searchRestaurantByName(String name)
    {
        ArrayList<restaurant> rlist=new ArrayList<>();
        
        for(restaurant r: restaurants)
        {
            if(name.equalsIgnoreCase(r.getName())||r.getName().toLowerCase().contains(name.toLowerCase()))
            {
                rlist.add(r);
            }
        }
        return rlist;
    }
    
    public ArrayList<restaurant> searchRestaurantByScore(double lower,double upper)
    {
        //Boolean done=false;
        ArrayList<restaurant> rlist=new ArrayList<>();

        for(restaurant r: restaurants)
        {
            if(r.getScore()>=lower&&r.getScore()<=upper)
            {
                rlist.add(r);
                //done=true;
            }
        }
        return rlist;
    }

    public ArrayList<restaurant> searchRestaurantByCategory(String cat)
    {
        //Boolean done=false;
        ArrayList<restaurant> rlist=new ArrayList<>();

        for(restaurant r: restaurants)
        {
            if(r.isCategory(cat))
            {
                rlist.add(r);
                //done=true;
            }
        }
        return rlist;
        /*if(!done)
        {
            System.out.println("No such restaurant with this category");
        }*/
    }

    public ArrayList<restaurant> searchRestaurantByPrice(String price)
    {
        //Boolean done=false;
        ArrayList<restaurant> rlist=new ArrayList<>();
        for(restaurant r: restaurants)
        {
            if(r.getPrice().equalsIgnoreCase(price))
            {
                rlist.add(r);
                //done=true;
            }
        }
        return rlist;
        /*if(!done)
        {
            System.out.println("No such restaurant with this price");        
        }*/
    }

    public ArrayList<restaurant> searchRestaurantByZipCode(String code)
    {
        //Boolean done=false;
        ArrayList<restaurant> rlist=new ArrayList<>();
        for(restaurant r: restaurants)
        {
            if(r.getZipcode().equals(code))
            {
                rlist.add(r);
                //done=true;
            }
        }
        return rlist;
        /*if(!done)
        {
            System.out.println("No such restaurant with this zip code");       
        }*/
    }

    /*public void searchByCatagoryWise()
    {
        for(String s:catagories)
        {
            System.out.print(s+": ");
            for(restaurant r:restaurants)
            {
                if(r.isCatagory(s))
                {
                    System.out.print(r.getName()+",");
                }
            }
            System.out.println();
        }

    }*/
    
    public ArrayList<String> getCategories()
    {
        return categories;
    }

    public ArrayList<restaurant> getRestaurants()
    {
        return restaurants;
    }
    
    public ArrayList<food> getFoods()
    {
        return foods;
    }
    
    public ArrayList<food> searchFoodByName(String foodName)
    {
        ArrayList<food> flist=new ArrayList<>();
        for(food f:foods)
        {
            if(f.getName().equalsIgnoreCase(foodName)||f.getName().toLowerCase().contains(foodName.toLowerCase()))
            {
                flist.add(f);
            }
        }
        return flist;
    }

    public ArrayList<food> searchFoodByNameInRestaurant(String restaurantName,String foodName)
    {
        int id=-1;
        ArrayList<food> flist=new ArrayList<>();
        for(restaurant r:restaurants)
        {
            if(r.getName().equalsIgnoreCase(restaurantName))
            {
                id=r.getId();
                break;
            }
        }
        for(food f:foods)
        {
            if(f.getResId()==id&&(f.getName().equalsIgnoreCase(foodName)||f.getName().toLowerCase().contains(foodName.toLowerCase())))
            {
                flist.add(f);
            }
        }
        return flist;

    }

    public ArrayList<food> searchFoodByCategory(String Category)
    {
        ArrayList<food> flist=new ArrayList<>();
        for(food f:foods)
        {
            if(f.getCategory().equalsIgnoreCase(Category)||f.getCategory().toLowerCase().contains(Category.toLowerCase()))
            {
                flist.add(f);
            }
        }
        return flist;
    }

    public ArrayList<food> searchFoodByCategoryInRestaurant(String Category,String restaurantName)
    {
        ArrayList<food> flist=new ArrayList<>();
        //restaurant res;
        int resId=-1;
        Boolean found=false;
        for(restaurant r: restaurants)
        {
            if(r.getName().equalsIgnoreCase(restaurantName))
            {
                resId=r.getId();
                found=true;
                break;
            }
        }
        if(found)
        {
            for(food f:foods)
            {
                if(f.getResId()==resId&&f.getCategory().equalsIgnoreCase(Category))
                {
                    flist.add(f);
                }
            }
        }  
        return flist;
    }

    public ArrayList<food> searchFoodByPrice(double lower,double upper)
    {
        ArrayList<food> flist=new ArrayList<>();
        for(food f:foods)
        {
            if(f.getPrice()>=lower&&f.getPrice()<=upper)
            {
                flist.add(f);
            }
        }
        return flist;
    }

    public ArrayList<food> searchFoodByPriceInRestaurant(double lower,double upper,String restaurantName)
    {
        int id=-1;
        for(restaurant r:restaurants)
        {
            if(r.getName().equalsIgnoreCase(restaurantName))
            {
                id=r.getId();
                break;
            }
        }
        ArrayList<food> flist=new ArrayList<>();
        for(food f:foods)
        {
            if(f.getResId()==id&&f.getPrice()>=lower&&f.getPrice()<=upper)
            {
                flist.add(f);
            }
        }
        return flist;
    }

    public ArrayList<food> searchCostliestFoodInRestaurant(String restaurantName)
    {
        int id=-1;
        for(restaurant r:restaurants)
        {
            if(r.getName().equalsIgnoreCase(restaurantName))
            {
                id=r.getId();
                break;
            }
        }
        double maxPrice=-1.0;
        for(food f:foods)
        {
            if(f.getResId()==id)
            {
                if(f.getPrice()>maxPrice)
                maxPrice=f.getPrice();
            }
        }
        ArrayList<food> flist=new ArrayList<>();
        for(food f:foods)
        {
            if(f.getResId()==id&&f.getPrice()==maxPrice)
            {
                flist.add(f);
            }
        }
        return flist;
    }
    public int[] totalFoodItemInRestaurant()
    {
        int foodFrequency[]=new int[restaurants.size()+1];
        for(food f:foods)
        {
            foodFrequency[f.getResId()]++;
        }
        return foodFrequency;
    }




}
