package org.github.Elizaveta.hello;

public class Friendship {
    private Integer ID;
    private Integer ID_otheruser;

    public Friendship(Integer ID, Integer ID_otheruser) {
        this.ID = ID;
        this.ID_otheruser = ID_otheruser;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getID_otheruser() {
        return ID_otheruser;
    }
}
