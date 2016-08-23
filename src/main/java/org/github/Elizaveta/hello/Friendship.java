package org.github.Elizaveta.hello;

public class Friendship {
    private Integer id;
    private Integer idOtheruser;

    public Friendship(Integer ID, Integer otherUserID) {
        this.id = ID;
        this.idOtheruser = otherUserID;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdOtheruser() {
        return idOtheruser;
    }
}
