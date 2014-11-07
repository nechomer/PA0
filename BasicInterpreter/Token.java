
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
