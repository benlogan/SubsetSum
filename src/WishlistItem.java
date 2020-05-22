import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "num", "old-price", "date-added", "priority", "rating", "total-ratings", "comment", "picture", "page", "large-ssl-image", "affiliate-url" })
public class WishlistItem {
	public String name;
	public String link;
	@JsonProperty("new-price")
	public String priceString; // contains currency code
	public String ASIN;
}