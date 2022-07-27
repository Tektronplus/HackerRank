import java.util.*;

class Solution {

    public static int formingMagicSquare(List<List<Integer>> s) {
        List<Integer> patternA  = new ArrayList<>(Arrays.asList(6,1,8,7,5,3,2,9,4));
        List<Integer> patternAm = new ArrayList<>(Arrays.asList(8,1,6,3,5,7,4,9,2));
        List<Integer> patternB  = new ArrayList<>(Arrays.asList(8,3,4,1,5,9,6,7,2));
        List<Integer> patternBm = new ArrayList<>(Arrays.asList(4,3,8,9,5,1,2,7,6));
        List<Integer> patternC  = new ArrayList<>(Arrays.asList(4,9,2,3,5,7,8,1,6));
        List<Integer> patternCm = new ArrayList<>(Arrays.asList(2,9,4,7,5,3,6,1,8));
        List<Integer> patternD  = new ArrayList<>(Arrays.asList(2,7,6,9,5,1,4,3,8));
        List<Integer> patternDm = new ArrayList<>(Arrays.asList(6,7,2,1,5,9,8,3,4));

        List<List<Integer>> patternList = new ArrayList<>();
        patternList.add(patternA);
        patternList.add(patternAm);
        patternList.add(patternB);
        patternList.add(patternBm);
        patternList.add(patternC);
        patternList.add(patternCm);
        patternList.add(patternD);
        patternList.add(patternDm);


        List<Integer> sList = matrixToArray(s); 
        List<Integer> differenceList = calcDifference(patternList, sList);
        
        System.out.println(differenceList);
        
        return differenceList.get(0);
    }
    
    public static List<Integer> matrixToArray(List<List<Integer>> s){
        List<Integer> sList = new ArrayList<>();

        for(List<Integer> listIn : s){
            for(Integer num : listIn){
                sList.add(num);
            }
        }
        
        return sList;
    }

    public static List<Integer> calcDifference(List<List<Integer>> patternList, List<Integer> sList){
        List<Integer> differenceList = new ArrayList<>();

        for(List<Integer> pattern : patternList){
            int sum = 0;
            for(int i = 0; i < 9; i++){
                sum += Math.abs(pattern.get(i) - sList.get(i));
            }
            differenceList.add(sum);
        }
        Collections.sort(differenceList);
        
        return differenceList;
    }
}

