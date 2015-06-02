package webchat.test.message.networking;

import webchat.message.networking.NotLoggedInException;
import junit.framework.TestCase;

public class NotLoggedInExceptionTest extends TestCase {

	public void testException(){
		try{
			throw new NotLoggedInException();
		}
		catch(NotLoggedInException e){
			
		}
	}

}
