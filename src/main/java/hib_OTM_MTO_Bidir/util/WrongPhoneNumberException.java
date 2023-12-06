package hib_OTM_MTO_Bidir.util;

public class WrongPhoneNumberException  extends RuntimeException{
@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return "Phone Number is Not Valid";
}
	
}
