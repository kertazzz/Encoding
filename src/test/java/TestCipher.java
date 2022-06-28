import FirstTask.ReplaceWithoutKey;
import SecondTask.SingleReplacementWithKey;
import ThirdTask.DoubleColumnsAndRowsReplacement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class TestCipher {

  @Test(priority = 1, testName = "ReplaceWithoutKey", dataProvider="DataToEncode",dataProviderClass = DataForTest.class)
  public void encode(String message,String key){
    ReplaceWithoutKey test = new ReplaceWithoutKey();
    String encoded = test.setMessage(message).encode(ThreadLocalRandom.current().nextInt(1,10));

    StringBuilder decodedMsg = new StringBuilder();
    for (int j = 0; j < test.getRowCount(); j++) {
      for (int i = 0; i < test.getRowSize(); i++) {
        if (i == 0)
          decodedMsg.append(encoded.charAt(j));
        else if (j + (test.getRowCount() * i) < encoded.length())
          decodedMsg.append(encoded.charAt(j + (test.getRowCount() * i)));
      }
    }
    Assert.assertEquals(message,decodedMsg.toString().trim());
  }

  @Test(priority = 2,testName = "SingleReplacementByKey",dataProvider = "DataToEncode",dataProviderClass = DataForTest.class)
  public void encodeAndDecode(String message,String key){
    SingleReplacementWithKey test = new SingleReplacementWithKey();
    String encodedMsg = test.setKey(key).encode(message);
    String decodedMsg = test.setKey(key).decode(encodedMsg);
    Assert.assertEquals(decodedMsg,message.toUpperCase());
  }

  @Test(priority = 3,testName = "DoubleColAndRowReplace", dataProvider = "DataToEncode",dataProviderClass = DataForTest.class)
  public void doubleEncoDecode(String message,String firstKey){
    DoubleColumnsAndRowsReplacement test = new DoubleColumnsAndRowsReplacement();
    test.setKeyRows(firstKey).setKeyColumns(String.valueOf(ThreadLocalRandom.current().nextInt(1111,9999)));
    String encodedMsg = test.encode(message);
    String decodedMsg = test.decode(encodedMsg);
    Assert.assertEquals(decodedMsg,message);
  }
}
