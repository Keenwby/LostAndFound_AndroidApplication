package ServerSocket;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Adapter.EntityEditor;
import Adapter.PackageEntityfromDB;
import Adapter.UnpackageEntitytoDB;
import DataBase.DBInsert;
import Entities.Category;
import Entities.User;

public class DefaultSocketServer extends Thread implements SocketInterface,
SocketClientConstants{
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
    private ServerSocket serversock;
    private Socket sock;
    private String strHost;
    private int iPort;
    

    public DefaultSocketServer(String strHost, int iPort) { 
    	this.strHost = strHost;
    	this.iPort = iPort;
    	
    }//constructor

    public void run(){
       if (openConnection()){
          handleSession();
          closeSession();
       }
    }//run
    
    public boolean openConnection(){    	
    	   //create a new socket
    	   try {
    	     serversock = new ServerSocket(iPort);  
    	     System.out.println("Open host: " + strHost + " " +
                     "Open port: " + iPort);
    	   }
    	   catch(IOException socketError){
    	     System.err.println
    	        ("Unable to listen to Port: " + iPort);
    	     return false;
    	   }
    	   //ServerSocket accept
    	   //serverAccept();	   
    	  return serversock!=null ? true : false;
    	}
    
    public boolean serverAccept(){
    	try {
    			sock = serversock.accept();
    			oos = new ObjectOutputStream(sock.getOutputStream());
                ois = new ObjectInputStream(sock.getInputStream());
                
    		System.out.println("Accept succeeded.");
    		} catch (IOException e) {
    		System.err.println("Accept failed.");
    		System.exit(1); 
    		}
    	return sock!=null ? true : false;
    }
    
    //Server get an object from Client
    public Object getObject() throws IOException{
    	Object propobject = null;
       	try {
			propobject = ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return propobject;
    }
    //Server get a command from Client
    public int getCommand() throws IOException{
    	String get = null;
    	get = (String) getObject();
    	
    	return Integer.valueOf(get);
    }
    
    //Server send an object to Client
    public void sendObject(Object propsobject) throws IOException{
    	if (propsobject == null)
    		return;
		oos.writeObject(propsobject);
		oos.flush();
    }
    //Server send a command to Client
    public void sendCommand(int command) throws IOException{
    	
    	sendObject(Integer.toString(command));
    	System.out.println("Command " + command + " sent!");
    	oos.flush();
    }
    
    //Close Session
    public void closeSession(){
		try {
			ois = null;
			oos = null;
			sock.close();
		}
		catch (IOException e){
			System.err.println
			("Error closing socket to " + strHost);
   }       
}
    
    public void handleSession(){
    	System.out.println ("Server: handling session with "
    	            + strHost + ":" + iPort);
    	  try {
    		  
    	    while(true){
    	    	//Get an command
    	    	System.out.println("Server: Waiting...");
    	    	
    	    	if(serverAccept()==true){
    	    	
    	    	int command = getCommand();
    	    	
    	    	switch(command){
    	    	
    	    	case STOP://Stop the server
    	    		System.out.println("Command " + command + " received!");
    	    		return;
    	    	
    	    	case NEWUSER:
    	    		System.out.println("Command " + command + " received!");
    	    		User user = (User) getObject();
    	    		if(!user.equals(null)){
    	    			System.out.println("New User get!");
    	    			UnpackageEntitytoDB unuser = new EntityEditor();
    	    			unuser.unpackageUser(user);
    	    			sendCommand(SAVE_SUCCESS);
    	    		}
    	    		else{
    	    			System.out.println("Please upload again!");
    	    			sendCommand(SAVE_FAIL);
    	    		}
    	    		closeSocket();
    	    		break;
    	    	
    	    	case CHECKUSER:
    	    		System.out.println("Command " + command + " received!");
    	    		User checkuser = (User) getObject();
    	    		int result = CHECKUSER;
    	    		if(!checkuser.equals(null)){
    	    			System.out.println("Check User get!");
    	    			UnpackageEntitytoDB unuser = new EntityEditor();
    	    			result = unuser.checkUser(checkuser);
    	    			if(result == DBInsert.NOTFOUND)
    	    				sendCommand(SAVE_FAIL);
    	    			else if(result == DBInsert.FAIL){
    	    				sendCommand(CHECK_FAIL);
    	    			}else if(result == DBInsert.PASS){
    	    				sendCommand(CHECK_PASS);
    	    			}
    	    		}
    	    		else{
    	    			System.out.println("Please upload again!");
    	    			sendCommand(SAVE_FAIL);
    	    		}
    	    		closeSocket();
    	    		break;
    	    		
    	    	case FINDERUPLOAD:
    	    		System.out.println("Command " + command + " received!");
    	    		Category newcat = (Category) getObject();
    	    		if(!newcat.equals(null)){
    	    			System.out.println("New Category get!");
    	    			UnpackageEntitytoDB uncat = new EntityEditor();
    	    			uncat.unpackageCategory(newcat);
    	    			sendCommand(SAVE_SUCCESS);
    	    		}
    	    		else{
    	    			System.out.println("Please upload again!");
    	    			sendCommand(SAVE_FAIL);
    	    		}
    	    		closeSocket();
    	    		break;
    	    	
    	    	case OWNERCATEGORYLIST:
    	    		System.out.println("Command " + command + " received!");
    	    		Category catName = (Category) getObject();
    	    		PackageEntityfromDB pkList = new EntityEditor();
    	    		Category catList = pkList.packageItemList(catName);
    	    		sendObject(catList);
    	    		System.out.println("List sent!");
    	    		closeSocket();
    	    		break;
    	    	
    	    	case OWNERDOWNLOAD:
    	    		System.out.println("Command " + command + " received!");
    	    		Category locAndTime = (Category) getObject();
    	    		PackageEntityfromDB pkCat = new EntityEditor();
    	    		Category cat = pkCat.packageCategory(locAndTime);
    	    		sendObject(cat);	
    	    		System.out.println("Category sent");
    	    		closeSocket();
    	    		break;
    	    		
    	    	case OWNERANSWER:
    	    		System.out.println("Command " + command + " received!");
    	    		Category catans = (Category) getObject();
    	    		if(!catans.equals(null)){
    	    			System.out.println("New Answer get!");
    	    			UnpackageEntitytoDB unAns = new EntityEditor();
    	    			unAns.unpackageAnswer(catans);
    	    			sendCommand(SAVE_SUCCESS);
    	    		}
    	    		else{
    	    			System.out.println("Please upload again!");
    	    			sendCommand(SAVE_FAIL);
    	    		}
    	    		closeSocket();
    	    		break;
    	    		
    	    	case FINDERCHECK:
    	    		System.out.println("Command " + command + " received!");
    	    		Category checkCat = (Category) getObject();
    	    		PackageEntityfromDB check = new EntityEditor();
    	    		Category answer = check.packageAnswer(checkCat);
    	    		sendObject(answer);
    	    		
    	    		System.out.println("Answer sent!");
    	    		closeSocket();
    	    		break;
    	    	
    	    	case FINDERRESULT:
    	    		System.out.println("Command " + command + " received!");
    	    		int res = getCommand();
    	    		Category returnCat = (Category) getObject();
    	    		UnpackageEntitytoDB unRes = new EntityEditor();
    	    		if(res == CHECK_PASS)
        	    		unRes.insertRes(returnCat, true);
    	    		else
    	    			unRes.insertRes(returnCat, false);
    	    		System.out.println("Result " + res + " get!");
    	    		closeSocket();
    	    		break;
 
    	    	case OWNRESULT:
    	    		System.out.println("Command " + command + " received!");
    	    		Category ansCat = (Category) getObject();
    	    		UnpackageEntitytoDB getRes = new EntityEditor();
    	    		if(getRes.getResult(ansCat) == 1){
    	    			sendCommand(CHECK_PASS);
    	    			System.out.println("Command " + CHECK_PASS + " send!");
    	    		}else{
    	    			sendCommand(CHECK_FAIL);
    	    			System.out.println("Command " + CHECK_FAIL + " send!");
    	    		}
    	    		PackageEntityfromDB finalCat = new EntityEditor();
    	    		ansCat = finalCat.packageCategorywithContactInfo(ansCat);
    	    		sendObject(ansCat);
    	    		closeSocket();
    	    		break;
    	    		
    	    	case UPDATEUSER:
    	    		System.out.println("Command " + command + " received!");
    	    		User updateUser = (User) getObject();
    	    		if(!updateUser.equals(null)){
    	    			System.out.println("updateUser User get!");
    	    			UnpackageEntitytoDB unuser = new EntityEditor();
    	    			unuser.updateUser(updateUser);
    	    			sendCommand(SAVE_SUCCESS);
    	    		}
    	    		else{
    	    			System.out.println("Please upload again!");
    	    			sendCommand(SAVE_FAIL);
    	    		}
    	    		closeSocket();
    	    		break;
    	    	}
    	    	}
    	    }
    	 
    	  }
    	  catch (IOException e){
    		  e.printStackTrace();
    	}       
    }
   
	//Build an auto from properties file
    public void buildCategory() throws IOException{
      	   	
    }
    
    public void closeSocket(){
		try {
			ois.close();
			oos.close();
			sock.close();
		}
		catch (IOException e){
			System.err.println
			("Error closing socket to " + strHost);
   }   
}
}

