package zookeeper.framework.curator.discovery;

/**
 * @author JillW
 * @date 2020/10/22
 */
public class InstanceDetails {
    private String description;
    public InstanceDetails() {
        this("");
    }
    public InstanceDetails(String description) {
        this.description = description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
