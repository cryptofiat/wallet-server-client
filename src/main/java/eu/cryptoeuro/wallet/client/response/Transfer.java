package eu.cryptoeuro.wallet.client.response;

import java.util.Date;

import lombok.Data;

@Data
public class Transfer {

    private String id;
    private String targetAccount;
    private String sourceAccount;
    private TransferStatus status;
    private Long amount;
    private Long fee;
    private Long nonce;
    private String reference;
    private String signature;
    private String blockHash;
    private Date timestamp;

}
