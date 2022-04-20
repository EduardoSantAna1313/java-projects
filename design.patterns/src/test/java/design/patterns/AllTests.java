package design.patterns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import design.patterns.adapter.AdapterInMemoryTest;
import design.patterns.behavioral.chain.of.responsability.ChainOfResponsabilityBillTest;
import design.patterns.bridge.BridgeTest;
import design.patterns.facede.FacadeTest;
import design.patterns.flyweight.MusicFlyweightTest;

/**
 * Class to AllTests.
 *
 * @author Eduardo
 */
@RunWith(Suite.class)
@SuiteClasses({
	AdapterInMemoryTest.class, ChainOfResponsabilityBillTest.class, BridgeTest.class, FacadeTest.class,
	MusicFlyweightTest.class
})
public class AllTests {

}
