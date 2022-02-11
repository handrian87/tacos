package t.spring.tacos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoWithJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    private Date createdAt;

    @ManyToMany(targetEntity = IngredientWithJPA.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientWithJPA> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
