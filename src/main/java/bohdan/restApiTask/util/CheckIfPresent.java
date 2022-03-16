package bohdan.restApiTask.util;

import bohdan.restApiTask.exception.CustomException;
import bohdan.restApiTask.model.Crypto;

import java.util.Locale;
import java.util.stream.Stream;

public class CheckIfPresent {
    public static void checkIfCrypto(String name){
       if(Stream.of(Crypto.values()).noneMatch(t->t.name().equals(name.toUpperCase(Locale.ROOT)))){
           throw new CustomException("That`s not CRYPTO");
       };
    }
}
