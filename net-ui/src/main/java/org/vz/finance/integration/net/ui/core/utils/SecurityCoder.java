package org.vz.finance.integration.net.ui.core.utils;


import java.security.Security;

public abstract class SecurityCoder
{
    private static Byte ADDFLAG = Byte.valueOf((byte)0);
    static  {
        if (ADDFLAG.byteValue() == 0) {

            Security.addProvider(new BouncyCastleProvider());
            ADDFLAG = Byte.valueOf((byte)1);
        }
    }
}
