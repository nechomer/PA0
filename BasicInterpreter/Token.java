
public class Token {
	
	
    final static String names[] = {
        "cmd", "exp", "var", "num", "boolop", "binop",
    };
    
    /** The different types of token */
	 final static int CMD = 0;
	 final static int VAR = 2;
	 final static int NUM = 3;
	 final static int BOOLOP = 4;
	 final static int BINOP = 5;
	 
	 protected int     type;     // token's type
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
	 
	 Token(int type, int numVal) {
	     type = this.type;
         numVal = this.numVal;
     }
	 
	 Token(int type, String strVal, int numVal) {
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
