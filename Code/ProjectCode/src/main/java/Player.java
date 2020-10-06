import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import javafx.scene.image.Image;

public class Player extends Tile
{
    Pair<Integer, Integer> coordinates = new Pair<>(0, 0);

    Player()
    {
        this.setWidth(100);
        this.setHeight(100);
        Image image = new Image("dragon.jpg");
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);

    }

    public Pair getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(int xPos, int yPos)
    {
        coordinates = new Pair<>(xPos, yPos);
    }
}
