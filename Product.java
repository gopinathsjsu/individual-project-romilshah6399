public class Product implements Item
{
    String description;
    float price;

    Product(String d)
    {
        description=d;
    }

    public String getItemName()
    {
        return description;
    }

    public float getItemPrice()
    {
        return price;
    }


    public void addItem(Item i)
    {

    }

}