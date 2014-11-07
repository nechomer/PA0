

public class BinOpExp extends Exp {
	
	public static final int ADD = 1;
	public static final int SUB = 2;
	public static final int MUL = 3;
	public static final int DIV = 4;
	
	
	public VarExp getVar1() {
		return var1;
	}

	public void setVar1(Exp var1) {
		this.var1 = var1;
	}

	public VarExp getVar2() {
		return var2;
	}

	public void setVar2(Exp var2) {
		this.var2 = var2;
	}

	public int getBinOP() {
		return binOP;
	}

	public void setBinOP(int binOP) {
		this.binOP = binOP;
	}

	Exp var1, var2;
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
		case (Token.VAR) :   binExp.setVar1(new VarExp(binExp.getCurrentLineNumber(), lex));
		case (Token.NUM) :   binExp.setVar1(new NumExp(binExp.getCurrentLineNumber(), lex));
		case (Token.BINOP) : binExp.setVar1(new BinOpExp(binExp.getCurrentLineNumber(), lex, token.getNum()));

		}
		
		Token.checkSpace(lex);
		token = lex.getNextToken();
		if (token.getType() != Token.VAR || token.getType() != Token.NUM || token.getType() != Token.BINOP) {
			Printer.PrintError(binExp.getCurrentLineNumber(), 1);
			return;
		}
		
		switch (token.getType()) {
		case (Token.VAR) :   {binExp.setVar2(new VarExp(binExp.getCurrentLineNumber(), lex));}
		case (Token.NUM) :   {binExp.setVar2(new NumExp(binExp.getCurrentLineNumber(), lex));}
		case (Token.BINOP) : {binExp.setVar2(new BinOpExp(binExp.getCurrentLineNumber(), lex, token.getNum()));}

		}
		
		Token.checkSpace(lex);

	}
	
	public int evalExp() {
		switch (binOP) {
			case (ADD) : return var1.evalExp() + var2.evalExp(); 
			case (SUB) : return var1.evalExp() - var2.evalExp(); 
			case (MUL) : return var1.evalExp() * var2.evalExp(); 
			case (DIV) : return var1.evalExp() / var2.evalExp(); 
		}
	}
}
