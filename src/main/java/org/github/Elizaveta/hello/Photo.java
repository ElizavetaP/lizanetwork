package org.github.Elizaveta.hello;

public class Photo {
    private int photo_id;
    private int album_id;
    private String photo_name;
    private int ID;

    public Photo(int photo_id, int album_id, String photo_name, int ID) {
        this.photo_id = photo_id;
        this.album_id = album_id;
        this.photo_name = photo_name;
        this.ID = ID;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (photo_id != photo.photo_id) return false;
        if (album_id != photo.album_id) return false;
        if (ID != photo.ID) return false;
        return photo_name != null ? photo_name.equals(photo.photo_name) : photo.photo_name == null;

    }

    @Override
    public int hashCode() {
        int result = photo_id;
        result = 31 * result + album_id;
        result = 31 * result + (photo_name != null ? photo_name.hashCode() : 0);
        result = 31 * result + ID;
        return result;
    }
}
