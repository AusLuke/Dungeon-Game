import com.sun.prism.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameBoard1
{
    private GridPane gameBoard;
    private Player1 player = new Player1();

    GameBoard1()
    {
        gameBoard = new GridPane();
        for(int x = 0; x < 6; x++)
            for(int y = 0; y < 4; y++)
                gameBoard.add(new Tile1(), x, y, 1, 1);
        gameBoard.add(player, 0, 0);
        gameBoard.setHgap(1);
        gameBoard.setVgap(1);

        gameBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            Node source = e.getPickResult().getIntersectedNode();
            System.out.println(source);
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.println(colIndex + " " + rowIndex);

            //Swapping if valid
        });
    }

    GridPane getGameBoard()
    {
        return gameBoard;
    }

}
