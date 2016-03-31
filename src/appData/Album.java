package appData;

public class Album implements java.io.Serializable {

  private String name;
  private String user;
  private ArrayList<Photo> photos;

  public Album(String name, String user){
    this.name = name;
    this.user = user;
    this.photos = new ArrayList<Photo>();
  }

  public void renameAlbum(String name){
    this.name = name;
  }

  public void addPhoto(Photo photo){
    photos.add(photo);
  }

  public void deletePhoto(int photoId){
    for (Photo photo : photos) {
        if (photo.getId().equals(photoId)) { // need album name in Album.java
            photos.remove(photo);
        }
    }
  }
}
