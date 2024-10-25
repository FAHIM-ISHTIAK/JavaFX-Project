package stuffs;

import java.io.Serializable;

public class food implements Serializable{
    
    private String name;
    private double price;
    private int resId;
    private String category;

    public food(int resId,String category,String name,double price)
    {
        this.name=name;
        this.price=price;
        this.resId=resId;
        this.category=category;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setPrice(double price)
    {
        this.price=price;
    }

    public void setResId(int resId)
    {
        this.resId=resId;
    }

    public void setCatagory(String category)
    {
        this.category=category;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public int getResId()
    {
        return this.resId;
    }

    public String getCategory()
    {
        return this.category;
    }
}

