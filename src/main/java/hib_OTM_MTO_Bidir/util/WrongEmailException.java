package hib_OTM_MTO_Bidir.util;

public class WrongEmailException extends RuntimeException{
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Email Not Valid";
	}
}
