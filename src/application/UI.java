package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading Chess Position. Valid values are a1 to h8");
		}
	}
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		clearScreen();
		String boardBorder = "  +---+---+---+---+---+---+---+---+";
		
		System.out.println(boardBorder);
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(pieces.length  - i + " |");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)));
			}
			System.out.println();
			System.out.println(boardBorder);
		}
		System.out.println("    a   b   c   d   e   f   g   h");
	}
	
	private static void printPiece(ChessPiece piece, Boolean fillBackground) {
		String printOut = ""; 
		if (fillBackground)	{
			printOut += ANSI_WHITE_BACKGROUND;
		}
		printOut += " ";
		
		if (piece == null) {
			printOut += ANSI_GREEN + "-";
		} else {
            if (piece.getColor() == Color.WHITE) {
            	printOut += ANSI_RED + piece;
            }
            else {
            	printOut += ANSI_BLUE + piece;
            }
		}
		System.out.print( printOut + " " + ANSI_RESET + "|");
	}
	
	
	
}
