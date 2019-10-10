# MoeidCryptoMachine
A simple to use Library for Encryption and decryption in android.
## Getting Started

This tiny library assists you to make your JSON objects more secure by performing encryption on values of your JSON data and adaptable with your class models whenever the purpose is to encrypt some parts of the data.

### Prerequisites
Android studio with gradle version 4.0+

## Demo
Simple JSON
```
{
  "ContactBook":
  {
    "HolderName":"Moeid",
    "HolderFamilyName":"Heidari",
    "Contacts":
    [
      {
        "ContactName":"Emma",
        "ContactNumber":"+79531678954"
        
      },
      {
        "ContactName":"Abigail",
        "ContactNumber":"+79534378967"
        
      },
      {
        "ContactName":"Ella",
        "ContactNumber":"+79531947952"
        
      }
    ]
    
    
  }
}
```
Simple JSON
```
{
  "ContactBook":
  {
    "HolderName":"q6paljhz1Lq/JJvFmTbZVw==",
    "HolderFamilyName":"OPiDOBNq72EkindxnH5kKw==",
    "Contacts":
    [
      {
        "ContactName":"6XHPhQxU3kzXoNjcDKP2TQ==",
        "ContactNumber":"cLr2mRCk8HECUYNgqXkiXg=="
        
      },
      {
        "ContactName":"8LbTqnHAFl/O7nFIWV3+ug==",
        "ContactNumber":"gg46uz8VMlB2woBMjBj+Xw=="
        
      },
      {
        "ContactName":"1jcAFG+xUGb4rL899q4t4g==",
        "ContactNumber":"xebhNYa02hL2kRFkJzh6zw=="
        
      }
    ]
    
    
  }
}
```
## Method
Algorithm: AES, 
Mode:CBC, 
Key size in Bits:128, 
IV (Optional), 
Output Text Format : Base64 

## How to use

Step 1:
In your Models specify any attribute you want to do the Encryption or Decryption on with @ShouldBeEncrypted Annotation (just String attributes).
Example : 
```
public class ViewModel
{
    @ShouldBeEncrypted
    String name;
    String family;
    int age;

    public List<Contact> contacts=null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ViewModel(String name, String family, int age, List<Contact> contacts) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.contacts = contacts;
    }


}
```

## How to Install
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
dependencies {
	        implementation 'com.github.MoeidHeidari:MoeidCryptoMachine:0.2.0'
	}

```
## Note
Define List type attributes with Public access modifier.

Step 2:
```
                CipherMan.with()
                    .setMode(CipherMode.ECRYPT) // Modes: Excrypt,Decrypt
                    .setClassType(ViewModel.class)//Class type
                    .setKey("1234567891234567")// 128 key
                    .setIV("ThisIsASecretKey")//IV
                    .setObject(model)//Instantiated object which you want to perform the operation on
                    .doTheOperation(); // That's all :)
```
## Author

* **Moeid Heidari** 

## And a special thanks to everyone who helped me

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details



