package bohdan.restApiTask.util;

import bohdan.restApiTask.exception.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfPresentTest {

    @Test
    void checkIfCrypto() {
        assertThrows(CustomException.class,()->CheckIfPresent.checkIfCrypto("name"));
    }
}