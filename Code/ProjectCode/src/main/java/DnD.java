import java.util.Scanner;

public class DnD
{

    public static void main(String[] args)
    {
        GameBoard gameBoard = new GameBoard();
        Scanner input = new Scanner(System.in);

        while (true)
        {
            gameBoard.printBoard();
            gameBoard.doPlayerMove(input.next());
        }
    }
}
