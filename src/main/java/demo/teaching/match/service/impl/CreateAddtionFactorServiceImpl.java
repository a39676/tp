package demo.teaching.match.service.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import demo.teaching.match.pojo.bo.AdditionFactorBO;

public class CreateAddtionFactorServiceImpl {
	
	public static void main(String[] args) {
		CreateAddtionFactorServiceImpl t = new CreateAddtionFactorServiceImpl();
		Set<AdditionFactorBO> resultSet = t.createRandomAddtion(3, 10, 20);
		
		for(AdditionFactorBO ele : resultSet) {
			System.out.println(ele.getFactor1().intValue() + " + " + ele.getFactor2().intValue() + " = ");
		}
	}

	public Set<AdditionFactorBO> createRandomAddtion(Integer min, Integer max, Integer size) {
		AdditionFactorBO bo = null;
		Random r = new Random();
		Set<AdditionFactorBO> resultSet = new HashSet<>();
		max = max + 1;
		while(resultSet.size() < size) {
			bo = new AdditionFactorBO();
			bo.setFactor1(0D + r.nextInt(max - min) + min);
			bo.setFactor2(0D + r.nextInt(max - min) + min);
			resultSet.add(bo);
		}
		
		return resultSet;
	}
}
