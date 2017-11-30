package eu.cryptoeuro.wallet.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Nonce {

    @NonNull
    private Long nonce;

}
