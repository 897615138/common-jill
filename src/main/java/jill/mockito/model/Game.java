package jill.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Comparator;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Data
@AllArgsConstructor
@Builder
@ToString

public class Game {
    Comparator<Game> comparator;
    private String type;
    private int rate;
}

