import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Wall extends Tile
{
    boolean visited = false;
    Image image = new Image("rock.jpg");
    ImagePattern imagePattern = new ImagePattern(image);

//    Wall()
//    {
//        this.setWidth(100);
//        this.setHeight(100);
//        this.setFill(Paint.valueOf("#000000"));
//    }

    //If you encounter a new wall, fill it, otherwise return 0 to avoid printing to the chat bar
    public int setImage()
    {
        if (visited) return 0;
        this.setFill(imagePattern);
        visited = true;
        return 2;
    }
}
