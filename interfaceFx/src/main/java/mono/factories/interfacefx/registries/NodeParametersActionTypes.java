package mono.factories.interfacefx.registries;

import mono.factories.registries.id.Identifier;

public class NodeParametersActionTypes {
    public static final Identifier
    UPDATE = new Identifier("update"),
    CHANGE_DATA = new Identifier("change_data"),
    ADD_TO_COMPONENT = new Identifier("add_in_component"),
    REMOVE_FROM_COMPONENT = new Identifier("remove_from_component"),
    COMPONENT_ADD_IN_SCREEN = new Identifier("component_add_in_screen"),
    COMPONENT_REMOVE_FROM_SCREEN = new Identifier("component_remove_from_screen")
    ;
}
