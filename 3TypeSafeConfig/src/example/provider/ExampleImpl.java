package example.provider;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import example.api.Example;

/**
 * This is the implementation.It registers a Example service.
 */
@Component(immediate=true, name="Example")
public class ExampleImpl implements Example {
	@interface Config {
		String name() default "3TypeSafeConfig";
	}

	private Config config;

	@Activate
	void activate(@SuppressWarnings("hiding") Config config) {
		this.config = config;
		say("Hello");
	}

	@Deactivate
	void deactivate() {
		say("Goodbye");
	}

	@Override
	public boolean say(String message) {
		System.out.println(config.name() + ":" + message);
		return false;
	}

}
