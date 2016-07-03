package com.sergeyvolkodav.cachecalculator.utils;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Md5HashTest {

    @Test
    public void md5Sum() throws Exception {
        String hash = Md5Hash.md5Sum("_0,1.,+ABZ");
        assertThat(hash, is("ab4243ad9899b2f7fa69fa370a160ab1"));
    }
}