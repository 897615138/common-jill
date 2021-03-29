package jill.common.util.yml.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jill W
 * @date 2021/03/24
 */
@Data
@Builder

public class ControllerMethod {
    private String key;
    private String code;
    private String name;
    private String extraFormat;
    private String extraArgs;
    private String handler;
}
