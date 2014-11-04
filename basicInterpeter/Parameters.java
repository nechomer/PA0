package basicInterpeter;


public class Parameters {
	public enum Operator {
		ADD(1), SUB(2), MUL(3), DIV(4), 
		LT(5), GT(6), LE(7), GE(8), EQ(9), NEQ(10); 
		
		private final int op;
		
		Operator(int op) {
			this.op = op;
		}
		
		public int op() { return op; }
	}
	
	public static void PrintError(int line , int code) {
		System.out.println ("Error! Line:"+line+" Code:"+code);
	}
	
	public static void Print( int val ) {
		System.out.println (""+val);
	}
}
