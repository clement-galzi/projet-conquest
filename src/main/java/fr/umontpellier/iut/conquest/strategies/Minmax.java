package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

import java.util.ArrayList;
import java.util.List;

public class Minmax implements Strategy {
    private int IALevel;

    public Minmax(int IALevel) {
        this.IALevel = IALevel;
    }

    @Override
    public Move getMove(Board board, Player player) {
        List<Move> moveValid = board.getValidMoves(player);
        Move mouvement = moveValid.get(0);
        int valeur = Integer.MIN_VALUE;
        Board boardCourant = new Board(board);
        for(Move move : moveValid) {
            System.out.println(move.toString());
            int res = Minmax(this.IALevel-1,player,boardCourant,true);
            System.out.println(res);
            if ( res > valeur){
                valeur = res;
                mouvement = move;
            }
        }
        System.out.println(mouvement.toString());
        return mouvement;
    }

    public int Minmax (int profondeur, Player joueurCourant, Board boardCourant, boolean joueurActuel) {
        List<Move> mouvement = boardCourant.getValidMoves(joueurCourant);
        int value = 0;
        if (profondeur == 0){
            for (Move move : mouvement) {
                Board board = new Board(boardCourant);
                board.movePawn(move);
                return board.evaluer(joueurCourant);
            }
        }
        if (joueurActuel) {
            value = Integer.MIN_VALUE;
            for (Move move : mouvement) {
                Board board = new Board(boardCourant);
                board.movePawn(move);
                value = max(value,Minmax(profondeur-1,joueurCourant.getGame().getOtherPlayer(joueurCourant),board,false));
            }
        }
        else{
            value = Integer.MAX_VALUE;
            for (Move move : mouvement) {
                Board board = new Board(boardCourant);
                board.movePawn(move);
                value = min(value,Minmax(profondeur-1,joueurCourant.getGame().getOtherPlayer(joueurCourant),board,true));
            }
        }
        return value;
    }

    public int max (int value,int minmax){
        if (value > minmax){
            return value;
        }
        else {
            return minmax;
        }
    }

    public int min (int value,int minmax){
        if (value < minmax){
            return value;
        }
        else {
            return minmax;
        }
    }
/**
    function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
    value := −∞
            for each child of node do
    value := max(value, minimax(child, depth − 1, FALSE))
            return value
    else (* minimizing player *)
    value := +∞
            for each child of node do
    value := min(value, minimax(child, depth − 1, TRUE))
            return value
 */
}