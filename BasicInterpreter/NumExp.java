

public class NumExp extends Exp {

	int val;
	
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public NumExp(int currentLineNumber, Lexer lex) {
		super(NUM_EXP, currentLineNumber);
		parseExp(this, lex);
	}
	
	private static void parseExp(NumExp numExp, Lexer lex) {
		Lexer.checkSpace(lex);
		
		Token token = lex.getNextToken();
		if (token.getType() != Token.NUM) {
			Parser.setErrCode(numExp.getCurrentLineNumber(), 1);
			return;
		}
		numExp.setVal(token.getNum());
		
		Lexer.checkSpace(lex);
		
	}

	@Override
	public int evalExp() {
		return getVal();
	}

	@Override
	public boolean valIsValid() {
		return true;
	}
	
	
}
