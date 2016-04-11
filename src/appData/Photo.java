package appData;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
  private String tagBeforeDeLimiting;
  private int photoId;

  public Photo(String path, Tag tag, String caption, File image){
    this.path = path;
    this.caption = caption;
    tags = new ArrayList<Tag>();
    this.tags.add(tag);
    // tagBeforeDeLimiting = tags;
    // this.tags = tokenizeTags(tags);
    cal = new GregorianCalendar();
    cal.set(Calendar.MILLISECOND, 0);
    this.date = cal.getTime();
    this.image = image;
    photoId = 1 + (int)(Math.random() * 5000); 
  }

  public static ArrayList<Tag> tokenizeTags(String preTags){

	  StringTokenizer st = new StringTokenizer(preTags, ",");

	  ArrayList<Tag> tagList = new ArrayList<Tag>();

	  while (st.hasMoreTokens()){
		  Tag temp = new Tag(st.nextToken());
		  tagList.add(temp);
	  }

	  return tagList;


  }

  public void addTag(Tag tag){
    if(!tags.contains(tag))
      this.tags.add(tag);
  }

  public void removeTag(Tag tag){
    this.tags.remove(tag);
  }

  public static ArrayList<Tag> getTags(Photo photo){

      return photo.tags;
  }

  public String getDateString(){
    DateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
    return formatedDate.format(this.date);
  }

    public String getSimpleDateString(){
    DateFormat formatedDate = new SimpleDateFormat("MMMM dd, yyyy");
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

	public int getId() {
		return photoId;
	}

	public String toString(){
		return path;
	}

	public File getPhotoFile(){
		return this.image;
	}

	public String getCaption(){
		return caption;
	}

	public String getPreTags(){
    String tagString = "";
    for(Tag tag : tags){
      tagString = tagString + tag.getValue()  + " ";
    }
    return tagString;
		//return tagBeforeDeLimiting;
	}

	public void setPreTags(String string){
		tagBeforeDeLimiting = string;
	}

	public void editCaption(String caption, User user){
		this.caption = caption;
		Admin.updateUser(user);
	}

	public void editTags(String tags, User user){
		this.tags = tokenizeTags(tags);
		Admin.updateUser(user);
	}
	
	public static ArrayList<Photo> removePhoto(ArrayList<Photo> photoList, int photoId){
		
		for (int i = 0; i < photoList.size(); i++){
			
			if (photoList.get(i).getId() == photoId){
				System.out.println("REMOVING FROM PHOTO LIST");
				photoList.remove(i);
				return photoList;
			}
			
		}
		
		return null;
		
	}


}
