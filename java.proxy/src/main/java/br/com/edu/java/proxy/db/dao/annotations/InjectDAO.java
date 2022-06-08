/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.db.dao.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to InjectDAO.
 *
 * @author Eduardo
 */
@Retention(RUNTIME)
@Target({
	TYPE, FIELD
})
public @interface InjectDAO {

}
