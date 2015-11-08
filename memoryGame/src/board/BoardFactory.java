package board;

public class BoardFactory {

	public Board getBoard(String boardDifficultLevel) {
		
		if(boardDifficultLevel == null) {
			return null;
		}
		
		if(boardDifficultLevel.equalsIgnoreCase("easy")) {
			//TODO easy level board implementation
		}
		
		else if(boardDifficultLevel.equalsIgnoreCase("normal")) {
			return new NormalBoard();
		}
		
		else if (boardDifficultLevel.equalsIgnoreCase("hard")) {
			//TODO hard level board implementation
		}
		
		return null;
	}
}
