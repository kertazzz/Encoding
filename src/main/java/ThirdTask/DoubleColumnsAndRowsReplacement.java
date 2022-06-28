package ThirdTask;


import java.util.Arrays;

public class DoubleColumnsAndRowsReplacement {

  private String keyColumns;
  private String keyRows;
  private String sortedKeyColumns;
  private String sortedKeyRows;

  public DoubleColumnsAndRowsReplacement(){}
  public DoubleColumnsAndRowsReplacement setKeyColumns(String keyColumns) {
    this.keyColumns = keyColumns;
    return this;
  }
  public DoubleColumnsAndRowsReplacement setKeyRows(String keyRows) {
    this.keyRows = keyRows;
    return this;
  }
  private String[][] createArr(String str){
    String[][] inputArr = new String[keyRows.length()][keyColumns.length()];
    int counter = 0;
    for (int i = 0; i < keyRows.length(); i++) {
      for (int j = 0; j < keyColumns.length(); j++, counter++)
        if (counter < str.length())
          inputArr[i][j] = str.substring(counter, counter + 1);
        else inputArr[i][j] = " ";
    }
    return inputArr;
  }

  public String encode(String str) {
    String[][] inputArr = createArr(str);
    char[] columArr = keyColumns.toCharArray();
    Arrays.sort(columArr);
    StringBuilder collector = new StringBuilder();
    sortedKeyColumns = collector.append(columArr).toString();
    String cloneKeyColumns = keyColumns;
    int counter = 0;
    String[][] columnsEncodedArray = new String[keyRows.length()][keyColumns.length()];
    for (char x : keyColumns.toCharArray()) {
      for (int j = 0; j < keyRows.length(); j++)
        columnsEncodedArray[j][counter] = inputArr[j][cloneKeyColumns.indexOf(columArr[counter])];
      cloneKeyColumns = cloneKeyColumns.replaceFirst(String.valueOf(columArr[counter]), " ");
      counter++;
    }

    char[] rowArr = keyRows.toCharArray();
    Arrays.sort(rowArr);
    collector.delete(0,collector.capacity());
    sortedKeyRows = collector.append(rowArr).toString();
    String cloneKeyRows = keyRows;
    counter = 0;
    String[][] rowsEncodedArray = new String[keyRows.length()][keyColumns.length()];
    for (char x : keyRows.toCharArray()) {
      for (int i = 0; i < keyColumns.length(); i++) {
        rowsEncodedArray[counter][i] = columnsEncodedArray[cloneKeyRows.indexOf(rowArr[counter])][i];
      }
      cloneKeyRows = cloneKeyRows.replaceFirst(String.valueOf(rowArr[counter]), " ");
      counter++;
    }

    StringBuffer encodedMsg = new StringBuffer("");
    for (String[] a : rowsEncodedArray) {
      for (String ch : a) {
        encodedMsg.append(ch);
      }
    }
    return encodedMsg.toString().trim();
  }

  public String decode(String str){

    String[][] inputArr = createArr(str);
    char[] keyRow = keyRows.toCharArray();
    String cloneSortedRow = sortedKeyRows ;
    int counter = 0;

    String[][] rowsDecodedArr = new String[keyRows.length()][keyColumns.length()];
    for (char x : keyRows.toCharArray()){
       for (int j = 0; j < keyColumns.length(); j++){
         rowsDecodedArr[counter][j] = inputArr[cloneSortedRow.indexOf(String.valueOf(keyRow[counter]))][j];
       }
       cloneSortedRow = cloneSortedRow.replaceFirst(String.valueOf(keyRow[counter]), " ");
       counter++;
    }

    char[] keyCol = keyColumns.toCharArray();
    String cloneSortedCol = sortedKeyColumns;
    counter = 0;
    String[][] columnsDecodedArr = new String[keyRows.length()][keyColumns.length()];
    for (int i = 0; i < keyColumns.length(); i++){
      for (int j = 0; j < keyRows.length(); j++){
        columnsDecodedArr[j][i] = rowsDecodedArr[j][cloneSortedCol.indexOf(String.valueOf(keyCol[i]))];
      }
      cloneSortedCol = cloneSortedCol.replaceFirst(String.valueOf(keyCol[i])," ");
      counter++;
    }

   StringBuffer decodedMsg = new StringBuffer("");
   for (String[] a : columnsDecodedArr) {
     for (String ch : a) {
       decodedMsg.append(ch);
     }
   }

   return decodedMsg.toString().trim();
 }
}
