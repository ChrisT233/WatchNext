package internship.watch.next.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
// used for generating getters and setters
@Data
public abstract class BaseEntity {
    @Id
    //we have to use generation type identity in order to correlate with the serial ID from the database table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
}
