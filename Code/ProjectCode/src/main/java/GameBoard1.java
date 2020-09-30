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

    GameBoard1()
    {
        gameBoard = new GridPane();
        for(int x = 0; x < 6; x++)
            for(int y = 0; y < 4; y++)
                gameBoard.add(new Tile1(), x, y, 1, 1);
        gameBoard.add(new Player1(), 0, 0);
        gameBoard.setHgap(1);
        gameBoard.setVgap(1);

        gameBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            //System.out.println(e.getX() + " " + e.getY() + " " + e.getButton());
            Node source = (Node)e.getSource() ;
            Integer colIndex = gameBoard.getColumnIndex(source);
            Integer rowIndex = gameBoard.getRowIndex(source);
            System.out.println(colIndex + " " + rowIndex);
        });


        //gameBoard.onMouseClickedProperty()
    }

    GridPane getGameBoard()
    {
        return gameBoard;
    }


}
