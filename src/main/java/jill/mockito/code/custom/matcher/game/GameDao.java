package jill.mockito.code.custom.matcher.game;

import jill.mockito.model.Game;

import static jill.common.constant.LogConstant.log;

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