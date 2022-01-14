package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="feeding_types")
public class FeedingType extends BaseEntity{
    @Size(min = 5, max = 30)
    @Column(unique=true)
    String name;
    @NotBlank
    String description;
    @ManyToOne
	@JoinColumn(name = "pet_type_id")
    PetType petType;
}
