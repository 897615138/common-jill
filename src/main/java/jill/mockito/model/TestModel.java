package jill.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
    private String name;
    private Integer id;
    private Date birthday;
}
