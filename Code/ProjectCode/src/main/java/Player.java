import javafx.scene.paint.ImagePattern;
import javafx.util.Pair;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Player extends Tile
{
    Pair<Integer, Integer> coordinates = new Pair<>(0, 0);
    Image image = new Image("dragon.jpg");
    ImagePattern imagePattern = new ImagePattern(image);
    HashMap<String, Integer> inventory = new HashMap<>();
    boolean hasKey = false;

    Player()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(imagePattern);
    }

    //Return the coordinates of the player
    public Pair getCoordinates()
    {
        return coordinates;
    }

    //Set the players new coordinates for swapping
    public void setCoordinates(int xPos, int yPos)
    {
        coordinates = new Pair<>(xPos, yPos);
    }

    //Change the player's key status if they found a key or consumed one
    public void setKey()
    {
        //Update the hash map to include a key, or decrement one
        if (!hasKey)
            inventory.put("Key", 1);
        else
            inventory.merge("Key", -1, Integer::sum);
        hasKey = !hasKey;
    }

    //Check the player's key status
    public boolean checkKey()
    {
        return hasKey;
    }

    //Creates a string to display the player's inventory
    public String getInventory()
    {
        StringBuilder list = new StringBuilder();
        list.append("\nInventory:\n");

        if (inventory.isEmpty())
        {
            list.append(" Empty...\n\n");
            return list.toString();
        }

        for (Map.Entry<String, Integer> x : inventory.entrySet())
            list.append(" ").append(x.getKey()).append(" ").append(x.getValue()).append("x\n");
        list.append("\n");
        return list.toString();
    }
}
