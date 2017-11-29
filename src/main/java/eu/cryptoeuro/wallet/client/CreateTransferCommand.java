package eu.cryptoeuro.wallet.client;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateTransferCommand implements Serializable {
    private String sourceAccount;
    private String targetAccount;
    private Long amount;
    private Long fee;
    private Long nonce;
    private String signature;
}
