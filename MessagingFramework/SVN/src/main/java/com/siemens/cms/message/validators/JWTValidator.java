package com.siemens.cms.message.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matteo on 6/2/16.
 */
@Component
public class JWTValidator implements Validator{

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${security.oauth2.resource.jwt.key-file:jwt-test.jks}")
    private String jwtKeyFile; // = "jwt-test.jks";

    @Value("${security.oauth2.resource.jwt.keypair-name:jwt-test}")
    private String keyPairName; // ="jwt-test";

    @Value("${security.oauth2.resource.jwt.keypair-pass}")
    private String keyPairPass; // = "testpass";

    private RsaVerifier verifier = null;

    private static JWTValidator ourInstance = new JWTValidator();

    @Bean
    public static JWTValidator getInstance() {
        return ourInstance;
    }

    private JWTValidator() {
    }

    @PostConstruct
    public void init() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(jwtKeyFile), keyPairPass.toCharArray());

        PublicKey pk = keyStoreKeyFactory.getKeyPair(keyPairName).getPublic();
        verifier = new RsaVerifier((RSAPublicKey)pk);
    }

    @Override
    public boolean isValid(String token) {
        if(verifier == null) {
            init();
        }
        if(StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            decodeaAndVerity(token);
            return true;
        } catch (InvalidSignatureException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Object> getContent(String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    private Jwt decodeaAndVerity(String token) {

        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);

        logger.info(jwt.getClaims().toString());

        return jwt;

    }

}
