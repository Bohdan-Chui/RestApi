package bohdan.restApiTask.util;

import bohdan.restApiTask.dto.RequestDTO;
import bohdan.restApiTask.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void dtoPair() {
        RequestDTO requestDTO = RequestDTO.builder().lprice("12").build();
        assertThrows(CustomException.class, ()->Mapper.dtoPair(requestDTO));
    }
}