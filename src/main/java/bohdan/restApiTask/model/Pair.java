package bohdan.restApiTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pair implements Serializable {
    @Id
    private String id;

    private Crypto crypto;
    private Fiat fiat;
    private Double price;

    public Pair(Crypto crypto, Fiat fiat, Double price) {
        this.crypto = crypto;
        this.fiat = fiat;
        this.price = price;
    }
}
