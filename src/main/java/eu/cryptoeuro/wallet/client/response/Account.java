package eu.cryptoeuro.wallet.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private String address;
    private boolean approved;
    private boolean closed;
    private boolean frozen;
    private Long nonce;
    private Long balance;

}
