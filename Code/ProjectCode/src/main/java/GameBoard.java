import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class GameBoard
{
    private GridPane gameBoard;
    private Player player = new Player();

    GameBoard()
    {
        gameBoard = new GridPane();
        for(int x = 0; x < 6; x++)
            for(int y = 0; y < 4; y++)
                gameBoard.add(new Tile(), x, y, 1, 1);
        gameBoard.add(player, 0, 0);
        gameBoard.add(new Wall(), 1, 1);
        gameBoard.add(new Wall(), 2, 0);
        gameBoard.add(new Wall(), 1, 3);
        gameBoard.add(new Wall(), 4, 1);
        gameBoard.add(new Wall(), 5, 1);
        gameBoard.add(new Wall(), 3, 3);
        gameBoard.setHgap(1);
        gameBoard.setVgap(1);

        gameBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            try
            {
                Node source = e.getPickResult().getIntersectedNode();
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);

                int playerC = (int) player.getCoordinates().getKey();
                int playerR = (int) player.getCoordinates().getValue();


                if (playerC == colIndex - 1 && playerR == rowIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerC == colIndex + 1 && playerR == rowIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerR == rowIndex - 1 && playerC == colIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
                else if (playerR == rowIndex + 1 && playerC == colIndex)
                    swap(source, playerC, playerR, colIndex, rowIndex);
            }
            catch(Exception ignored){}
        });
    }

    void swap(Node source, int playerC, int playerR, int colIndex, int rowIndex)
    {
        if (source.getClass() == Wall.class)
        {
            ((Wall)source).setFill(Paint.valueOf("#000080"));
            return;
        }

        Player temp = player;
        player.setCoordinates(colIndex, rowIndex);
        gameBoard.getChildren().remove(temp);
        gameBoard.add(new Tile(1), playerC, playerR, 1, 1);
        gameBoard.add(temp, colIndex, rowIndex, 1, 1);
    }

    GridPane getGameBoard()
    {
        return gameBoard;
    }

}
