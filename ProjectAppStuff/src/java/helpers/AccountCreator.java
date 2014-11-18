/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Damon Wolfgang Duckett
 */
public class AccountCreator {

    public final String MODERATOR_PHRASE = "sugar, spice, and everything nice";
    public final String ADMINISTRATOR_STRING = "friendship is magic";
    
    private String Username;
    private String pass;
    private String passConfirm;
    private String passPhrase;
    
    private static String staticUsername;
    private static String staticPass;
    private static String staticPassConfirm;
    private static String staticPassPhrase;
    
    public AccountCreator() {
        staticPassPhrase = "";
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and setter methods. Click on the + sign on the left to edit the code.">
    
    /**
     * @return the Username
     */
    public String getUsername() {
        return staticUsername;
    }

    /**
     * @param Username the Username to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
        staticUsername = Username;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
        staticPass = pass;
    }

    /**
     * @return the staticUsername
     */
    public static String getStaticUsername() {
        return staticUsername;
    }
    
    /**
     * @param passConfirm the passConfirm to set
     */
    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
        staticPassConfirm = passConfirm;
    }
    

    /**
     * @return the passPhrase
     */
    public String getPassPhrase() {
        return staticPassPhrase;
    }

    /**
     * @param passPhrase the passPhrase to set
     */
    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
        staticPassPhrase = passPhrase;
    }
    
    /**
     * @return the pass
     */
    public String getPass() {
        return staticPass;
    }

    /**
     * @return the passConfirm
     */
    public String getPassConfirm() {
        return staticPassConfirm;
    }

// </editor-fold>
    
    private boolean createAccount() {
        /*TODO: implement the logic to create a new User account
        and put it in the database and all of that fun stuff
        but make sure that the username is not already taken (although that is
        probably already part of the SQL stuff and you probably already knew
        that anyway)*/
        /* TAG: database stuff */
        
        /*ADDITIONAL NOTES:
        *only use the static variables to implement your logic. The non-static
        variables will not be set to what we want to test so please do not use
        them because you will cause bugs when running code if you do
        ~Thank You
        *the passPhrases are for creating Moderators and
        Administrators. If the person typed in "sugar, spice, and everything nice"
        that means the created User account should have Moderator privilages and
        if they entered in "friendship is magic" then the created User account
        should have administrator privilages. If the passPhrase was not one of these
        then return false unless they left it blank resulting in it being NULL or
        a string set as ""
        *the passPhrases are adjustable but for now that is what they are because
        I had a bet and I wan $30 from it so get over it until we ask the client
        what she wants it to be*/
        
        /*Last note: I started writing the code just a little bit to help out*/
        if(!(staticPassPhrase.compareTo("") == 0)) {
            
            if(staticPassPhrase.compareTo(MODERATOR_PHRASE) == 0) {
                /*TODO: add logic to make the user you are adding to the database
                of the Moderator subclass*/
                return true;
            }
            else if(staticPassPhrase.compareTo(ADMINISTRATOR_STRING) == 0){
                /*TODO: add logic to make the user you are adding to the database
                of the Administrator subclass*/
                return true;
            }
            else {
                return false;
            }
        }
        /*if this part of the code is reached, the User object you will be
        creating will of type User.*/
        return true;
    }

    public boolean canMake() {
        /*TODO: put in logic that will allow determine if the current Username is
        taken yet NOTE: use the staticUsername variable only in your logic!!!!!
        the non-static variable will not be set to the correct String you will
        need to use. Keep that in mind for all methods in classes within the
        helpers Java packages*/
        
        /*so if you find a User who already has the requested name just use a
        return false; statement and then the rest can be taken care with the
        simple code below that will implement the complicated code you need to
        write in the above method*/
        
        return createAccount();
    }

    
}