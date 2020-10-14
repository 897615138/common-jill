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
    private String type;
    private int rate;

    Comparator<Game> comparator = new Comparator<Game>(){

        @Override
        public int compare(Game o1, Game o2) {
            return 0;
        }
    };
}

