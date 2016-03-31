package appData;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Photo implements java.io.Serializable {

  private String name;
  private String user;
  private String caption;
  private Calendar calendar;
  private Date date;
  private File image;
  private ArrayList<Tag> tags;
  private ArrayList<String> albums;

  public Photo(String name, String user, String caption, String album, File image){
    this.name = name;
    this.user = user;
    this.caption = caption;
    this.tags = new ArrayList<Tag>();
    albums = new ArrayList<String>();
    albums.add(album);
    cal = new GregorianCalendar();
    cal.set(Calendar.MILLISECOND, 0);
    this.date = cal.getTime();
    this.image = image;
  }

  public void addToAlbum(String album){
    this.albums.add(album);
  }

  public void removeFromAlbum(String album){
    this.albums.remove(album);
  }

  public void addTag(Tag tag){
    this.tags.add(tag);
  }

  public void removeTag(Tag tag){
    this.tags.remove(tag);
  }

  public String getDateString(){
    DateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
    return formatedDate.format(this.date);
  }

  public void setStringDate(String date) {
    SimpleDateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
    try {
      Date d = (Date) formatedDate.parse(date);
      this.date = d;
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
