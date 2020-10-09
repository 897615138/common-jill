package jill.mockito.code.customMatcher.game;

import jill.mockito.model.Game;

import static jill.common.consts.LogConstants.log;
/**
 * @author JillW
 * @date 2020/09/30
 */
public class GameDao {
    public void addRate(Game game) {
        log.info(game.toString());
        log.info("addRate");
    }
}