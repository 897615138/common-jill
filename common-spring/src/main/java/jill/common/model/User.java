package jill.common.model;

import lombok.Data;

/**
 * @author Jill W
 * @date 2020/12/14
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
