/*
 * COPYRIGHT...
 */
package br.com.edu.java.proxy.db.dao.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * SqlDAO annotation.
 *
 * @author Eduardo
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface SqlDAO {

	String value();

}
