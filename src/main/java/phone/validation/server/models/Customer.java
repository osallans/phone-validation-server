package phone.validation.server.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer{
    @Id
    private int id;
	@Column(name="name")
	private String name;
	@Column(name="phone")
    private String phone;
    
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	@Column(name="valid")
    private int valid;

	 public Customer(){

	 }
	

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public String getPhone() {
		return phone;
	}
    public Country getCountry() {
		return country;
	}
    public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
    public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + " , name=" + name +" , phone=" + phone +" , country=" + country.getName() + "]";
	}
}