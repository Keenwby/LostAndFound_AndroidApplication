package Exception;

public enum Errors {
	
	NO1(1, "User not found!"),
	NO2(2, "Image not found");
	
	private final int errorno;
	private final String errormsg;
	private Errors(int errorno, String errormsg){
		this.errorno = errorno;
		this.errormsg = errormsg;
	}
	public int getErrorno(){
		return errorno;
	}
	public String getErrormsg(){
		return errormsg;
	}
}
