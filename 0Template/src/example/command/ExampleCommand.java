package example.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
	private Example target;

	public void example(String message) {
		target.say(message);
	}

	@Reference
	void setExample(Example service) {
		this.target = service;
	}

}
