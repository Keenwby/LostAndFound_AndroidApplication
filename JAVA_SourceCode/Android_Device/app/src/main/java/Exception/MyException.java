package Exception;

public class MyException extends Exception{
	
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private int errorno = 0;
		private String errormsg = null;
		
		//Constructors
		public MyException() {
			super();
		}
		public MyException(int errorno){
			super();
			this.errorno = errorno;
		}
		
		public MyException(Errors e) {
			super();
			this.errorno = e.getErrorno();
			this.errormsg = e.getErrormsg();
		}
		
		//Getters & Setters
		public int getErrorno() {
			return errorno;
		}
		
		public void setErrorno(int errorno) {
			this.errorno = errorno;
		}
		
		public String getErrormsg() {
			return errormsg;
		}
		
		public void setErrormsg(String errormsg) {
			this.errormsg = errormsg;
		}
		//Printers
		public String toString() {
			return "Exception: [errorno=" + errorno + ", errormsg=" + errormsg; 
		}
		
		//Fixers
		public void fix(int errno) {
		
		Fixer f = new Fixer(); 
		switch(errno){
		case 1: f.fix1();	break;
		case 2: f.fix2();	break;
		/*case 3: f1.fix3();	break;
		case 4: f1.fix4();	break;
		case 5: f1.fix5();	break;*/
			}
		}
}
