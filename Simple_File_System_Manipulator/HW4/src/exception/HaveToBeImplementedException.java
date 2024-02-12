package exception;

public class HaveToBeImplementedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public HaveToBeImplementedException(String fnName) {
		super(fnName + " must be implemented by you!");
	}
}