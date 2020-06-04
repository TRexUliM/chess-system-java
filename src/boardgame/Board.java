package boardgame;

import boardgame.exception.BoardException;

public class Board {

	private int rows;
	private int cols;
	private Piece[][] pieces;
	
	public Board(int rows, int cols) {
		if (rows < 1 || cols < 1) {
			throw new BoardException("Error Creating board: there must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.cols = cols;
		pieces = new Piece[rows][cols];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public Piece piece (int row, int col) {
		if (!positionExists(row, col)) {
			throw new BoardException("Position not on the board");
		}
		
		return pieces[row][col];
	}
	
	public Piece piece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getCol()];
	}
	
	public void placePiece (Piece piece, Position position) { 
		if (thereIsAPiece(position)) {
			throw new BoardException("Theres already a piece on position " + position);
		}
		pieces[position.getRow()][position.getCol()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getCol()] = null;
		return aux;
		
	}
	
	public boolean positionExists(int row, int column) {
		return (row >= 0) && (row < rows) && (column >= 0) && (column <= cols);
	}
	
	public boolean positionExists(Position position)  {
		return positionExists(position.getRow(), position.getCol());
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		
		return piece(position) != null;
	}
}
