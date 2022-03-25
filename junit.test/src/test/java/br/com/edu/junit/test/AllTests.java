package br.com.edu.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Edaurdo
 */
@RunWith(Suite.class)
@SuiteClasses({
	TestCaseDivisao.class, TestCaseBusiness.class
})
public class AllTests {

}
