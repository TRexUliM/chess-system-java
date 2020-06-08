package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
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
	
	public static final String ANSI_BLINK = "\u001B[5m";
	public static final String ANSI_RAPID_BLINK = "\u001B[6m";
	
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
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("Turn: " + chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		} else {
			System.out.println("CHECKMATE");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
	}
	
	
	public static void printBoard(ChessPiece[][] pieces) {
		clearScreen();
		String boardBorder = "  +---+---+---+---+---+---+---+---+";
		
		System.out.println(boardBorder);
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(pieces.length  - i + " |");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)), false);
			}
			System.out.println();
			System.out.println(boardBorder);
		}
		System.out.println("    a   b   c   d   e   f   g   h");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		clearScreen();
		String boardBorder = "  +---+---+---+---+---+---+---+---+";
		
		System.out.println(boardBorder);
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(pieces.length  - i + " |");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)), possibleMoves[i][j]);
			}
			System.out.println();
			System.out.println(boardBorder);
		}
		System.out.println("    a   b   c   d   e   f   g   h");
	}
	
	private static void printPiece(ChessPiece piece, Boolean fillBackground, Boolean showPossibleMove) {
		String printOut = ""; 

		if (showPossibleMove) {
			System.out.print(ANSI_BLINK);
		}

		if (fillBackground)	{
			printOut += ANSI_WHITE_BACKGROUND;
		}
		
		if (showPossibleMove) {
			printOut += "(";
		} else {
			printOut += " ";
		}
		
		if (piece == null) {
			printOut += ANSI_GREEN + "-" + ANSI_RESET;
		} else {
            if (piece.getColor() == Color.WHITE) {
            	printOut += ANSI_RED + piece + ANSI_RESET;
            }
            else {
            	printOut += ANSI_BLUE + piece + ANSI_RESET;
            }
		}
		
		if (fillBackground)	{
			printOut += ANSI_WHITE_BACKGROUND;
		}
		
		if (showPossibleMove) {
			printOut += ANSI_BLINK + ")";
		} else {
			printOut += " ";
		}
		System.out.print( printOut + ANSI_RESET + "|");
	}
	
	private static void printCapturedPieces (List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured Pieces: ");
		System.out.print("White: ");
		System.out.print(ANSI_BLUE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Black: ");
		System.out.print(ANSI_RED);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}
	
	
}
