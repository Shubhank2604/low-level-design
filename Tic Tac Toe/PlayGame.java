public class PlayGame {
    public static void main(String[] args) {
        System.out.println("\n===>>> TicTacToe Game\n");
        TicTacToe game = new TicTacToe();
        game.initializeGame();
        String status = game.startGame();
        if ("WIN".equals(status)) {
            System.out.print("\n===>>> GAME OVER: " + game.winner.getName() + " WON ");
        } else {
            System.out.print("\n===>>> GAME OVER: DRAW");
        }
    }
}
