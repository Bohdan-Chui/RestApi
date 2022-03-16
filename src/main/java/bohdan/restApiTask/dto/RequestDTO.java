package bohdan.restApiTask.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Dto for mapping data
 */
@Component
@Data
public class RequestDTO {
    String lprice;
    String curr1;
    String curr2;

}
