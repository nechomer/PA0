package basicInterpeter;

public class Expression {
	
	Expression arg1, arg2;
	Parameters.Operator op;
	
	public Expression(Expression arg1, Expression arg2, Parameters.Operator op) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.op   = op;
	}
	
	
	public int evalBinary(Program program) {
		int arg1Val = arg1.getValue(program);
		int arg2Val = arg2.getValue(program);
		switch (op) {
		case ADD : return arg1Val + arg2Val;
		case SUB : return arg1Val - arg2Val; 
		case MUL : return arg1Val * arg2Val;
		case DIV : return arg1Val / arg2Val; 
		default : System.err.print("Syntax error");
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
		default : /*print error*/;
		}
	}
}
