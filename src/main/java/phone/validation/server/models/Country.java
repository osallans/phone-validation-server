package phone.validation.server.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	private String name;
    private String code;
    private String regex;
	public Country(){}


	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getRegex() {
		return regex;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + " , name=" + name +" , code=" + code + " , regex= "+regex+"]";
	}
}