package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testeRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}
		
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];
		
		Position p = new Position(0, 0);
		// above
		p.setValues(position.getRow() - 1, position.getCol());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// bellow
		p.setValues(position.getRow() + 1, position.getCol());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// left
		p.setValues(position.getRow(), position.getCol() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// right
		p.setValues(position.getRow(), position.getCol() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// NW
		p.setValues(position.getRow() - 1, position.getCol() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// NE
		p.setValues(position.getRow() - 1, position.getCol() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// SW
		p.setValues(position.getRow() + 1, position.getCol() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		// SE
		p.setValues(position.getRow() + 1, position.getCol() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCol()] = true;
		}
		
		// special move Castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// special move King side rook (rook pequeno)
			Position posT1 = new Position(position.getRow(), position.getCol() + 3);
			if (testeRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getCol() + 1);
				Position p2 = new Position(position.getRow(), position.getCol() + 2);
				
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getCol() + 2] = true;
				}
				
			}
			// special move Queen side rook (rook grande)
			Position posT2 = new Position(position.getRow(), position.getCol() - 4);
			if (testeRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getCol() - 1);
				Position p2 = new Position(position.getRow(), position.getCol() - 2);
				Position p3 = new Position(position.getRow(), position.getCol() - 3);
				
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getCol() - 2] = true;
				}
				
			}
		}
		
		
		return mat;
	}
}
