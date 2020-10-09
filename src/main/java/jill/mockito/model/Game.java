package jill.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Data
@AllArgsConstructor
@Builder
@ToString
public class Game {
    private String type;
    private int rate;
}

