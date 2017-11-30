# wallet-server-client

[![CircleCI](https://circleci.com/gh/cryptofiat/wallet-server-client.svg?style=svg)](https://circleci.com/gh/cryptofiat/wallet-server-client)

Client library for wallet-server to encapsulate signing and sending of requests.
This is recommended to use for all wallet-server calls that interact with Ethereum.

# Publishing changes

There's a GitHub-backed repository for builds at https://github.com/cryptofiat/wallet-server-client-jars. In order to build and publish do the following:

1. Clone the repository into a folder, e.g. `/home/developer/wallet-server-client-jars`
2. In `wallet-server-client` project folder do `./gradlew -Dmaven.repo.local=/home/developer/wallet-server-client-jars publish`
3. Commit and push changes in `wallet-server-client` project folder
4. Commit and push changes in `/home/developer/wallet-server-client-jars`

# Using wallet-server-client as a dependency

In order to use the artifacts from our own maven-like repository, it needs to be defined in dependent projects as follows:

```
repositories {
...
	maven { url "https://raw.githubusercontent.com/cryptofiat/wallet-server-client-jars/master" } 
...  
}
```
