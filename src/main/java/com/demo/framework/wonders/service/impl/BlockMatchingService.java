package com.demo.framework.wonders.service.impl;

import com.demo.framework.wonders.service.intf.IBlockMatchingService;
import com.demo.framework.wonders.entity.Possibilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Krishan Shukla
 */
@Service
public class BlockMatchingService implements IBlockMatchingService {
    final static Logger logger = LoggerFactory.getLogger(BlockMatchingService.class);

    @Override
    public Boolean isWonderPossible(String cards, String possibleWonder) {

        List<String> cardList = new ArrayList<>();

        cardList = Arrays.asList(cards.split(",")).stream()
                .map(
                        elemnetType -> {
                            String elementsInList = elemnetType.replace("/", "")
                                    .replace("]", "")
                                    .replace("[", "")
                                    .replaceAll("\\s*", "");

                            return elementsInList;
                        }
                )
                .collect(Collectors.toList());
       int[] wondersCharIntSet = possibleWonder.chars().boxed().collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
      // List<Character> wondersCharSet = possibleWonder.

        //Find all combination of Wonder
        List<Character> myWonderList = possibleWonder.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        if (myWonderList.size() > cardList.size()) {
            return false;
        }
        //Unqiue combinations of the possible wonders
        permute(possibleWonder).forEach(System.out::println);
       Set<String> possibleWonderSet =  permute(possibleWonder);

        /*   ALGO OF CHECKING ALL PERMUTATION OF POSSIBLE WONDER
        // STEP-1 Iterate Permutation AND FIND IF THAT CHAR IS IN CARD- GREEDY
        // STEP-2 Create set of possibilities until all combination is found


        */

         final List<String> searchedCardsList = cardList;
        // STEP-1
        int counterOfIndex =0;
        for(String wonderWord : possibleWonderSet){
            for(String cardCombination : cardList)
            {
                if(cardCombination.toUpperCase().contains(wonderWord.toUpperCase())){
                   new Possibilities(wonderWord , cardCombination , counterOfIndex);


                   break;
                }
                counterOfIndex ++;
            }

        }
       /* permute(possibleWonder).stream().filter( t->{

          Boolean isWonderInTheString =  searchedCardsList.stream().filter(d-> d.contains(t));
                    return isWonderInTheString;
                }
        )*/

        cardList.stream().forEach(System.out::println);
        myWonderList.stream().forEach(System.out::println);

        return false;
    }


    public Set<String> permute(String str) {
        Set<String> res = new LinkedHashSet<>();
        if (str.isEmpty()) {
            res.add(str);
        } else {
            for (int i = 0; i < str.length(); i++) {
                for (String s : permute(str.substring(0, i)+str.substring(i+1))) {
                    res.add(str.charAt(i)+s);
                }
            }
        }
        return res;
    }



}
