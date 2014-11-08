package basicInterpreter;

public class Token {
	
	
    final static String names[] = {
        "cmd", "exp", "var", "num", "boolop", "binop", "symbol", "error", "eol",
    };
    
    /** The different types of token */
<<<<<<< HEAD
	 final static int Cmd    = 0;
	 final static int Exp    = 1;
	 final static int Var    = 2;
	 final static int Num    = 3;
	 final static int BoolOp = 4;
	 final static int BinOp  = 5;
	 final static int Symbol = 6;
	 final static int Error  = 7;
	 final static int Eol    = 8;
=======
	 final static int CMD = 0;
	 final static int VAR = 2;
	 final static int NUM = 3;
	 final static int BOOLOP = 4;
	 final static int BINOP = 5;
>>>>>>> origin/master
	 
	 protected int     type;     // token's type
	 protected int 	   parVal;
	 protected String  strVal;
	 protected int     numVal;     // numeric value
	 
	 int getType () {
		 return type;
	 }
	 
	 int getNum () {
		 return numVal;
	 }
	 
	 String getStr () {
		 return strVal;
	 }
	 
	 Token(int type) {
	     type = this.type;
     }
	 
<<<<<<< HEAD
	 Token(int type, double numVal, int parVal) {
=======
	 Token(int type, int numVal) {
>>>>>>> origin/master
	     type = this.type;
         numVal = this.numVal;
         parVal = this.parVal;
     }
	 
<<<<<<< HEAD
	 Token(int type, String strVal, int parVal) {
=======
	 Token(int type, String strVal, int numVal) {
>>>>>>> origin/master
	     type = this.type;
	     strVal = this.strVal;
	     parVal = this.parVal;
     }
	 
	 
	 int typeNum() {
	     return type;
	 }
	 String typeString() {
	     return names[type];
	 }

}
