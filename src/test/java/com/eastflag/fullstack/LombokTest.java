package com.eastflag.fullstack;

import com.eastflag.fullstack.domain.ResultVO;
import org.junit.jupiter.api.Test;

public class LombokTest {
    @Test
    public void ResultTest() {
        ResultVO result = new ResultVO(0, "hong");
        System.out.println(result);
    }
}
