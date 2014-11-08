
public class Token {
	
	
    final static String names[] = {
        "cmd", "exp", "var", "num", "boolop", "binop", "symbol", "error", "eol",
    };
    
    /** The different types of token */
	 final static int Cmd    = 0; // if, goto, print, := // TODO return string
	 final static int Var    = 2; // [a-z] one letter //TODO return char
	 final static int Num    = 3; // number no start with 0 //TODO return int
	 final static int BoolOp = 4; // 
	 final static int BinOp  = 5;
	 final static int Symbol = 6;
	 final static int Error  = 7;
	 final static int Eol    = 8;
	 
	 
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
	 
	 Token(int type, int numVal, int parVal) {
	     type = this.type;
         numVal = this.numVal;
         parVal = this.parVal;
     }
	 
	 Token(int type, String strVal, int parVal) {
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
