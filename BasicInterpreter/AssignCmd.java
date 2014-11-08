

public class AssignCmd extends Cmd {

	char var;
	
	public char getVar() {
		return var;
	}

	public void setVar(char var) {
		this.var = var;
	}

	public Exp getExp() {
		return exp;
	}

	public void setExp(Exp exp) {
		this.exp = exp;
	}

	Exp exp;
	
	public AssignCmd(int currentLineNumber, Lexer lex, char var) {
		super(ASSIGN_CMD, currentLineNumber);
		this.var = var;
		parseCMD(this, lex);
	}
	
	private static void parseCMD(AssignCmd assignCmd, Lexer lex) {
		Lexer.checkSpace(lex);
				
		Token token = lex.getNextToken();
		if (token.getStr() != ":=") {
			Parser.setErrCode(assignCmd.getCurrentLineNumber(), 1);
		}
		
		Lexer.checkSpace(lex);
		
		token = lex.getNextToken();
		if (token.getType() != Token.BINOP || token.getType() != Token.NUM || token.getType() != Token.VAR) {
			Parser.setErrCode(assignCmd.getCurrentLineNumber(), 1);
		}
		switch (token.getType()) {
			case Token.BINOP : assignCmd.setExp(new BinOpExp(assignCmd.getCurrentLineNumber(), lex, token.typeNum()));
			case Token.NUM : assignCmd.setExp(new NumExp(assignCmd.getCurrentLineNumber(), lex));
			case Token.VAR : assignCmd.setExp(new VarExp(assignCmd.getCurrentLineNumber(), lex));
		}
		
		token = lex.getNextToken();
		if (token.getType() != Token.EOL) {
			Parser.setErrCode(assignCmd.getCurrentLineNumber(), 1);
		}
	}

}
