package intership.dev.contact.model;

import java.io.Serializable;

/**
 * Created by vietruyn on 21/07/2015.
 *
 * Created Constructor
 */
public class Contact implements Serializable{
    private int mAvatar;
    private String mNameContact;
    private String mDescContact;

    /**
     * Create a new user with information
     * @param mAvatar      The avatar of a user
     * @param mNameContact    The fullname of a user
     * @param mDescContact Some information of a user
     */
    public Contact(int mAvatar, String mNameContact, String mDescContact) {
        this.mAvatar = mAvatar;
        this.mNameContact = mNameContact;
        this.mDescContact = mDescContact;
    }

    public int getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(int mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getmNameContact() {
        return mNameContact;
    }

    public void setmNameContact(String mNameContact) {
        this.mNameContact = mNameContact;
    }

    public String getmDescContact() {
        return mDescContact;
    }

    public void setmDescContact(String mDescContact) {
        this.mDescContact = mDescContact;
    }
}

