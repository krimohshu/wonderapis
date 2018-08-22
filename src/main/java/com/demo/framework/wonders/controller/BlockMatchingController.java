package com.demo.framework.wonders.controller;

import com.demo.framework.wonders.service.intf.IBlockMatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Krishan Shukla
 */

@RestController
@RequestMapping("/wonder")
public class BlockMatchingController {

    final static Logger logger = LoggerFactory.getLogger(BlockMatchingController.class);

    @Autowired
    private IBlockMatchingService homeService;

    @RequestMapping(value = "find/{cards}/{possibleWonder}",
            method = {RequestMethod.GET})
    public String getWandOfHarryPotter(@RequestParam(defaultValue = "[W/B/S/O , W , S/B , S]" ,required = false, value="cards") String cards , @RequestParam(defaultValue="WWSS",required = false, value="possibleWonder") String possibleWonder) {

        homeService.isWonderPossible(cards , possibleWonder);
        logger.info("input " + cards + "   " + "output " + possibleWonder);
        return null;
    }


}
