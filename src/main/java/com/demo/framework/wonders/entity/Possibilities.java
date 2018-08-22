package com.demo.framework.wonders.entity;

import lombok.Data;
import lombok.Getter;

/**
 * @author Krishan Shukla
 */
@Data
public class Possibilities {

    private String wonderWord;
    private String cardCombination;
    private int indexAtWonderFound;


    public Possibilities(String wonderWord, String cardCombination, int indexAtWonderFound) {
        this.wonderWord = wonderWord;
        this.cardCombination = cardCombination;
        this.indexAtWonderFound = indexAtWonderFound;
    }


}
