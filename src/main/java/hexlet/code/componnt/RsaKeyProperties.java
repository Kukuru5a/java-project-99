package hexlet.code.componnt;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
