/*
 * COPYRIGHT...
 */
package design.patterns.estructural.adapter.core.hexagonal.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class to User entity.
 *
 * @author Eduardo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String email;

	private String password;

}
