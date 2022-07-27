import java.util.*;


class Solution {
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        int result = 0;
        
        //Directions relative to the queen
        List<List<Integer>> UU_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Up
        List<List<Integer>> UR_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Up-Right
        List<List<Integer>> RR_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Right
        List<List<Integer>> DR_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Down-Right
        List<List<Integer>> DD_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Down 
        List<List<Integer>> DL_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Down-Left
        List<List<Integer>> LL_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Left
        List<List<Integer>> UL_line = new ArrayList<>(Arrays.asList(Arrays.asList(r_q, c_q))); //Up-Left
        
        
        List<Integer> cleanObstacle = new ArrayList<>(Arrays.asList(n,n,n,n,n,n,n,n));

        for(List<Integer> obstacle : obstacles){
            int r_ob = obstacle.get(0).intValue();
            int c_ob = obstacle.get(1).intValue();
            
            if(r_ob > r_q && c_ob == c_q){ //UU    
                cleaningObs(cleanObstacle, r_ob, r_q, 0);
            
            } else if(r_ob > r_q && c_ob > c_q && Math.abs(r_ob - r_q) == Math.abs(c_ob - c_q)){ //UR
                cleaningObs(cleanObstacle, c_ob, c_q, 1);
                
            } else if(r_ob == r_q && c_ob > c_q){ //RR
                cleaningObs(cleanObstacle, c_ob, c_q, 2);
                
            } else if(r_ob < r_q && c_ob > c_q && Math.abs(r_ob - r_q) == Math.abs(c_ob-c_q)){ //DR
                cleaningObs(cleanObstacle, c_ob, c_q, 3);
                
            } else if(r_ob < r_q && c_ob == c_q){ //DD
                cleaningObs(cleanObstacle, r_ob, r_q, 4);
                
            } else if(r_ob < r_q && c_ob < c_q && Math.abs(r_ob - r_q) == Math.abs(c_ob - c_q)){ //DL
                cleaningObs(cleanObstacle, c_ob, c_q, 5);
                
            } else if(r_ob == r_q && c_ob < c_q){ //LL
                cleaningObs(cleanObstacle, c_ob, c_q, 6);
                  
            } else if(r_ob > r_q && c_ob < c_q && Math.abs(r_ob - r_q) == Math.abs(c_ob - c_q)){ //UL  
                cleaningObs(cleanObstacle, c_ob, c_q, 7);
            }
        }
        
        for(int i = 0; i < n; i++){
            straightLine(UU_line, cleanObstacle, n, c_q, true, true, 0);
            straightLine(DD_line, cleanObstacle, n, c_q, true, false, 4);
            straightLine(RR_line, cleanObstacle, n, r_q, false, true, 2);
            straightLine(LL_line, cleanObstacle, n, r_q, false, false, 6);
            
            obliqueLine(UR_line, cleanObstacle, n, true, true,1);
            obliqueLine(DR_line, cleanObstacle, n, true, false,3);
            obliqueLine(DL_line, cleanObstacle, n, false, false,5);
            obliqueLine(UL_line, cleanObstacle, n, false, true,7);

        }
        
        List<List<List<Integer>>> lineList = new ArrayList<>();
        Collections.addAll(lineList, UU_line, UR_line, RR_line, DR_line, DD_line, DL_line, LL_line, UL_line); 
        
        for(List<List<Integer>> line : lineList){
            line.remove(0);
            
            result += line.size();
        }
        
        return result;
    }

    public static void cleaningObs(List<Integer> cleanObstacle, int ob, int q, int index){
        int dif = Math.abs(ob - q);
        cleanObstacle.set(index, cleanObstacle.get(index).intValue() > dif ? dif : cleanObstacle.get(index).intValue());
    }
    public static void straightLine(List<List<Integer>> list, List<Integer> cleanObstacle, int n, int valueQueen, boolean isVertical, boolean isPositive, int indexObs){
        int sizeList = list.size();
        int lastIndex = sizeList - 1;
        int valueObs = cleanObstacle.get(indexObs).intValue();
        int changeValue = isPositive ? 1 : -1;
        boolean limitBoard = isPositive ? list.get(lastIndex).get(isVertical ? 0 : 1) < n : list.get(lastIndex).get(isVertical ? 0 : 1) > 1;
        
        if(limitBoard && sizeList < valueObs){
            int row = isVertical ? list.get(lastIndex).get(isVertical ? 0 : 1) + changeValue : valueQueen;
            int col = isVertical ? valueQueen : list.get(lastIndex).get(isVertical ? 0 : 1) + changeValue;

            list.add(Arrays.asList(row, col));
        }
    }
    
    public static void obliqueLine(List<List<Integer>> list, List<Integer> cleanObstacle, int n, boolean isPositiveX, boolean isPositiveY, int indexObs){
        int sizeList = list.size();
        int lastIndex = sizeList - 1;
        int valueObs = cleanObstacle.get(indexObs).intValue();
        
        boolean limitBoardX = isPositiveX ? list.get(lastIndex).get(1) < n : list.get(lastIndex).get(1) > 1;
        boolean limitBoardY = isPositiveY ? list.get(lastIndex).get(0) < n : list.get(lastIndex).get(0) > 1;

        
        if(limitBoardX && limitBoardY && sizeList < valueObs){
            int row = list.get(lastIndex).get(0) + (isPositiveY ? 1 : -1);
            int col = list.get(lastIndex).get(1) + (isPositiveX ? 1 : -1);

            list.add(Arrays.asList(row, col));
        }
    }
}
