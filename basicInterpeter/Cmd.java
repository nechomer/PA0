package basicInterpreter;

public abstract class Cmd {
	
	public static String IF_CMD = "IF";
	public static String GOTO_CMD = "GOTO";
	public static String PRINT_CMD = "PRINT";
	public static String MOUNT_CMD = "MOUNT";
	
	private String type;

	public Cmd() {
		// TODO Auto-generated constructor stub
	}
	
	public String getType() {
		return type;
	}

}
