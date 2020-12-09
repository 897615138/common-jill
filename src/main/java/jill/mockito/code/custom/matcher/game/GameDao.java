package jill.mockito.code.custom.matcher.game;

import jill.mockito.model.Game;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Slf4j
public class GameDao {
    public void addRate(Game game) {
        log.info(game.toString());
        log.info("addRate");
    }
}