public class GameBoard
{
    Tile[][] gameBoard = new Tile[8][12];
    Player player = new Player();

    //Can read in text file here to load level
    GameBoard()
    {
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 12; y++)
                gameBoard[x][y] = new Tile();
        gameBoard[(int) player.getCoordinates().getKey()][(int) player.getCoordinates().getValue()] = player;
        gameBoard[2][2] = new Wall();
        gameBoard[2][3] = new Wall();
        gameBoard[3][2] = new Wall();
        gameBoard[4][2] = new Wall();
        gameBoard[5][2] = new Wall();
        gameBoard[6][2] = new Wall();
        gameBoard[7][2] = new Wall();
        gameBoard[7][4] = new Wall();
        gameBoard[6][4] = new Wall();
        gameBoard[5][4] = new Wall();
        gameBoard[4][4] = new Wall();
        gameBoard[4][5] = new Wall();
        gameBoard[7][3] = new Chest();



    }

    public void printBoard ()
    {
        for (Tile[] x : gameBoard)
        {
            for (Tile y : x)
                System.out.print(y.getTile() + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void doPlayerMove(String direction)
    {
        int x = (int) player.getCoordinates().getKey();
        int y = (int) player.getCoordinates().getValue();
        gameBoard[x][y] = new Tile();

        if (direction.equals("w") && !gameBoard[x - 1][y].getTile().equals("W") && x != 0)
        {
            player.setCoordinates(x - 1, y);
            gameBoard[x - 1][y] = player;
        }
        else if (direction.equals("a") && !gameBoard[x][y - 1].getTile().equals("W") && y != 0)
        {
            player.setCoordinates(x, y - 1);
            gameBoard[x][y - 1] = player;
        }
        else if (direction.equals("s") && !gameBoard[x + 1][y].getTile().equals("W") && x != 7)
        {
            player.setCoordinates(x + 1, y);
            gameBoard[x + 1][y] = player;
        }
        else if (direction.equals("d") && !gameBoard[x][y + 1].getTile().equals("W") && (int) y != 11)
        {
            player.setCoordinates(x, y + 1);
            gameBoard[x][y + 1] = player;
        }
        else
            gameBoard[x][y] = player;
    }
}
