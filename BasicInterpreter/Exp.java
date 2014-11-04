package basicInterpreter;

import BASICSyntaxError;
import BooleanExp;
import Exp;
import lexer;
import Token;

public class Exp {
	
	Exp arg1, arg2;
	Parameters.Operator op;

	int num;
	
	String str;
	
	public Exp(Exp arg1, Exp arg2, Parameters.Operator op) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.op   = op;
	}
	
	//The exp is a number
	public Exp(int num) {
		this.num = num;
	}
		
	//The exp is a string
	public Exp(String str) {
		this.str = str;
	}
	
	public boolean isStringExp() {
		return str!=null;
	}
	
	public boolean isBooleanExp() {
		return (op.compareTo(Parameters.Operator.LT) >= 0);
	}
	
	public int evalConst(Program program) {
		if (isStringExp()) return 0;
		return num;
	}
	
	public int evalBinary(Program program) {
		int arg1Val = arg1.getValue(program);
		int arg2Val = arg2.getValue(program);
		switch (op) {
		case ADD : return arg1Val + arg2Val;
		case SUB : return arg1Val - arg2Val; 
		case MUL : return arg1Val * arg2Val;
		case DIV : return arg1Val / arg2Val; 
		default  : System.err.print("Syntax error");
		}
	}
		
	
	public boolean evalBoolean(Program program) {
		int arg1Val = arg1.getValue(program);
		int arg2Val = arg2.getValue(program);
		switch (op) {
		case LT  : return arg1Val < arg2Val;
		case GT  : return arg1Val > arg2Val;
		case LE  : return arg1Val <= arg2Val;
		case GE  : return arg1Val >= arg2Val;
		case EQ  : return arg1Val == arg2Val;
		case NEQ : return arg1Val != arg2Val;
		default  : System.err.print("Syntax error");
		}
	}
	
	public static Exp ParseClause(Lexer lex) {
		Token token = lex.getNextToken();
		Exp exp;
		
		if (token.isLP()) {
			exp = parseExp(lex);
			token = lex.getNextToken();
			if (!token.isRP()) {
				lex.getPrevToken();
				/*syntax error do to parentheses*/
			}
		}
		
		
	}
    public static Exp ParseExp(lexer lex) throws BASICSyntaxError {
        Exp result;
        Token t;

        t = lt.nextToken();
    
        switch (t.op()) {
            case OP_EQ:
                result = new BooleanExp(OP_EQ, result, string(lt));
                break;
            case OP_NE:
                result = new BooleanExp(OP_NE, result, string(lt));
                break;
            case OP_LT:
                result = new BooleanExp(OP_LT, result, string(lt));
                break;
            case OP_LE:
                result = new BooleanExp(OP_LE, result, string(lt));
                break;
            case OP_GT:
                result = new BooleanExp(OP_GT, result, string(lt));
                break;
            case OP_GE:
                result = new BooleanExp(OP_GE, result, string(lt));
                break;
        }
        lt.unGetToken();
        return result;
    }
	
}
