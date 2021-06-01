package lotto.store;

import java.util.ArrayList;
import java.util.List;

import interaction.InputView;
import interaction.OutputView;
import lotto.LottoNumbers;
import lotto.generator.AutoLottoNumberGenerator;
import lotto.generator.MessageLottoNumberGenerator;

public class ManualLottoStore extends LottoStore {

    private static final String MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String MANUAL_AND_AUTOMATIC_COUNT_MESSAGE = "수동으로 %d장 자동으로 %d장 구매했습니다.";

    private final int manualCount;
    private final int automaticCount;

    public ManualLottoStore(Budget budget, int price, int manualCount) {
        super(budget, price);
        this.manualCount = manualCount;
        this.automaticCount = count - manualCount;
        valid(budget, price, manualCount);
    }

    private static void valid(Budget budget, int price, int manualCount) {
        int count = budget.get() / price;
        if (count < manualCount) {
            throw new IllegalArgumentException("구매 가능 개수보다 많은 로또를 수동으로 입력할 수 없습니다");
        }
    }

    @Override
    public Ticket produceLotto() {
        List<LottoNumbers> result = new ArrayList<>(count);
        result.addAll(manualInputLotto());

        OutputView.sendMessage(String.format(MANUAL_AND_AUTOMATIC_COUNT_MESSAGE, manualCount, automaticCount));
        AutoLottoNumberGenerator autoGenerator = new AutoLottoNumberGenerator();
        for (int i = 0; i < automaticCount; i++) {
            LottoNumbers lotto = autoGenerator.generate();
            result.add(lotto);
        }
        return new Ticket(result, budget);
    }

    private List<LottoNumbers> manualInputLotto() {
        OutputView.sendMessage(MANUAL_LOTTO_COUNT_MESSAGE);
        List<LottoNumbers> result = new ArrayList<>(manualCount);
        while (result.size() < manualCount) {
            result.add(MessageLottoNumberGenerator.of(InputView.lottoNumberMessage()));
        }
        return result;
    }

}
