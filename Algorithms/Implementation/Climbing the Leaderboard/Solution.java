import java.util.*;


class Result {
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        
        // - Delete replicated items in "ranked" List
        List<Integer> cleanRanked = new ArrayList<Integer>(new LinkedHashSet<Integer>(ranked));
        List<Integer> positionPlayer = new ArrayList<>();
        
        int position = cleanRanked.size();
        int indexPlayer = 0;
        
        for(int i = cleanRanked.size()-1; i >= 0; i--){
            
            while(player.get(indexPlayer).intValue() <= cleanRanked.get(i).intValue()){
                    
                if(player.get(indexPlayer).intValue() == cleanRanked.get(i).intValue()){
                    positionPlayer.add(position);
                } else {
                    positionPlayer.add(position+1);
                }
                    
                if(indexPlayer == player.size()-1){break;}
                indexPlayer++;
            }
            
            while(player.get(indexPlayer).intValue() > cleanRanked.get(0).intValue()){
                    
                positionPlayer.add(1);
                    
                if(indexPlayer == player.size()-1){ break;}
                indexPlayer++;
            }
            
            if(player.size() == positionPlayer.size()){break;}
            position--;
        }
        
        return positionPlayer;
    }
}
