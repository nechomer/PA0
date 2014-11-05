package basicInterpreter;

public class IfCmd extends Cmd {
	
	private char var1, var2;
	private int boolOP;
	private Cmd nextCmd;
	
	public char getVar1() {
		return var1;
	}

	public void setVar1(char var1) {
		this.var1 = var1;
	}

	public char getVar2() {
		return var2;
	}

	public void setVar2(char var2) {
		this.var2 = var2;
	}

	public int getBoolOP() {
		return boolOP;
	}

	public void setBoolOP(int boolOP) {
		this.boolOP = boolOP;
	}

	public Cmd getNextCmd() {
		return nextCmd;
	}

	public void setNextCmd(Cmd nextCmd) {
		this.nextCmd = nextCmd;
	}
	
	public IfCmd(int currentLineNumber, Lexer lex) {
		super(IF_CMD, currentLineNumber);
		parseCMD(this, lex);
	}
	
	private static void parseCMD(IfCmd ifCmd, Lexer lex) {
		Token token = lex.getNextToken();
		if (token.getStr() != "(") {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		token = lex.getNextToken();
		if (token.getType() != Token.Var) {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		ifCmd.setVar1(token.getStr().charAt(0));
		
		token = lex.getNextToken();
		if (token.getType() != Token.BoolOp) {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		ifCmd.setBoolOP(token.getNum());
		
		token = lex.getNextToken();
		if (token.getType() != Token.Var) {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		ifCmd.setVar2(token.getStr().charAt(0));
		
		token = lex.getNextToken();
		if (token.getStr() != ")") {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		
		token = lex.getNextToken();
		if (token.getType() != Token.Cmd) {
			Printer.PrintError(ifCmd.getCurrentLineNumber(), 1);
			return;
		} 
		switch (token.getNum()) {
			case Cmd.GOTO_CMD : ifCmd.setNextCmd(new GOTOCmd(ifCmd.getCurrentLineNumber(), lex));
			case Cmd.IF_CMD : ifCmd.setNextCmd(new IfCmd(ifCmd.getCurrentLineNumber(), lex));;
			case Cmd.PRINT_CMD : ifCmd.setNextCmd(new PrintCmd(ifCmd.getCurrentLineNumber(), lex));;
			case Cmd.ASSIGN_CMD : ifCmd.setNextCmd(new AssignCmd(ifCmd.getCurrentLineNumber(), lex));;
		}
	}
}
