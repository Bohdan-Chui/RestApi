package bohdan.restApiTask.util;

import bohdan.restApiTask.dto.RequestDTO;
import bohdan.restApiTask.exception.CustomException;
import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Fiat;
import bohdan.restApiTask.model.Pair;

/**
 * simple mapper for data
 */
public class Mapper {
    public static Pair dtoPair(RequestDTO requestDTO) throws  CustomException{
        if(requestDTO.getCurr1()!=null||requestDTO.getCurr2()!=null||requestDTO.getLprice()!=null){
            throw new CustomException("Some fields is null");
        }
        if(requestDTO.getCurr1().isEmpty()||requestDTO.getCurr2().isEmpty()||requestDTO.getLprice().isEmpty()){
            throw new CustomException("Some fields is empty");
        }
        return new Pair(Crypto.valueOf(requestDTO.getCurr1()), Fiat.valueOf(requestDTO.getCurr2()),
                Double.valueOf(requestDTO.getLprice()));
    }
}
