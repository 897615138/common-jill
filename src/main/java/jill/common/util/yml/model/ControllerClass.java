package jill.common.util.yml.model;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;

/**
 * @author Jill W
 * @date 2021/03/24
 */
@Data
@Builder
public class ControllerClass {
    private String className;
    private String appCode;
    private String appName;
    private LinkedHashMap<String, ControllerMethod> methods;
}
