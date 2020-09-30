import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    Tile()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#000000"));
    }
    Tile(int color)
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#C0C0C0"));
    }
}
