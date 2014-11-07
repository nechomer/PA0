

public class BinOpExp extends Exp {
	
	public static final int ADD = 1;
	public static final int SUB = 2;
	public static final int MUL = 3;
	public static final int DIV = 4;
	
	
	public VarExp getVar1() {
		return var1;
	}

	public void setVar1(VarExp var1) {
		this.var1 = var1;
	}

	public VarExp getVar2() {
		return var2;
	}

	public void setVar2(VarExp var2) {
		this.var2 = var2;
	}

	public int getBinOP() {
		return binOP;
	}

	public void setBinOP(int binOP) {
		this.binOP = binOP;
	}

	VarExp var1, var2;
	int binOP;
	
	public BinOpExp(int currentLineNumber, Lexer lex, int binOp) {
		super(BINARY_EXP, currentLineNumber);
		this.setBinOP(binOp);
		parseEXP(this, lex);
	}
	
	private static void parseEXP(BinOpExp binExp, Lexer lex) {
		Token.checkSpace(lex);
		Token token = lex.getNextToken();
		if (token.getType() != Token.VAR || token.getType() != Token.NUM || token.getType() != Token.BINOP) {
			Printer.PrintError(binExp.getCurrentLineNumber(), 1);
			return;
		}
		switch (token.getType()) {
		case (Token.VAR) : {binExp.setVar1(new VarExp(getCurrentLineNumber(), lex));}
		}
		token = lex.getNextToken();
		
	}
}
