package stuffs;

import java.io.Serializable;
import java.util.ArrayList;

public class Quantity implements Serializable{
    
    Integer quantity;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return this.quantity;
    }
}
