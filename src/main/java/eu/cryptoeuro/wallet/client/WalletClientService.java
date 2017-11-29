package eu.cryptoeuro.wallet.client;

import org.apache.commons.codec.binary.Base64;
import org.ethereum.crypto.ECKey;
import org.ethereum.crypto.HashUtil;
import org.spongycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Component
public class WalletClientService {
    public CreateTransferCommand createAndSignCreateTransferCommand(String senderAddress, Long senderNonce, String receiverAddress, Long sprayAmount, ECKey key, Long fee) throws RuntimeException {
        byte[] signatureArg;

        try {
            signatureArg = signDelegate(fee, sprayAmount, senderNonce + 1, without0x(receiverAddress), key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreateTransferCommand createTransferCommand = new CreateTransferCommand();
        createTransferCommand.setSourceAccount(senderAddress);
        createTransferCommand.setTargetAccount("0x" + receiverAddress);
        createTransferCommand.setAmount(sprayAmount);
        createTransferCommand.setFee(fee);
        createTransferCommand.setNonce(senderNonce + 1);
        createTransferCommand.setSignature(Hex.toHexString(signatureArg));
        return createTransferCommand;
    }

    private byte[] signDelegate(long fee, long amount, long nonce, String address, ECKey signer) throws IOException {
        ByteArrayOutputStream hashInput = new ByteArrayOutputStream();
        hashInput.write(uint256(nonce));
        hashInput.write(Hex.decode(without0x(address)));
        hashInput.write(uint256(amount));
        hashInput.write(uint256(fee));

        byte[] hashOutput = HashUtil.sha3(hashInput.toByteArray());
        String strSig = signer.sign(hashOutput).toBase64();

        // Because contract expects the sig concatenated in different order than canonical
        byte[] byteSig = new byte[65];
        System.arraycopy(Base64.decodeBase64(strSig), 1, byteSig, 0, 64);
        System.arraycopy(Base64.decodeBase64(strSig), 0, byteSig, 64, 1);
        return byteSig;
    }

    private byte[] uint256(long val) {
        ByteBuffer bytes = ByteBuffer.allocate(32);
        bytes.putLong(32 - Long.BYTES, val);
        return bytes.array();
    }

    private String without0x(String hex) {
        return hex.startsWith("0x") ? hex.substring(2) : hex;
    }
}
