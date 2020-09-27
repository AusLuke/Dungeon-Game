import com.sun.prism.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameBoard1
{
    private GridPane gameBoard;

    GameBoard1()
    {
        gameBoard = new GridPane();
        for(int x = 0; x < 6; x++)
            for(int y = 0; y < 4; y++)
            {
                gameBoard.add(new Tile1(), x, y, 1, 1);
            }
        gameBoard.setHgap(1);
        gameBoard.setVgap(1);
    }

    GridPane getGameBoard()
    {
        return gameBoard;
    }
}
