package basicInterpreter;

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
	
	public AssignCmd(int currentLineNumber, Lexer lex) {
		super(ASSIGN_CMD, currentLineNumber);
		parseCMD(this, lex);
	}
	
	private static void parseCMD(AssignCmd assignCmd, Lexer lex) {
		Token token = lex.getNextToken();
		if (token.getType() != Token.Var) {
			Printer.PrintError(assignCmd.getCurrentLineNumber(), 1);
			return;
		}
		assignCmd.setVar(token.getStr().charAt(0));
		
		token = lex.getNextToken();
		if (token.getStr() != ":=") {
			Printer.PrintError(assignCmd.getCurrentLineNumber(), 1);
			return;
		}
		
		token = lex.getNextToken();
		if (token.getType() != Token.BinOp || token.getType() != Token.Num || token.getType() != Token.Var) {
			Printer.PrintError(assignCmd.getCurrentLineNumber(), 1);
			return;
		}
		switch (token.getType()) {
			case Token.BinOp : assignCmd.setExp(new BinExp(lex));
			case Token.Num : assignCmd.setExp(new NumExp(lex));
			case Token.Var : assignCmd.setExp(new VarExp(lex));
		}
		
		token = lex.getNextToken();
		if (token.getType() != EOL) {
			Printer.PrintError(assignCmd.getCurrentLineNumber(), 1);
			return;
		}
	}

}
