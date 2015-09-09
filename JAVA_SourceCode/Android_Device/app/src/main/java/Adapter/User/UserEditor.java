package Adapter.User;

import Adapter.FixException;
import Adapter.proxyUserEditor;
import Entities.User;

public class UserEditor extends proxyUserEditor implements EditUserDB, CheckUser, CreateUser, FixException{

}
