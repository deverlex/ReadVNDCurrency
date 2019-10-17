import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void testReadHundred() {
        Assert.assertEquals("một trăm", VNDCurrencyReader.readNumber(100L));
        Assert.assertEquals("một trăm linh một", VNDCurrencyReader.readNumber(101L));
        Assert.assertEquals("một trăm linh hai", VNDCurrencyReader.readNumber(102L));
        Assert.assertEquals("một trăm mười hai", VNDCurrencyReader.readNumber(112L));
    }

    @org.junit.Test
    public void testThousand() {
        Assert.assertEquals("một nghìn", VNDCurrencyReader.readNumber(1000L));
        Assert.assertEquals("một nghìn một trăm linh một", VNDCurrencyReader.readNumber(1101L));
        Assert.assertEquals("một nghìn không trăm linh một", VNDCurrencyReader.readNumber(1001L));
        Assert.assertEquals("hai nghìn ba trăm mười hai", VNDCurrencyReader.readNumber(2312L));
    }

    @org.junit.Test
    public void testMillion() {
        Assert.assertEquals("một triệu", VNDCurrencyReader.readNumber(1000000L));
        Assert.assertEquals("một triệu không trăm linh một", VNDCurrencyReader.readNumber(1000001L));
        Assert.assertEquals("một triệu một trăm linh hai nghìn một trăm linh một", VNDCurrencyReader.readNumber(1102101L));
        Assert.assertEquals("một triệu không trăm linh một nghìn ba trăm năm mươi", VNDCurrencyReader.readNumber(1001350L));
        Assert.assertEquals("ba trăm triệu", VNDCurrencyReader.readNumber(300000000L));
        Assert.assertEquals("ba trăm hai mươi triệu bốn trăm nghìn không trăm linh một", VNDCurrencyReader.readNumber(320400001L));
    }

    @org.junit.Test
    public void testBillion() {
        Assert.assertEquals("một tỷ", VNDCurrencyReader.readNumber(1000000000L));
        Assert.assertEquals("một tỷ không trăm linh một", VNDCurrencyReader.readNumber(1000000001L));
        Assert.assertEquals("một tỷ một trăm nghìn ba trăm hai mươi mốt", VNDCurrencyReader.readNumber(1000100321L));
        Assert.assertEquals("một tỷ không trăm linh một triệu bốn trăm mười nghìn", VNDCurrencyReader.readNumber(1001410000L));
    }

    @org.junit.Test
    public void testVeryBigNumber() {
        Assert.assertEquals("chín mươi chín triệu chín trăm chín mươi chín nghìn chín trăm chín mươi chín tỷ chín trăm chín mươi chín triệu chín trăm chín mươi chín nghìn chín trăm chín mươi chín",
                VNDCurrencyReader.readNumber(99999999999999999L));
    }
}
