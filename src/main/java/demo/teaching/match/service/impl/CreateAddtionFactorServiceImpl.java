package demo.teaching.match.service.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import demo.teaching.match.pojo.bo.AdditionFactor3ElementBO;
import demo.teaching.match.pojo.bo.AdditionFactorBO;
import demo.teaching.match.pojo.bo.SubtractionFactorBO;

public class CreateAddtionFactorServiceImpl {
	
	public static void main(String[] args) {
		CreateAddtionFactorServiceImpl t = new CreateAddtionFactorServiceImpl();
		Set<AdditionFactorBO> resultSet = t.createRandomAddtion(3, 11, 15);
		for(AdditionFactorBO ele : resultSet) {
			System.out.println(ele.getEquationString());
		}
		
		Set<AdditionFactor3ElementBO> resultSet2 = t.createRandomAddtion3Element(3, 11, 10);
		for(AdditionFactor3ElementBO ele: resultSet2) {
			System.out.println(ele.getEquationString());
		}
		
		Set<SubtractionFactorBO> resultSet3 = t.createRandomSubtraction(1, 11, 5);
		for(SubtractionFactorBO ele: resultSet3) {
			System.out.println(ele.getEquationString());
		}
	}

	public Set<AdditionFactorBO> createRandomAddtion(Integer min, Integer max, Integer size) {
		AdditionFactorBO bo = null;
		Random r = new Random();
		Set<AdditionFactorBO> resultSet = new HashSet<>();
		while(resultSet.size() < size) {
			bo = new AdditionFactorBO();
			bo.setFactor1(0D + r.nextInt(max - min) + min);
			bo.setFactor2(0D + r.nextInt(max - min) + min);
			resultSet.add(bo);
		}
		
		return resultSet;
	}
	
	public Set<AdditionFactor3ElementBO> createRandomAddtion3Element(Integer min, Integer max, Integer size) {
		AdditionFactor3ElementBO bo = null;
		Random r = new Random();
		Set<AdditionFactor3ElementBO> resultSet = new HashSet<>();
		while(resultSet.size() < size) {
			bo = new AdditionFactor3ElementBO();
			bo.setFactor1(0D + r.nextInt(max - min) + min);
			bo.setFactor2(0D + r.nextInt(max - min) + min);
			bo.setFactor3(0D + r.nextInt(max - min) + min);
			resultSet.add(bo);
		}
		
		return resultSet;
	}
	
	public Set<SubtractionFactorBO> createRandomSubtraction(Integer min, Integer max, Integer size) {
		SubtractionFactorBO bo = null;
		Random r = new Random();
		Set<SubtractionFactorBO> resultSet = new HashSet<>();
		while(resultSet.size() < size) {
			bo = new SubtractionFactorBO();
			bo.setFactor2(0D + r.nextInt(max - min) + min);
			bo.setFactor1(0D + r.nextInt(max - bo.getFactor2().intValue()) + bo.getFactor2());
			resultSet.add(bo);
		}
		
		return resultSet;
	}
	
}
