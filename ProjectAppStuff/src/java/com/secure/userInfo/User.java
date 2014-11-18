package com.secure.userInfo;

import com.others.App;

public class User {


	private String name;
	private String password;
	private final boolean admin;
        private boolean moderator;
	

	//constructor for guests only.
	public User() {
		this.name = "Guest";
		this.password = "";
		this.admin = false;
                this.moderator = false;
	}

	public User(String name, String password, boolean admin, boolean moderator){
		this.name = name;
		this.password = password;
		this.admin = admin;
                this.moderator = moderator;
	}
        
         // <editor-fold defaultstate="collapsed" desc="Getters and setters. Click on the + sign on the left to edit the code.">
	public boolean isAdmin() {
		return this.admin;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	
	public void setName(String name) {
		this.name = name;
                //TODO: make sure no one else is using the name before setting the name
	}
	
	public void setPassword(String password, String currentPassword) {
		if ((this.password).compareTo(currentPassword) != 0) {
			throw new IllegalArgumentException("Authentication failed");
		}
		this.password = password;
	}
        
        public boolean isModerator() {
            return moderator;
        }// </editor-fold>
        
        public void postComment(App app) {
            /* TODO: implement the logic to add a comment to the Forum object
             * of an App's page here so all user objects can use it*/
        }
        
	/*methods for the Users
	 * post (all User objects except for Guests can do this)
	 * request app (all Users except Guest and possibly Administrator can do this)
	 * List to be continued*/
}