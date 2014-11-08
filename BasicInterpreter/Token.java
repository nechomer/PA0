
public class Token {
	
	
    final static String names[] = {
        "cmd", "var", "num", "boolop", "binop", "symbol", "error", "eol", "eof"
    };
    
    /** The different types of token */
	 final static int Cmd    = 0; 
	 final static int Var    = 1;
	 final static int Num    = 2; 
	 final static int BoolOp = 3; 
	 final static int BinOp  = 4;
	 final static int Symbol = 5;
	 final static int Error  = 6;
	 final static int Eol    = 7;
	 final static int Eof    = 8;
	 
	 
	 protected int     type;    
	 protected int 	   parVal;
	 protected String  strVal;
	 protected char    chrVal;
	 protected int  numVal; 
	 
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
	 
	 Token(int type, int numVal) {
	     type = this.type;
         numVal = this.numVal;
     }
	 Token(int type, char chrVal) {
	     type = this.type;
	     chrVal = this.chrVal;
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
