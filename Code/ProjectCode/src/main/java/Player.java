import javafx.scene.paint.Paint;
import javafx.util.Pair;

public class Player extends Tile
{
    Pair<Integer, Integer> coordinates = new Pair<>(0, 0);

    Player()
    {
        this.setWidth(100);
        this.setHeight(100);
        this.setFill(Paint.valueOf("#0000FF"));
        /*this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                setFill(Paint.valueOf("#00FF00"));
            }
        });*/
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
