package hib_OTM_MTO_Bidir.util;

public class IdNotFoundException extends RuntimeException {

	private String message;

	public IdNotFoundException() {
	}

	public IdNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
