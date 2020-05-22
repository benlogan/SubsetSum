import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * So, I don't really remember what I was trying to do here!
 * 
 * I created this back in 2016.
 * 
 * I think the aim was to take a given Amazon voucher (say £100) 
 * and work out the most efficient way of spending that on wishlist items
 * 
 * @author benlogan
 *
 */
public class ParseWishlist {

	public static void main(String[] args) {
		try {
			new ParseWishlist().mapObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * then pass the arraylist of prices to subset sum and identify suitable purchases,
	 * based on a target price (say £100)
	 * 
	 * remember, if there are 163 items in the list - thats a max of 26569 combos that need to be checked!
	 */
	public void mapObject() throws JsonParseException, JsonMappingException, IOException {
		Map<String, Integer> numbers = new HashMap<String, Integer>();
		//ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		ObjectMapper mapper = new ObjectMapper();
		List<WishlistItem> itemList = Arrays.asList(mapper.readValue(new File("data/wishlist.json"), WishlistItem[].class));
		for(WishlistItem item : itemList) {
			System.out.println("Item price : " + item.priceString);
			if(item.priceString.contains("£")) {
				double price = Double.valueOf(item.priceString.substring(1, item.priceString.length()));
				System.out.println("Found a price that we can use! " + price);
				int priceToAdd = new Double(price).intValue();
				if(priceToAdd > 0) {
					numbers.put(item.ASIN, priceToAdd);
					//numbers.add(priceToAdd);
				}
			}
		}
		
		System.out.println("Data Set : " + numbers);
		
		int target = 68;
		SubsetSum.sumUp(numbers, target);
	}
}
