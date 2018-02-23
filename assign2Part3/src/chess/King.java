package chess;

public class King extends Piece {

	public King(ChessColour colour) {
		super(colour, ChessPieces.KING);
	}

	@Override
	public boolean isLegalMove(ChessBoard chessBoard, Coordinate src,
			Coordinate dest) {
		boolean isOccupied = super.isLegalMove(chessBoard, src, dest);
		if(!isOccupied) {
			int rowDiff = dest.getRowNumber() - src.getRowNumber();
			int columnDiff = dest.getColumnNumber() - src.getColumnNumber();
			if(checkDiff(rowDiff, 1) || checkDiff(columnDiff, 1))
				return true;
		}	
		return false;
	}
}
