package stuffs;

import java.io.Serializable;
import java.util.ArrayList;

public class restaurant implements Serializable{
    
    private int id;
    private double score;
    private String name;
    private String price;
    private String zipcode;
    private String[] category;
    ArrayList<food> foods;
    private int catcount;

    public restaurant(int id,String name,double score,String price,String zipcode,String[] category)
    {
        this.id=id;
        this.score=score;
        this.name=name;
        this.price=price;
        this.zipcode=zipcode;
        //category=new String[3];
        foods=new ArrayList<>();
        this.category=category;
        catcount=this.category.length;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }

    public void setScore(double score)
    {
        this.score=score;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setPrice(String price)
    {
        this.price=price;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode=zipcode;
    }

    public void addCategory(String Category)
    {
        if(catcount==3)
        return;
        category[catcount++]=Category;
    }

    public void addFoodToRestaurant(food f)
    {
        foods.add(f);
    }

    public int getId()
    {
        return this.id;
    }

    public double getScore()
    {
        return this.score;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPrice()
    {
        return this.price;
    }
    
    public String getZipcode()
    {
        return this.zipcode;
    }
    
    public void addFood(food f)
    {
        this.foods.add(f);
    }
    
    public ArrayList<food> getFoods()
    {
        return this.foods;
    }

    public Boolean hasFood(String s)
    {
        for(food f:foods)
        {
            if(s.equalsIgnoreCase(f.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public String[] getCategory()
    {
        return category;
    }

    public Boolean isCategory(String cat)
    {
        for(String s:category)
        {
            if(cat.equalsIgnoreCase(s)||s.toLowerCase().contains(cat.toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }
    
    public void showDetails()
    {
        System.out.println("Restaurant Name: "+name);
        System.out.println("Id: "+ id);
        System.out.println("Score: "+ score);
        System.out.println("Price: "+ price);
        System.out.println("Zip Code: "+ zipcode);
        System.out.println("Catagory: ");
        for(int i=0;i<catcount;i++)
        {
            System.out.print(category[i]+" :");
        }
        System.out.println("\n");
    }
    
}
