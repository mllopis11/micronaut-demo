package mike.demo.company.organization.model;

public class Organization {

    private String uuid;
    private String name;
    private String address;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("Organization [");

        // @formatter:off
        builder.append("uuid=").append(uuid)
                .append(", name=").append(name)
                .append(", address=").append(address);
        // @formatter:on

        return builder.append("]").toString();
    }
}
