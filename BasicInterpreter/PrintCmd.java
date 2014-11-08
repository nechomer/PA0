

public class PrintCmd extends Cmd {
	
	private Exp exp;
	
	public Exp getExp() {
		return exp;
	}

	public void setExp(Exp exp) {
		this.exp = exp;
	}

	public PrintCmd(int currentLineNumber, Lexer lex) {
		super(PRINT_CMD, currentLineNumber);
		parseCMD(this, lex);
	}
	
	private static void parseCMD(PrintCmd printCmd, Lexer lex) {
		Token token = lex.nextToken();
		if (token.getchar() != '(') {
			Parser.setErrCode(printCmd.getCurrentLineNumber(), 1);
			return;
		}
		
		token = lex.nextToken();
		if (token.getType() != Token.BinOp && token.getType() != Token.Num && token.getType() != Token.Var) {
			Parser.setErrCode(printCmd.getCurrentLineNumber(), 1);
			return;
		}
		switch (token.getType()) {
			case (Token.BinOp) : {printCmd.setExp(new BinOpExp(printCmd.getCurrentLineNumber(), lex, token.getNum())); break;}
			case (Token.Num)   : {printCmd.setExp(new NumExp(printCmd.getCurrentLineNumber(), lex)); break;}
			case (Token.Var)   : {printCmd.setExp(new VarExp(printCmd.getCurrentLineNumber(), lex)); break;}
		}
		
		token = lex.nextToken();
		if (token.getchar() != ')') {
			Parser.setErrCode(printCmd.getCurrentLineNumber(), 1);
			return;
		}
		
	}
}
