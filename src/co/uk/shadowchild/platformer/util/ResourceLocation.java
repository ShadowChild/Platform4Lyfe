package co.uk.shadowchild.platformer.util;

/**
 * @author ShadowChild
 */
public class ResourceLocation {

    private String resourceType;
    private String location;

    public ResourceLocation(String resource) {

        String resourceType;
        String location;

        if(resource.contains(":")) {

            resourceType = resource.substring(0, resource.indexOf(":"));
            location = resource.substring(resource.indexOf(":") + 1);
        } else {

            resourceType = "textures";
            location = resource;
        }
        this.resourceType = resourceType;
        this.location = "/assets/" + resourceType + "/" + location;
    }

    public ResourceLocation(String resourceType, String location) {

        this(resourceType + ":" + location);
    }

    public String getLocation() {

        return location;
    }
}
