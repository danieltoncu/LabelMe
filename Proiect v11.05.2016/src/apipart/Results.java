package apipart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Lucian on 14.05.2016.
 */
public class Results {
	public Results() {
	}

	public Map<String, BigDecimal> mapValues(String myString) {

		String[] myString1 = myString.split(" ");
		Map<String, BigDecimal> myMap = new HashMap<String, BigDecimal>();
		String category, name2;
		StringTokenizer stringTokenizer = new StringTokenizer(myString, " ");

		while (stringTokenizer.hasMoreElements()) {
			String categoryName = stringTokenizer.nextElement().toString();
			category = categoryName.substring(0, categoryName.indexOf("["));
			name2 = categoryName.substring(categoryName.indexOf("[") + 1, categoryName.indexOf("]"));
			Float percent = Float.parseFloat(name2);
			BigDecimal a = BigDecimal.valueOf(percent);
			myMap.put(category, a);
		}
		return myMap;
	}
	public ArrayList<String> getCategory(Map<String,BigDecimal> myMap){
		ArrayList<String> results=new ArrayList<>();
		boolean noCategory=true;
		ArrayList<BigDecimal> procents=new ArrayList<>();
		BigDecimal value;
		BigDecimal max=BigDecimal.valueOf(0);
		for (Map.Entry<String, BigDecimal> entry : myMap.entrySet()) {
			procents.add(entry.getValue());
			int res=max.compareTo(entry.getValue());
			if(res==-1) max=entry.getValue();
        }
		value=procents.get(0);
		int i=1;
		while(i<procents.size()&&noCategory){
			int res1=value.compareTo(procents.get(i));
			if(res1!=0){
				noCategory=false;
			}
			i++;
		}
		if(noCategory) return results;
		BigDecimal marja=max.multiply(BigDecimal.valueOf(10));
		marja=marja.divide(BigDecimal.valueOf(100));
		BigDecimal min=max.subtract(marja);
		//System.out.println(min);
		for (Map.Entry<String, BigDecimal> entry : myMap.entrySet()) {
			int res=max.compareTo(entry.getValue());
			int resm=min.compareTo(entry.getValue());
			if(res==0) results.add(entry.getKey());
			if(resm==0)  results.add(entry.getKey());
			if(res==-1&&resm==1) results.add(entry.getKey());
        }
		return results;
	}
}
