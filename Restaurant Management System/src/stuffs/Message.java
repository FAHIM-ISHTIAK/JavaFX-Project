package stuffs;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{
    
    private String resName;
    private ArrayList<food> foodList;
    private String msg;
    private Double price;

    public Message(String resName,ArrayList<food> foodList,Double price)
    {
        this.resName=resName;
        this.foodList=foodList;
        this.price=price;
    }

    public void setResName(String resName)
    {
        this.setResName(resName);
    }

    public void setResFoodList(ArrayList<food> foodlList)
    {
        this.foodList=foodlList;
    }

    public void setMsg(String msg)
    {
        this.msg=msg;
    }

    public void setPrice(Double price)
    {
        this.price=price;
    }

    public String getResName()
    {
        return this.resName;
    }

    public ArrayList<food> getFoodList()
    {
        return this.foodList;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public Double getPrice()
    {
        return this.price;
    }
}
