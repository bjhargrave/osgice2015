package example.provider;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import example.api.Example;

/**
 * This is the implementation.It registers a Example service.
 */
@Component(immediate = true, name = "Example1", property = "name=2MultipleFieldRef_1")
public class ExampleImpl1 implements Example {
	private String name;

	@Activate
	void activate(Map<String, Object> map) {
		name = map.containsKey("name") ? (String) map.get("name") : "World1";
		say("Hello");
	}

	@Deactivate
	void deactivate(Map<String, Object> map) {
		say("Goodbye");
	}

	@Override
	public boolean say(String message) {
		System.out.println(name + ":"  + message);
		return false;
	}

}
