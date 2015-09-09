package Adapter.User;

import Entities.User;

public interface CreateUser {

	public User createUser(String name, String pw, String contact, String ip);

}
