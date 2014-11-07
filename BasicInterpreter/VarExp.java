

public class VarExp extends Exp {
	
	char name;
	
	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public VarExp(int currentLineNumber, Lexer lex) {
		super(VAR_EXP, currentLineNumber);
		parseEXP(this, lex);
	}
	
	private static void parseEXP(VarExp varExp, Lexer lex) {
		Lexer.checkSpace(lex);
		
		Token token = lex.getNextToken();
	}
	
	public int evalExp() {
		return variables.get(name);
	}
}
