package asir.com;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Asir {
	public static void main(String[] args) {
     List<Integer> listnum	=Arrays.asList(0,0,1,2,3,4,5);
     
     List<Match> listm=Arrays.asList(new Match(3,"asir"),new Match(10,"asir"),new Match(2,"asir"),new Match(5,"asir"));
     
    // Collections.sort(listm, Comparator.comparingLong(Match::getId));
     listm.stream().forEach(t->System.out.println(t.getId()));
     
     
	}
	
	

}
