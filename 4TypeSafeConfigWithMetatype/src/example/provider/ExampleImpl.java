package example.provider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import example.api.Example;

/**
 * This is the implementation.It registers a Example service.
 */
@Designate(ocd = ExampleImpl.Config.class)
@Component(immediate=true, name="Example")
public class ExampleImpl implements Example {
	@ObjectClassDefinition
	@interface Config {
		String name() default "4TypeSafeConfigWithMetatype";

		String hello() default "Hello";

		String goodbye() default "Goodbye";
	}

	private Config config;

	@Activate
	void activate(@SuppressWarnings("hiding") Config config) {
		this.config = config;
		say(config.hello());
	}

	@Deactivate
	void deactivate() {
		say(config.goodbye());
	}

	@Override
	public boolean say(String message) {
		System.out.println(config.name() + ":" + message);
		return false;
	}

}
