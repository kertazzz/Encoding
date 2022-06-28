package SecondTask;

import java.util.*;

public class SingleReplacementWithKey {

  private String key;
  private int rowCount;
  private String sortedStringKey;

  public SingleReplacementWithKey() {
  }

  public SingleReplacementWithKey setKey(String key) {
    this.key = key;
    return this;
  }
  private int getRowCount(String message){
    return rowCount = (((message.length() % key.length()) == 0) ? (message.length() / key.length()) : (message.length() / key.length() + 1));
  }
  private String[][] createArr(String message){
    message = message.toUpperCase();
    char[] sortedKey = key.toCharArray();
    Arrays.sort(sortedKey);
    sortedStringKey = String.copyValueOf(sortedKey);
    String[][] inputArr = new String[getRowCount(message)][key.length()];
    int index = 0;
    for (int i = 0; i < key.length(); i++) {
      for (int j = 0; j < rowCount; j++) {
        if(index < message.length()){
          inputArr[j][i] = String.valueOf(message.charAt(index));
          index++;}
        else
          inputArr[j][i] = " ";
      }
    }
    return inputArr;
  }
  public String encode(String message) {
    String[][] inputArr = createArr(message);
    char[] dd = sortedStringKey.toCharArray();
    String[][] encodedArr = new String[rowCount][key.length()];
    String keyClone = key;
    for (int i = 0; i < key.length(); i++){
      for(int j = 0; j < rowCount; j++) {
        encodedArr[j][i] = inputArr[j][keyClone.indexOf(String.valueOf(dd[i]))];
      }
      keyClone = keyClone.replaceFirst(String.valueOf(dd[i])," ");
    }
    StringBuffer encodedMsg = new StringBuffer();
    for (int i = 0; i < key.length(); i++)
      for (int j = 0; j < rowCount; j++)
        encodedMsg.append(encodedArr[j][i]);
    return encodedMsg.toString();
  }
    public String decode (String message) {
      String[][] inputArr = createArr(message);
      String[][] decodedArr = new String[rowCount][key.length()];
      String keyClone = sortedStringKey;
      char[] dd = key.toCharArray();
      int counter = 0;
      for (char x : key.toCharArray()) {
        for (int j = 0; j < rowCount; j++) {
          decodedArr[j][counter] = inputArr[j][keyClone.indexOf(String.valueOf(dd[counter]))];
        }
        keyClone = keyClone.replaceFirst(String.valueOf(dd[counter]), " ");
        counter++;
      }

      StringBuffer decodedMsg = new StringBuffer();
      for (int j = 0; j < key.length(); j++) {
        for (int i = 0; i < rowCount; i++)
          decodedMsg.append(decodedArr[i][j]);
      }
      return decodedMsg.toString().trim();
    }
}