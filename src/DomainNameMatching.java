import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DomainNameMatching {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Main Domain");
		String mainDomain = sc.nextLine();
		mainDomain = processDomain(mainDomain);
		System.out.println("Enter the number of domains to be compared");
		int num = Integer.parseInt(sc.nextLine());
		List<String> domainsList = new ArrayList<>();
		System.out.println("Enter "+num+" domains");
		for(int i=0;i<num;i++) {
			String s = sc.nextLine();
			domainsList.add(s);
		}
		System.out.println("Similarity between domains:");
		for(String domain : domainsList) {
			domain = processDomain(domain);
			double similarity = domainCompare(mainDomain, domain);
			System.out.println(mainDomain+"\t"+domain+"\t"+similarity);
		}
		

	}
	
	public static String processDomain(String s) {
		return s.replaceAll("\\s", "");
	}
	
	public static double domainCompare(String main, String domain) {
		String longer = main, shorter = domain;
	    if (main.length() < domain.length()) {
	      longer = domain; shorter = main;
	    }
	    int longerLength = longer.length();
	    if (longerLength == 0) { return 1.0;}
	    return (longerLength - levenshteinDistance(longer, shorter)) / (double) longerLength;
	}
	
	public static int levenshteinDistance(String s1, String s2) {
	    s1 = s1.toLowerCase();
	    s2 = s2.toLowerCase();

	    int[] costs = new int[s2.length() + 1];
	    for (int i = 0; i <= s1.length(); i++) {
	      int lastValue = i;
	      for (int j = 0; j <= s2.length(); j++) {
	        if (i == 0)
	          costs[j] = j;
	        else {
	          if (j > 0) {
	            int newValue = costs[j - 1];
	            if (s1.charAt(i - 1) != s2.charAt(j - 1))
	              newValue = Math.min(Math.min(newValue, lastValue),
	                  costs[j]) + 1;
	            costs[j - 1] = lastValue;
	            lastValue = newValue;
	          }
	        }
	      }
	      if (i > 0)
	        costs[s2.length()] = lastValue;
	    }
	    return costs[s2.length()];
	  }
	
	

}
