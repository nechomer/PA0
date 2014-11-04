
public class Token {
	
	
    final static String names[] = {
        "cmd", "exp", "var", "num", "boolop", "binop",
    };
    
    /** The different types of token */
	 final static int Cmd = 0;
	 final static int Exp = 1;
	 final static int Var = 2;
	 final static int Num = 3;
	 final static int BoolOp = 4;
	 final static int BinOp = 5;
	 
	 protected int     type;     // token's type
	 protected String  strVal;
	 protected double  numVal;     // numeric value
	 
	 int getType () {
		 return type;
	 }
	 
	 double getNum () {
		 return numVal;
	 }
	 
	 String getStr () {
		 return strVal;
	 }
	 
	 Token(int type) {
	     type = this.type;
     }
	 
	 Token(int type, double numVal) {
	     type = this.type;
         numVal = this.numVal;
     }
	 
	 Token(int type, String strVal, double numVal) {
	     type = this.type;
	     strVal = this.strVal;
         numVal = this.numVal;
     }
	 
	 
	 int typeNum() {
	     return type;
	 }
	 String typeString() {
	     return names[type];
	 }

}
