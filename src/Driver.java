import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {
	public static void main(String[] args) throws IOException {
		Integer[] messageIn = new Integer[10000];
		Integer[] cipher = new Integer[10000];
		Integer[] key = new Integer[10000];
		Object[] cipherText = new Object[10000];
		
		Scanner keyboard = new Scanner(System.in);
		
		String path = "C:\\Users\\Clayt\\OneDrive\\Desktop\\Job 2018\\oneTimePad";

		//relations between ints and chars, essentially key/value pairs linked to encodingArray's index
		Object[] encodingArray = {0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','.','?','!', ' '};
		
		System.out.println("Please enter option "
				+ "\n0:Encode plaintext to ciphertext"
				+ "\n1:Decode ciphertext to plaintext");
		int firstChoice = keyboard.nextInt();
		if(firstChoice==0) {
			Scanner scanner = new Scanner(new File(path + "\\Encode\\Message.txt"));
			
			System.out.println("please enter choice \n"
					+ "0 : Use Message.txt in 'encode' folder \n"
					+ "1 : Enter message manually \n"
					+ "-1: Exit");
			
			int choice = keyboard.nextInt();
			String message = "";
			keyboard.nextLine();
			if(choice==0) {
	
		        scanner.useDelimiter(",");	        
		        message = scanner.next();
			}
			if(choice==1) {
				System.out.println("please enter message \n"
						+ "use ONLY a-z,A-Z,0-9,!,.,?, and spaces \n"
						+ "use NO commas, NO brackets");
				message = keyboard.nextLine();
			}
			if (choice==-1){
				System.exit(0);
			}
			
			
	        Scanner howManyUsed = new Scanner(new File(path + "\\key\\howManyUsed.txt"));
	        howManyUsed.useDelimiter(",");
			
	
			int toSkip=0;
	        while(howManyUsed.hasNextLine()) {
	        	
	        	String howMany = howManyUsed.next();
	//        	System.out.print(howMany + ", ");
	        	howMany = howMany.replaceAll("[^0-9]+","");
	        	int real_howMany = Integer.parseInt(howMany);
	        	
	        	toSkip=real_howMany;
	
	 //       	System.out.print("PRINTING TOSKIP" + toSkip);
	        }
			howManyUsed.close();
			
			
			
	        int messageLength = message.length();
	          	
	        System.out.println("message length is " + messageLength + ", used keylength is " + toSkip);
	        
	        System.out.println("Plaintext: " + message);
	        System.out.print("Message as Ints: ");
	        for(int i=0;i<message.length();i++) {
	        	
	            messageIn[i] = Arrays.asList(encodingArray).indexOf(message.charAt(i));
	            System.out.print(messageIn[i] + ", ");
	            
	            
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	        }
	        
	        Scanner scanKey = new Scanner(new File(path + "\\key\\0-65.csv"));
	        scanKey.useDelimiter(",");
	        
	        int i=0;
	        while(scanKey.hasNextLine() && i<messageLength+toSkip) {
	        	
	        	String rank = scanKey.next();
	//        	System.out.print(rank + ", ");
	        	rank = rank.replaceAll("[^0-9]+","");
	        	int real_rank = Integer.parseInt(rank);
	        	
	        	
	        	
	        	
	        	if(i<toSkip) {
	        		i++;
	        	}
	        	
	        	else {
		        	key[i-toSkip]=real_rank;
		        	i++;
	        	}
	        	
	        	
	        }
	        System.out.println("");
	        
	        System.out.print("shift: ");
	        for(int k=0;k<messageLength;k++) {
	        	System.out.print(key[k] +", ");
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        System.out.println("");
	        
	        System.out.print("Shifted Ints: ");
	        for(int j=0;j<messageLength;j++) {
	        	
	        	cipher[j]= ((messageIn[j]+key[j]) % encodingArray.length);
	        	
	        	System.out.print(cipher[j] + ", ");
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        System.out.println("");
	        
	        System.out.print("Ciphertext: ");
	        for(int k=0;k<messageLength;k++) {
	        	System.out.print(  Arrays.asList(encodingArray).get(cipher[k])    +"");
	        	cipherText[k]=Arrays.asList(encodingArray).get(cipher[k]);
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        
	        
	        System.out.println("\n\nWrite to file?\n"
	        		+ "0:No\n"
	        		+ "1:Yes\n"
	        		+ "NOTE: IF you write to file, some of the key will be used up\n"
	        		+ "for cryptographic reasons, it should never be reused");
	        int newChoice=keyboard.nextInt();
	        
	        //MAY MESS THINGS UP!!!!!!!!!!!!!!!!
	        keyboard.nextLine();
	        
	        if(newChoice==0) {
	        	System.exit(0);
	        }
	        if(newChoice==1) {
	//      \\Encode\\ciphers  	
	//        	path + "\\Encode\\ciphers\\cipher"+ "0" + ".txt"
	        	
	        	  BufferedWriter outputWriter = null;
	        	  outputWriter = new BufferedWriter(new FileWriter(path + "\\Encode\\ciphers\\cipher"+ toSkip+ ".txt"));
	        	  for (int count = 0; count < messageLength; count++) {
	        	    // Maybe:
	        	    outputWriter.write(cipherText[count]+"");
	        	    // Or:
	//        	    outputWriter.write(Integer.toString(x[i]);
	//        	    outputWriter.newLine();
	        	  } 
	        	  outputWriter.flush();  
	        	  outputWriter.close();
	        	  
	        	
	        	  BufferedWriter howManyWriter = null;
	        	  howManyWriter = new BufferedWriter(new FileWriter(path + "\\key\\howManyUsed.txt",false));
	          	  	
	        	  howManyWriter.write(String.valueOf(toSkip+messageLength));
	
	        	  howManyWriter.flush();  
	        	  howManyWriter.close();
	        }
	        //end of if statements
	        
	        System.out.println("");
	        scanner.close();
			scanKey.close();
		}
		if(firstChoice==1) {
			System.out.println("Printing files of form ciphertextXXXX.txt in " + path + "\\Encode\\ciphers"
					+ "\nPlease enter number of file");
			
			
			
			File folder = new File(path + "\\Encode\\ciphers");
			File[] listOfFiles = folder.listFiles();
			
			int i=0;
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        System.out.println(i + ": " + file.getName());
			        i++;
			    }
			}
			int fileChoice = keyboard.nextInt();
			String fileName = listOfFiles[fileChoice].getName();
			
			System.out.println("You have chosen " + fileName + ", decoding" );
			
			//ok so now we want to get the number on the end of that file
			
			
			int length ="cipher".length();
			
			int increment = 0;
			
			String s = "";
			while (Character.isDigit(fileName.charAt(length+increment))) {
				
				s += fileName.charAt(length+increment);
				increment++;
			}
			int toSkip = Integer.parseInt(s);
			
			Scanner toDecode = new Scanner(new File(path + "\\Encode\\ciphers\\" + fileName));
			
	        toDecode.useDelimiter(",");	        
	        String message = toDecode.next();
	        
	        
	        
	        
	        int messageLength = message.length();
          	
	        System.out.println("message length is " + messageLength + ", key start index is " + toSkip);
	        
	        System.out.println("Ciphertext: " + message);
	        System.out.print("Message as Ints: ");
	        for(int j=0;j<message.length();j++) {
	        	
	        	if (Character.isDigit(message.charAt(j))) {
	        		messageIn[j] = message.charAt(j)-'0';
	        		System.out.print(messageIn[j] + ", ");
	        		continue;
	        	}
	            messageIn[j] = Arrays.asList(encodingArray).indexOf(message.charAt(j));
	            System.out.print(messageIn[j] + ", ");
	            
	            
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	        }
	        
	        Scanner scanKey = new Scanner(new File(path + "\\key\\0-65.csv"));
	        scanKey.useDelimiter(",");
	        
	        int k=0;
	        while(scanKey.hasNextLine() && k<messageLength+toSkip) {
	        	
	        	String rank = scanKey.next();
	//        	System.out.print(rank + ", ");
	        	rank = rank.replaceAll("[^0-9]+","");
	        	int real_rank = Integer.parseInt(rank);
	        	
	        	
	        	
	        	
	        	if(k<toSkip) {
	        		k++;
	        	}
	        	
	        	else {
		        	key[k-toSkip]=real_rank;
		        	k++;
	        	}
	        	
	        	
	        }
	        System.out.println("");
	        
	        System.out.print("shift: ");
	        for(int l=0;l<messageLength;l++) {
	        	System.out.print(key[l] +", ");
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        System.out.println("");
	        
	        
	        //although the array used is listed cipher that is just useful for abstraction reasons,
	        //cipher[j] actually holds plaintext integer encoding at j
	        System.out.print("Shifted Ints: ");
	        for(int j=0;j<messageLength;j++) {
	        	
	        	
	        	if (messageIn[j]-key[j]<0) {
	        		cipher[j]=encodingArray.length + (messageIn[j]-key[j]);
	        	}
	        	else {
		        	cipher[j]= (messageIn[j]-key[j]);
	        	}
	        	
	        	System.out.print(cipher[j] + ", ");
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        System.out.println("");
	        
	        System.out.print("plaintext: ");
	        for(int val=0;val<messageLength;val++) {
	        	System.out.print(  Arrays.asList(encodingArray).get(cipher[val])    +"");
	        	cipherText[val]=Arrays.asList(encodingArray).get(cipher[val]);
	        	
	        	
	            try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        
	        
	        System.out.println("\n\nWrite to file?\n"
	        		+ "0:No\n"
	        		+ "1:Yes\n");
	        int newChoice=keyboard.nextInt();
	        keyboard.nextLine();
	        
	        if(newChoice==0) {
	        	System.exit(0);
	        }
	        if(newChoice==1) {	
	        	  BufferedWriter outputWriter = null;
	        	  outputWriter = new BufferedWriter(new FileWriter(path + "\\Decode\\plaintext\\plaintext"+ toSkip+ ".txt"));
	        	  for (int count = 0; count < messageLength; count++) {
	        	    outputWriter.write(cipherText[count]+"");
	        	  } 
	        	  outputWriter.flush();  
	        	  outputWriter.close();
	        }   
	        toDecode.close();
		}
		keyboard.close();
	}
}
	
