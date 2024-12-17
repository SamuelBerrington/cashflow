package cashflow.demo.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Stock extends Asset {

}

