# Java Blockchain

This project is a basic implementation of a blockchain system in Java with functionalities for managing wallets, transactions, and blocks. It replicates a cryptocurrency ecosystem, allowing users to create wallets, conduct transactions, mine blocks, and validate the blockchain.


## Features

- **Wallet Management**: Create and manage digital wallets using private and public keys.
- **Transactions**: Send and receive funds using wallets.
- **Blocks and Mining**: Add transactions to blocks and mine blocks with proof-of-work.
- **Blockchain Validation**: Ensure the integrity of the blockchain by validating blocks and transactions.


## Project Structure

- `Block.java`: Defines the Block class, representing a block in the blockchain. It includes methods for calculating the block's hash and mining the block.
- `StringUtil.java`: Provides functions for cryptographic operations, such as SHA-256 hashing and ECDSA signatures.
- `Transaction.java`: Represents a transaction, including inputs, outputs, and methods for generating and verifying signatures.
- `TransactionInput.java`: Defines the input for a transaction, referencing previous transaction outputs.
- `TransactionOutput.java`: Defines the output for a transaction, including the recipient, value, and unique identifier.
- `Wallet.java`: Defines a digital wallet, including key generation, balance retrieval, and sending funds.
- `CustomBlockchain.java`: Contains the main blockchain logic, including blockchain initialization, block mining, and validation.


## Demo

#### Wallet Initialization

- `WalletA` and `WalletB` are created and initialized with private and public keys.
- `coinbase` wallet is used to generate the initial transaction.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/1_Screenshot%202024-08-16%20161541.png)

#### Genesis Block

- The `genesis block` is created with an initial transaction of $100 from `coinbase` to `WalletA`.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/2_Screenshot%202024-08-11%20183940.png)

#### Transactions

- `WalletA` sends $25.7 to `WalletB`.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/3_Screenshot%202024-08-11%20183940.png)
- `WalletA` attempts to send $100 to `WalletB`, which fails as the balance is insufficient.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/4_Screenshot%202024-08-11%20183951.png)
- `WalletB` sends $20.3 units  to `WalletA`.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/5_Screenshot%202024-08-11%20183951.png)

#### Blockchain Validation

The blockchain is validated to ensure correctness and integrity.
![Screenshot](https://github.com/saatvik-t/Java-Blockchain/blob/master/assets/6_Screenshot%202024-08-11%20183951.png)