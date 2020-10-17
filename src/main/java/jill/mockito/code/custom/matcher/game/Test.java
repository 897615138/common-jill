package jill.mockito.code.custom.matcher.game;

import jill.mockito.model.Game;

import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class Test {
    public static void main(String[] args) {
        final GameDao gameDao = mock(GameDao.class);
        Game test1 = Game.builder().type("签到").rate(7).build();
        gameDao.addRate(test1);
        verify(gameDao).addRate(argThat(new PartyMatcher<>(Game::getRate, 7)));
        verify(gameDao).addRate(argThat(new PartyMatcher<>(Game::getType, "签到")));
    }
}
