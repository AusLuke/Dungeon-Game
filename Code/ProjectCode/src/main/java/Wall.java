import javafx.scene.paint.Paint;

public class Wall extends Tile
{
    Wall()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#000000"));
    }
}
