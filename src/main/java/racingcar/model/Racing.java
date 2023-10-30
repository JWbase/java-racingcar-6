package racingcar.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.exception.NoCountInputException;
import racingcar.exception.NonNumericStringException;

public class Racing {
    private static final Pattern NUMBER = Pattern.compile("^\\d+$");
    private final Cars cars;
    private final Round round;

    public Racing(Cars cars, String count) {
        this.cars = cars;
        this.round = createRound(count);
    }

    private Round createRound(String count) {
        int intCount = parseIntFromString(count);
        return new Round(intCount);
    }

    public void playRound() {
        cars.move();
        round.nextRound();
    }

    public List<CarDto> getStatus() {
        return cars.getStatus();
    }

    public boolean isEnd() {
        return round.isEndRacing();
    }

    private int parseIntFromString(String count) {
        checkEmptyCount(count);
        checkIsNumber(count);
        return Integer.parseInt(count);
    }

    private void checkIsNumber(String count) {
        Matcher matcher = NUMBER.matcher(count);
        if (!matcher.matches()) {
            throw new NonNumericStringException();
        }
    }

    private void checkEmptyCount(String count) {
        if (count.isEmpty()) {
            throw new NoCountInputException();
        }
    }
}
