package lotto.statistics;

import java.util.Objects;

public class Statistic {

	public Statistic(int matchCount, Earn earn) {
		this.count = matchCount;
		this.earn = earn;
	}

	private int count;
	private final Earn earn;

	public int earningPrice() {
		return count * earn.winningPrice();
	}

	public Statistic plusMatchCount() {
		count++;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Statistic))
			return false;
		Statistic statistic = (Statistic)o;
		return count == statistic.count && earn == statistic.earn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, earn);
	}
}