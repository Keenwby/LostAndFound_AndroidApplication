package ServerSocket;

public interface SocketClientConstants {
    
	final static int IPORT = 9008;

	int DEFAULT = 0;
	
    int NEWUSER = 1;
    
    int CHECKUSER = 2;

    int FINDERUPLOAD = 3;

    int OWNERCATEGORYLIST = 4;
    
    int OWNERDOWNLOAD = 5;
    
    int OWNERANSWER = 6;
    
    int FINDERCHECK = 7;
    
    int FINDERRESULT = 8;
    
    int OWNRESULT = 9;
    
    int UPDATEUSER = 10;

    //Stop the socket
    int STOP = -1;
    
    //Save success
    int SAVE_SUCCESS = 99;
    int SAVE_FAIL = -99;
    
    //Check status
    int CHECK_PASS = 88;
    int CHECK_FAIL = -88;
}
