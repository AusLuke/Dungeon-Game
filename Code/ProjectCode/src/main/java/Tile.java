import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    Image image = new Image("grass.png");
    ImagePattern imagePattern = new ImagePattern(image);
    int tileStatus = 1;

    Tile()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#000000"));
    }

    //Flagged constructor for swapping tiles with the player
    Tile(int flag)
    {
        tileStatus = 0;
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(imagePattern);
    }

    //Return if the tile is new (1) or not (0)
    public int getStatus()
    {
        return tileStatus;
    }
}
