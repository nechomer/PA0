

public class BinOpExp extends Exp {
	
	public static final int ADD = 1;
	public static final int SUB = 2;
	public static final int MUL = 3;
	public static final int DIV = 4;
	
	
	public Exp getVar1() {
		return var1;
	}

	public void setVar1(Exp var1) {
		this.var1 = var1;
	}

	public Exp getVar2() {
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
		if (!Parser.checkSpace(binExp.getCurrentLineNumber(), lex))  return;
		
		Token token = lex.nextToken();
		if (token.getType() != Token.Var && token.getType() != Token.Num && token.getType() != Token.BinOp) {
			Parser.setErrCode(binExp.getCurrentLineNumber(), 1);
			return;
		}
		
		switch (token.getType()) {
		case (Token.Var) :   {binExp.setVar1(new VarExp(binExp.getCurrentLineNumber(), lex)); break; }
		case (Token.Num) :   {binExp.setVar1(new NumExp(binExp.getCurrentLineNumber(), lex)); break; }
		case (Token.BinOp) : {binExp.setVar1(new BinOpExp(binExp.getCurrentLineNumber(), lex, token.getNum())); break; }

		}
		
		if (!Parser.checkSpace(binExp.getCurrentLineNumber(), lex)) return;
		token = lex.nextToken();
		if (token.getType() != Token.Var && token.getType() != Token.Num && token.getType() != Token.BinOp) {
			Parser.setErrCode(binExp.getCurrentLineNumber(), 1);
			return;
		}
		
		switch (token.getType()) {
		case (Token.Var) :   {binExp.setVar2(new VarExp(binExp.getCurrentLineNumber(), lex)); break; }
		case (Token.Num) :   {binExp.setVar2(new NumExp(binExp.getCurrentLineNumber(), lex)); break; }
		case (Token.BinOp) : {binExp.setVar2(new BinOpExp(binExp.getCurrentLineNumber(), lex, token.getNum())); break; }

		}
		
	}
	
	public int evalExp() {
		if (valIsValid()) {
			switch (binOP) {
				case (ADD) : return var1.evalExp() + var2.evalExp(); 
				case (SUB) : return var1.evalExp() - var2.evalExp(); 
				case (MUL) : return var1.evalExp() * var2.evalExp(); 
				case (DIV) : return var1.evalExp() / var2.evalExp(); 
			}
		}
		Printer.PrintError(getCurrentLineNumber(), 4);
		System.exit(0); 
		return 0;
	}

	@Override
	public boolean valIsValid() {
		return var1.valIsValid() && var2.valIsValid();
	}
}
