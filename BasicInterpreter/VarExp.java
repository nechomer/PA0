

public class VarExp extends Exp {
	
	char name;
	
	public VarExp(int currentLineNumber, Lexer lex) {
		super(VAR_EXP, currentLineNumber);
		parseEXP(this, lex);
	}
	
	
}
