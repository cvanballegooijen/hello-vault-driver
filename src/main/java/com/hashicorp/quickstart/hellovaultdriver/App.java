package com.hashicorp.quickstart.hellovaultdriver;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.response.LogicalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class App implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private final Environment env;

    public App(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        context.close();
    }

    @Override
    public void run(String... strings) throws Exception {
        String vaultAddress = env.getProperty("VAULT_ADDR");
        String vaultToken = env.getProperty("VAULT_TOKEN");
        String vaultNamespace = env.getProperty("VAULT_NAMESPACE");
        if (vaultAddress == "" || vaultAddress == null) {
           vaultAddress = "http://127.0.0.1:8200";
        }
        if (vaultToken == "" || vaultToken == null) {
            vaultToken = "root";
        }
        if (vaultNamespace == "" || vaultNamespace == null) {
            vaultNamespace = "admin";
        }
        logger.info("{}", "Connecting to Vault using following configuration:");
        logger.info("{}", "VAULT_ADDR="+vaultAddress);
        logger.info("{}", "VAULT_TOKEN="+vaultToken);
        logger.info("{}", "VAULT_NAMESPACE="+vaultNamespace);
        final VaultConfig config = new VaultConfig()
                .address(vaultAddress)
                .nameSpace(vaultNamespace)
                .token(vaultToken)
                .build();
        final Vault vault = new Vault(config);

        // Write a secret
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("password", "Hashi123");

        // Write a secret
        final LogicalResponse writeResponse = vault.logical()
                .write("kv/my-secret-password", data);
        if (writeResponse.getLeaseId() != null) {
            System.out.println("Secret 'my-secret-password' written successfully.");
        } else {
            System.out.println("Unable to write 'my-secret-password'");
        }
        // Read a secret
        final String value = vault.logical()
                .read("kv/my-secret-password")
                .getData().get("password");
        if (value!=null) {
            System.out.println("Secret 'my-secret-password' read successfully");
            System.out.println("password=" + value);
        } else {
            System.out.println("Unable to read value from secret 'my-secret-password'");
        }

    }
}
