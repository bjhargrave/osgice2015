package example.command;

import java.util.Collection;

import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

import osgi.enroute.debug.api.Debug;
import osgi.enroute.dto.api.DTOs;
import osgi.enroute.dto.api.RequireDTOsImplementation;

/**
 * This is the implementation.
 * 
 */
@RequireDTOsImplementation
@Component(service=ExampleCommand.class, property = { Debug.COMMAND_SCOPE + "=example",
		Debug.COMMAND_FUNCTION + "=example" }, name="Example.command")
public class ExampleCommand {
	@Reference
	private DTOs					dtos;
	@Reference
	private ServiceComponentRuntime	scr;

	public void example(Bundle... bundles) throws Exception {
		Collection<ComponentDescriptionDTO> descs = scr.getComponentDescriptionDTOs(bundles);
		for (ComponentDescriptionDTO desc : descs) {
			System.out.println(dtos.encoder(scr.getComponentConfigurationDTOs(desc)).pretty().put());
		}
	}
}
