package bohdan.restApiTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOforCSV {
    private String name;
    private Double minPrice;
    private Double maxPrice;
}
