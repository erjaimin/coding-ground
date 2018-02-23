package chess;

public class Pawn extends Piece {

	public Pawn(ChessColour colour) {
		super(colour, ChessPieces.PAWN);
	}
	
	@Override
	public boolean isLegalMove(ChessBoard chessBoard, Coordinate src,
			Coordinate dest) {
		boolean isOccupied = super.isLegalMove(chessBoard, src, dest);
		if(!isOccupied) {
			int srcRow = src.getRowNumber();
			int rowDiff = dest.getRowNumber() - srcRow;
			int columnDiff = dest.getColumnNumber() - src.getColumnNumber();
			ChessColour destColour = null;
			Square destSquare = chessBoard.getSquare(dest);
			if(destSquare != null && destSquare.getPiece() != null) {
				destColour = destSquare.getPiece().getColour();
			}
			if (ChessColour.WHITE.equals(getColour())) {
				if ((columnDiff == 1 && destColour != null
						&& ChessColour.BLACK.equals(destColour))
						|| (checkDiffForward(rowDiff, 1)
								|| (srcRow == 1 && checkDiffForward(rowDiff, 2)))) {
					return true;
				}
			} else {
				if ((columnDiff == 1 && destColour != null
						&& ChessColour.WHITE.equals(destColour))
						|| (checkDiffForward(rowDiff, -1)
								|| (srcRow == 6 && checkDiffForward(rowDiff, -2)))) {
					return true;
				}
			}
		}	
		return false;
	}

}
