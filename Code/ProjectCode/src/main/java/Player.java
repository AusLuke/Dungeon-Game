import javafx.scene.paint.ImagePattern;
import javafx.util.Pair;

import javafx.scene.image.Image;

public class Player extends Tile
{
    Pair<Integer, Integer> coordinates = new Pair<>(0, 0);
    Image image = new Image("dragon.jpg");
    ImagePattern imagePattern = new ImagePattern(image);

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
}
