package FirstTask;


import java.util.Arrays;

public class ReplaceWithoutKey {
  private String sourceMessage;
  private int rowSize;
  private int rowCount;
  public ReplaceWithoutKey() {
  }


  public ReplaceWithoutKey setMessage(String msg) {
    this.sourceMessage = msg;
    return this;
  }

  public int getRowSize(){
    return this.rowSize;
  }

  public int getRowCount(){
    return this.rowCount;

  }
  public String encode(int rowsize) {
    rowSize = rowsize;
    sourceMessage = sourceMessage.trim();
    rowCount = ((sourceMessage.length() % rowsize) == 0) ? (sourceMessage.length() / rowsize) : (sourceMessage.length() / rowsize + 1);
    Character[][] cipherArray = new Character[rowsize][rowCount];
    int counter = 0;
    for (int j = 0; j < rowCount; j++) {
      for (int i = 0; i < rowsize; i++, counter++) {
        if(counter < sourceMessage.length())
          cipherArray[i][j] = sourceMessage.charAt(counter);
        else cipherArray[i][j] = ' ';
      }
    }
    StringBuilder collector = new StringBuilder();
    for (int i = 0; i < rowsize; i++) {
      for (int j = 0; j < rowCount; j++)
        collector.append(cipherArray[i][j]);
    }

    return collector.toString();
  }
}