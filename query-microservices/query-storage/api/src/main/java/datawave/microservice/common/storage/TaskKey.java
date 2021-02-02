package datawave.microservice.common.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@XmlRootElement
public class TaskKey extends QueryKey implements Serializable {
    private static final long serialVersionUID = -2589618312956104322L;
    
    public static final String TASK_ID_PREFIX = " T:";
    
    private final UUID taskId;
    
    @JsonCreator
    public TaskKey(@JsonProperty("taskId") UUID taskId, @JsonProperty("type") QueryType type, @JsonProperty("queryId") UUID queryId) {
        super(type, queryId);
        this.taskId = taskId;
    }
    
    public TaskKey(UUID taskId, QueryKey queryKey) {
        this(taskId, queryKey.getType(), queryKey.getQueryId());
    }
    
    public UUID getTaskId() {
        return taskId;
    }
    
    public String toKey() {
        return TASK_ID_PREFIX + taskId.toString() + super.toKey();
    }
    
    @Override
    public String toString() {
        return toKey();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskKey) {
            TaskKey other = (TaskKey) o;
            return new EqualsBuilder().appendSuper(super.equals(o)).append(getTaskId(), other.getTaskId()).isEquals();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(getType()).append(getQueryId()).toHashCode();
    }
}
