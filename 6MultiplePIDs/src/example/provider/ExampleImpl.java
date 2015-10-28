package example.provider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import example.api.Example;

/**
 * This is the implementation.It registers a Example service.
 */
@Component(immediate = true, name = "Example", configurationPid = {"Example.Name", "Example.Words"})
public class ExampleImpl implements Example {
	@ObjectClassDefinition(pid = "Example.Name")
	@interface Name {
		String name() default "6MultiplePIDs";
	}

	@ObjectClassDefinition(pid = "Example.Words")
	@interface Words {
		String hello() default "Hello";

		String goodbye() default "Goodbye";
	}

	private Name	name;
	private Words	words;

	@SuppressWarnings("hiding")
	@Activate
	void activate(Name name, Words words) {
		this.name = name;
		this.words = words;
		say(words.hello());
	}

	@Deactivate
	void deactivate() {
		say(words.goodbye());
	}

	@Override
	public boolean say(String message) {
		System.out.println(name.name() + ":" + message);
		return false;
	}
}
