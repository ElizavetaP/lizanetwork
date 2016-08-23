package org.github.Elizaveta.hello;

public class Photo {
    private int photoID;
    private int albumID;
    private String photoName;
    private int id;

    public Photo(int photoID, int albumID, String photoName, int id) {
        this.photoID = photoID;
        this.albumID = albumID;
        this.photoName = photoName;
        this.id = id;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (photoID != photo.photoID) return false;
        if (albumID != photo.albumID) return false;
        if (id != photo.id) return false;
        return photoName != null ? photoName.equals(photo.photoName) : photo.photoName == null;

    }

    @Override
    public int hashCode() {
        int result = photoID;
        result = 31 * result + albumID;
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
