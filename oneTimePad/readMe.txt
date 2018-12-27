Hello! This is a program capable of creating an encryption that cannot be cracked, even given infinite time and computational power! Pretty neat huh?

A random key from random.org of 10,000 ints from 0-65 as csv can be found under key/0-65.csv, this is used to encode plaintext to ciphertext

To be safe cryptographically you should generate your own ints, in the "truly random" folder it goes over how to 
actually generate truly random numbers from measuring quantum fluctuations, which sounds difficult but is actually quite
easy for anyone who has had rudimentary breadboard experience. 

However it is quite easier to simply get some random ints using the Math.Random() function
or by visiting random.org

Encoding takes plaintext a-z,A-Z,0-9,.,?,! (NO COMMAS)
The plaintext is written to program directly at runtime or written in oneTimePad\Encode\Message.txt then passed into the program,
Message.txt is set up with a random paragraph about Jewish history.

Encryption converts plaintext to ciphertextXXXX.txt and saves it to the ENCODE folder, 
where the XXXX is the start index of the key

Decryption looks into the ENCODE folder, displays any encoded messages (as ciphertextXXXX.txt), you choose which one to decrypt based on a prompt,
once decoded the plaintext is displayed, and if the user wants, saved to the DECODE folder as plaintextXXXX.txt

In the KEY folder there is encoding.txt which relays characters to their ints, but is not actually used by the program,
howManyUsed.txt is used to tell the program how much of the key has been used, and, for cryptographic reasons should never be reused.


/////////////////////////////////////TO MAKE IT WORK//////////////////////////////////////////////////////////////////////
The oneTimePad folder is shoved in with the rest of the code, it must be moved 
to a different folder for it to work, and this new folder's path must be added to line 21 of the java program
