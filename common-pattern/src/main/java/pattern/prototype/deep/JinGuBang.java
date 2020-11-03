package pattern.prototype.deep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JillW
 * @date 2020/10/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JinGuBang implements Serializable {

    public static final String NAME = "金箍棒";
    private static final long serialVersionUID = -5052863134988511459L;
    private Integer index;
}
