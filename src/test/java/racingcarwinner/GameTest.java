package racingcarwinner;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import racingcar.Game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    @DisplayName("차 이름 문자열 쪼개기")
    public void splitCarNameTest(){
        String[] CarNames = {"포비", "코난", "베니"};
        assertThat(Game.splitCarName("포비,코난,베니")).
                isEquals(CarNames);
    }

    @Test
    @DisplayName("경기 수가 제대로 입력 되었는가")
    public void countRoundTest(){

        InputView.inputRoundCount(1);
        assertTrue(racingcarwinner.Game.getRound() == 1);
    }
}
