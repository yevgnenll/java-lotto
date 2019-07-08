package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {
    private Set<LottoNumber> lotto;

    public Lotto(Set<LottoNumber> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException();
        }
        this.lotto = lotto;
    }

    public static Lotto of(String lotto) {
        Set<Integer> lottoSet = Arrays.stream(lotto.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        return new Lotto(makeLottoNumberSet(lottoSet.stream()));
    }

    public static Lotto of(List<Integer> lotto) {
        return new Lotto(makeLottoNumberSet(lotto.stream()));
    }

    public static Lotto of(Set<Integer> lotto) {
        return new Lotto(makeLottoNumberSet(lotto.stream()));
    }

    private static Set<LottoNumber> makeLottoNumberSet(Stream<Integer> stream) {
        return stream
                .map(LottoNumber::of)
                .collect(Collectors.toSet());
    }

    public Set<LottoNumber> getLotto() {
        return lotto;
    }

    public int match(Lotto winningLotto) {
        return (int) this.lotto.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lotto.contains(lottoNumber);
    }
}