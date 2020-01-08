package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

import java.util.List;

public class Naive implements Strategy {
    @Override
    public Move getMove(Board board, Player player) {
        List<Move> mouvement = board.getValidMoves(player);
        if (mouvement.isEmpty()){
            System.out.println("aucun coup valide possible");
            return null;
        }
        else{
            return mouvement.get(0);
        }
    }
}
