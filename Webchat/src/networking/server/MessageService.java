package networking.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import networking.Message;
import networking.MessageInterface;

public class MessageService extends UnicastRemoteObject implements MessageInterface{

	public MessageService() throws RemoteException{
		
	}
	
	public void push (Message message){
		
	}
	
	public Message pull (Message message){

		return message;
	}
	

}
