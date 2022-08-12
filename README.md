# hello-vault-driver
Runnable examples of how to integrate a Java Spring Boot application with
[HashiCorp](http://www.hashicorp.com) [Vault](https://www.vaultproject.io/), using the community-maintained
[Vault Java Driver](https://github.com/BetterCloud/vault-java-driver) library.

## [Introduction](./src/)

This is a simple example of reading and writing your first secret!

## [Prerequisites]()
- Java JDK installed
- Maven installed

and access to one of the following Vault environments
- Vault OSS
- HashiCorp Vault Enterprise
- HCP Vault 

The following environment variables:

- VAULT_ADDR 
- VAULT_TOKEN
- VAULT_NAMESPACE

## [Tested prerequisites]
- HCP Vault

`$ mvn -version 
    Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63) \
    Maven home: /opt/homebrew/Cellar/maven/3.8.6/libexec
    Java version: 18.0.2, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/18.0.2/libexec/openjdk.jdk/Contents/Home
    Default locale: en_NL, platform encoding: UTF-8
    OS name: "mac os x", version: "12.5", arch: "aarch64", family: "mac"`
## [Building the app]()
`$ mvn clean install -DskipTests`

## [Running the app]()
`$ java -jar target/hello-vault-driver-0.0.1-SNAPSHOT.jar`
