package b_Money;

public class Currency {
	private String name;
	private Double rate;
	
	Currency (String name, Double rate) {
		this.name = name;
		this.rate = rate;
	}

	// FIXED: tính universal value
	public Integer universalValue(Integer amount) {
		return (int) Math.round(amount * rate);
	}

	// FIXED: trả về name
	public String getName() {
		return name;
	}
	
	// FIXED: trả về rate
	public Double getRate() {
		return rate;
	}
	
	// FIXED: gán rate mới
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	// FIXED: chuyển đổi sang tiền tệ này
	public Integer valueInThisCurrency(Integer amount, Currency othercurrency) {
		double universal = amount * othercurrency.getRate();
		return (int) Math.round(universal / this.rate);
	}
}