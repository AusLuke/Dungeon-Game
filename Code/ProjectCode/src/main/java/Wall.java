import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Wall extends Tile
{
    Wall()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#000000"));
    }
    public void setImage()
    {
        this.setWidth(100);
        this.setHeight(100);
        Image image = new Image("rock.jpg");
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
    }
}
