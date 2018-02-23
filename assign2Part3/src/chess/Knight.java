package chess;

public class Knight extends Piece{

	public Knight(ChessColour colour) {
		super(colour, ChessPieces.KNIGHT);
	}
	
	
	@Override
	public boolean isLegalMove(ChessBoard chessBoard, Coordinate src,
			Coordinate dest) {
		boolean isOccupied = super.isLegalMove(chessBoard, src, dest);
		if(!isOccupied) {
			int rowDiff = dest.getRowNumber() - src.getRowNumber();
			int columnDiff = dest.getColumnNumber() - src.getColumnNumber();
			if((checkDiff(rowDiff, 1) && checkDiff(columnDiff, 2)) || 
					(checkDiff(rowDiff, 2) && checkDiff(columnDiff, 1)))
				return true;
		}	
		return false;
	}

}
