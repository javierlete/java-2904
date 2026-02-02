package com.ipartek.formacion.ejemplos.ipartube.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "CHAR(9)")
	@Pattern(regexp = "[\\dXYZ]\\d{7}[A-Z]", message = "debe tener un formato de 8 dígitos y una letra o letra, 7 dígitos y otra letra")
	private String dni;
	
	@NotBlank
	@Size(max = 25)
	private String nombre;
	
	@Email
	@NotBlank
	@Size(max = 255)
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	@Pattern(regexp = ".{3,}", message = "debe tener como mínimo tres caracteres")
	private String password;
	
	@Column(name = "fecha_nacimiento")
	@PastOrPresent
	private LocalDate fechaNacimiento;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "propietario")
	private Collection<Video> videos;
	
	public Optional<Integer> getEdad() {
		if(fechaNacimiento == null) {
			return Optional.empty();
		}
		
		return Optional.of(Period.between(fechaNacimiento, LocalDate.now()).getYears());
	}
}
