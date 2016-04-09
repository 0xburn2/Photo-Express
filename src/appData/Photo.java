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

  /**
	 * 
	 */
	private static final long serialVersionUID = -6503590946198221177L;
private String path;
  private String caption;
  private Calendar cal;
  private Date date;
  private File image;
  private ArrayList<Tag> tags;

  public Photo(String path, String tags, String caption, File image){
    this.path = path;
    this.caption = caption;
    this.tags = new ArrayList<Tag>();
    cal = new GregorianCalendar();
    cal.set(Calendar.MILLISECOND, 0);
    this.date = cal.getTime();
    this.image = image;
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
	
	public String toString(){
		return path;
	}

}
