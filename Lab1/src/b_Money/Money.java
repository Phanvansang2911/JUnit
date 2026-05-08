package b_Money;

// FIXED: dùng Comparable<Money> thay vì raw type
public class Money implements Comparable<Money> {
	private int amount;
	private Currency currency;

	// FIXED: tham số amount đổi thành int cho đồng nhất
	Money(int amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	// FIXED: trả về amount
	public Integer getAmount() {
		return amount;
	}
	
	// FIXED: trả về currency
	public Currency getCurrency() {
		return currency;
	}
	
	// FIXED: định dạng số tiền "10.5 SEK"
	@Override
	public String toString() {
		double value = amount / 100.0;
		if (value == (long) value) {
			return String.format("%d %s", (long) value, currency.getName());
		} else {
			return String.format("%s %s", value, currency.getName());
		}
	}
	
	// FIXED: tính giá trị phổ quát
	public Integer universalValue() {
		return currency.universalValue(amount);
	}
	
	// FIXED: so sánh bằng universal value
	public Boolean equals(Money other) {
		if (other == null) return false;
		return this.universalValue().equals(other.universalValue());
	}
	
	// FIXED: cộng hai Money (quy đổi về universal)
	public Money add(Money other) {
		long totalUniversal = this.universalValue() + other.universalValue();
		long newAmount = Math.round(totalUniversal / this.currency.getRate());
		return new Money((int) newAmount, this.currency);
	}

	// FIXED: trừ hai Money
	public Money sub(Money other) {
		long totalUniversal = this.universalValue() - other.universalValue();
		long newAmount = Math.round(totalUniversal / this.currency.getRate());
		return new Money((int) newAmount, this.currency);
	}
	
	// FIXED: kiểm tra bằng 0
	public Boolean isZero() {
		return amount == 0;
	}
	
	// FIXED: phủ định số tiền
	public Money negate() {
		return new Money(-amount, this.currency);
	}
	
	// FIXED: so sánh dựa trên universal value
	@Override
	public int compareTo(Money other) {
		return this.universalValue().compareTo(other.universalValue());
	}
}