


public class GOTOCmd extends Cmd {

	private int lineNumber;
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public GOTOCmd(int currentLineNumber, Lexer lex) {
		super(GOTO_CMD, currentLineNumber);
		parseCMD(this, lex);
	}
	
	private static void parseCMD(GOTOCmd gotoCmd, Lexer lex) {
		if (!Parser.checkSpace(gotoCmd.getCurrentLineNumber(), lex)) return;
		
		Token token = lex.nextToken();
		if (token.getType() != Token.Num) {
			Parser.setErrCode(gotoCmd.getCurrentLineNumber(), 1);
			lex.nextLine();
			return;
		}
		
		gotoCmd.setLineNumber(token.getNum());
		
	}
}
