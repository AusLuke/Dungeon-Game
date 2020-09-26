import javafx.util.Pair;

public class Player extends Tile
{
    Pair<Integer, Integer> coordinates = new Pair<>(0, 0);

    Player()
    {
    }

    public String getTile()
    {
        return "P";
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
