

public class IfCmd extends Cmd {
	
	public static final int LT = 1;
	public static final int GT = 2;
	public static final int LE = 3;
	public static final int GE = 4;
	public static final int EQ = 5;
	public static final int NEQ = 6;
	
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
		Token token = lex.nextToken();
		if (token.getStr() != "(") {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		}
		
		//checkSpace
		
		token = lex.nextToken();
		if (token.getType() != Token.Var) {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		} 
		ifCmd.setVar1(token.getStr().charAt(0));
		
		//checkSpace
		
		token = lex.nextToken();
		if (token.getType() != Token.BoolOp) {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		} 
		ifCmd.setBoolOP(token.getNum());
		
		//checkSpace
		
		token = lex.nextToken();
		if (token.getType() != Token.Var) {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		} 
		ifCmd.setVar2(token.getStr().charAt(0));
		
		token = lex.nextToken();
		if (token.getStr() != ")") {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		} 
		
		token = lex.nextToken();
		if (token.getType() != Token.Cmd || token.getType() != Token.Var) {
			Parser.setErrCode(ifCmd.getCurrentLineNumber(), 1);
		} 
		switch (token.getNum()) {
			case Cmd.GOTO_CMD : ifCmd.setNextCmd(new GOTOCmd(ifCmd.getCurrentLineNumber(), lex));
			case Cmd.IF_CMD : ifCmd.setNextCmd(new IfCmd(ifCmd.getCurrentLineNumber(), lex));;
			case Cmd.PRINT_CMD : ifCmd.setNextCmd(new PrintCmd(ifCmd.getCurrentLineNumber(), lex));;
		}
		if (token.getType() == Token.Var) {
			ifCmd.setNextCmd(new AssignCmd(ifCmd.getCurrentLineNumber(), lex, token.getStr().charAt(0)));
		}
	}
	
	public boolean evalCondition() {
		if (Main.variables.containsKey(var1) && Main.variables.containsKey(var2)) {
			switch (boolOP) {
			case (LT)  : return Main.variables.get(var1) < Main.variables.get(var2);
			case (GT)  : return Main.variables.get(var1) > Main.variables.get(var2);
			case (LE)  : return Main.variables.get(var1) <= Main.variables.get(var2);
			case (GE)  : return Main.variables.get(var1) >= Main.variables.get(var2);
			case (EQ)  : return Main.variables.get(var1) == Main.variables.get(var2);
			case (NEQ) : return Main.variables.get(var1) != Main.variables.get(var2);
			}
		} 
		Printer.PrintError(getCurrentLineNumber(), 4);
		System.exit(0);
		return false;
	}
}
