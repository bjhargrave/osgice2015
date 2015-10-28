package example.command;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import example.api.Example;
import osgi.enroute.debug.api.Debug;

/**
 * This is the implementation. It registers the Example interface and calls it
 * through a Gogo command.
 * 
 */
@Component(service=ExampleCommand.class, property = { Debug.COMMAND_SCOPE + "=example",
		Debug.COMMAND_FUNCTION + "=example" }, name="Example.command")
public class ExampleCommand {
	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	private List<Example> targets;

	public void example(String message) {
		for (Example target : targets)
			target.say(message);
	}
}
