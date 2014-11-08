


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
		this.currentLineNumber = currentLineNumber;
		parseCMD(this, lex);
	}
	
	private static void parseCMD(GOTOCmd gotoCmd, Lexer lex) {
		Token token = lex.nextToken();
		if (token.getType() != Token.Num) {
			Parser.setErrCode(gotoCmd.getCurrentLineNumber(), 1);
		}
	}
}
