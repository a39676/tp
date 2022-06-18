package littleTool.cashFlow.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import auxiliaryCommon.pojo.type.TimeUnitType;
import littleTool.cashFlow.pojo.dto.CashFlowDTO;

public class CashFlowCalService {

	private static List<CashFlowDTO> paymentList = new ArrayList<>();
	private static List<CashFlowDTO> incomeList = new ArrayList<>();
	private static BigDecimal cnyUsdRate = new BigDecimal(6.5);
	private static BigDecimal filOutput = new BigDecimal(150);
	private static BigDecimal filPrice = new BigDecimal(3);

	public static BigDecimal listResult(List<CashFlowDTO> list) {
		BigDecimal dailyTotal = BigDecimal.ZERO;
		BigDecimal tmpDailyAmount = null;

		for (CashFlowDTO dto : list) {
			if (TimeUnitType.day.equals(dto.getTimeUnit())) {
				tmpDailyAmount = dto.getAmount();
			} else if (TimeUnitType.week.equals(dto.getTimeUnit())) {
				tmpDailyAmount = dto.getAmount().divide(new BigDecimal(7), 4, RoundingMode.HALF_UP);
			} else if (TimeUnitType.month.equals(dto.getTimeUnit())) {
				tmpDailyAmount = dto.getAmount().divide(new BigDecimal(30.5), 4, RoundingMode.HALF_UP);
			} else if (TimeUnitType.year.equals(dto.getTimeUnit())) {
				tmpDailyAmount = dto.getAmount().divide(new BigDecimal(365), 4, RoundingMode.HALF_UP);
			}
			dailyTotal = dailyTotal.add(tmpDailyAmount);
			
			System.out.println(dto.getFlowName() + ", " + dto.getAmount() + ", 日流水: " + tmpDailyAmount );
		}

		return dailyTotal;
	}

	public static void addFlow(CashFlowDTO dto) {
		if (dto.getAmount().compareTo(BigDecimal.ZERO) > 0) {
			incomeList.add(dto);
		} else {
			paymentList.add(dto);
		}
	}

	public static void main(String[] args) {
		CashFlowDTO dto = new CashFlowDTO();
		dto.setFlowName("基础");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(13000));
		addFlow(dto);

		dto = new CashFlowDTO();
		dto.setFlowName("保险");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-25000));
		addFlow(dto);

		dto = new CashFlowDTO();
		dto.setFlowName("FIL机房");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-100000));
		addFlow(dto);

		dto = new CashFlowDTO();
		dto.setFlowName("舞蹈课");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-5000));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("钢琴课");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-4000));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("FIL 产出");
		dto.setTimeUnit(TimeUnitType.week);
		dto.setAmount(filOutput.multiply(filPrice).multiply(cnyUsdRate));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("卡数利息");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-700000D * 0.0005 * 15));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("拉卡周转手续费");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-700000D * 0.0063));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("vultr机房");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-15).multiply(cnyUsdRate));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("vultr机房2");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-60));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("月常支出");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-3000));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("学费");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-3200 * 9));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("138 月租");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-25));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("189 月租");
		dto.setTimeUnit(TimeUnitType.month);
		dto.setAmount(new BigDecimal(-250));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("过年高铁票");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-213.5 * 2.5 * 2));
		addFlow(dto);
		
		dto = new CashFlowDTO();
		dto.setFlowName("过年使费");
		dto.setTimeUnit(TimeUnitType.year);
		dto.setAmount(new BigDecimal(-5000));
		addFlow(dto);
		
//		dto = new CashFlowDTO();
//		dto.setFlowName("跳槽收入");
//		dto.setTimeUnit(TimeUnitType.month);
//		dto.setAmount(new BigDecimal(3500));
//		addFlow(dto);
//		
//		dto = new CashFlowDTO();
//		dto.setFlowName("现场办公通勤");
//		dto.setTimeUnit(TimeUnitType.month);
//		dto.setAmount(new BigDecimal(-16 * 22));
//		addFlow(dto);
//		
//		dto = new CashFlowDTO();
//		dto.setFlowName("现场办公午餐");
//		dto.setTimeUnit(TimeUnitType.month);
//		dto.setAmount(new BigDecimal(-20 * 22));
//		addFlow(dto);
		
		BigDecimal incomeDaily = listResult(incomeList);
		BigDecimal paymentDaily = listResult(paymentList);

		System.out.println(incomeDaily);
		System.out.println(paymentDaily);
		System.out.println(incomeDaily.add(paymentDaily));
	}
}
