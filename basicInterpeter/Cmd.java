package basicInterpreter;

public abstract class Cmd {
	
	public static int IF_CMD = 1;
	public static int GOTO_CMD = 2;
	public static int PRINT_CMD = 3;
	public static int MOUNT_CMD = 4;
	
	private String type;

	public Cmd() {
		// TODO Auto-generated constructor stub
	}
	
	public String getType() {
		return type;
	}

}
