package tacos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {
	
	@Id
	private String id;
	private String name;
	@Enumerated(EnumType.STRING) private Type type;
  

    public Ingredient(String id, String name, Type type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}