package appData;
import java.io.Serializable;

public class Tag implements Serializable  {

  private String type;
  private String value;
  private String defaultType = "Misc";



  /**
   * Creates a new tag given a type of tag and the value for it.
   * @param type - a type of a tag, such as Location, People, etc.
   * @param value - the value of the tag, such as New York, or John
   */
  public Tag(String type, String value) {
    this.type = type;
    this.value = value;
  }
  
  public Tag(String value){
	  this.value = value;
	  type = defaultType;
  }


  /* get methods */
  /**
   * returns the Tag type.
   *
   */
  public String getType(){
    return type;
  }


  /**
   * returns the Tag value.
   *
   */
  public String getValue(){
    return value;
  }


  /* set methods */
  /**
   * set the tag's type to the given string t.
   */
  public void setType(String t){
    this.type = t;
  }


  /**
   * set the tag's value to the given string s.
   */
  public void setValue(String s){
    this.value = s;
  }
  
  public String toString(){
	  return value;
  }
}
