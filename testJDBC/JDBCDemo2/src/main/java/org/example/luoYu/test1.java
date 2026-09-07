package org.example.luoYu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * test
 *
 * @author Yang QingBo
 * @date 2026-09-07 15:33
 * @description
 */
//A1. 集合操作 - 分组统计
//题目：
//
//给定一个订单列表 List<Order>，Order 包含字段：userId（用户ID）、amount（金额）、status（状态：PAID/PENDING/CANCELLED）。
//
//请实现一个方法，返回 Map<Long, Map<String, BigDecimal>>，即按 userId 分组，再按 status 分组，统计每个用户每种状态的订单总金额。
public class test1 {
	
	static class Order {
		private Long userId;
		private BigDecimal amount;
		private String status;  //PAID/PENDING/CANCELLED
		
		public Order(Long userId, BigDecimal amount, String status) {
			this.userId = userId;
			this.amount = amount;
			this.status = status;
		}
		
		public Long getUserId() {
			return userId;
		}
		
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		
		public BigDecimal getAmount() {
			return amount;
		}
		
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
	}
	
	public static void main(String[] args) {
		List<Order> orders = List.of(
				new Order(1L, new BigDecimal(100), "PAID"),
				new Order(2L, new BigDecimal(50), "PAID"),
				new Order(3L, new BigDecimal(200), "PENDING"),
				new Order(4L, new BigDecimal(300), "PENDING"),
				new Order(5L, new BigDecimal(400), "CANCELLED")
		);
		System.out.println(sumByUserAndStatus(orders));
		
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		int sum = numbers.parallelStream().reduce(0, (a, b) -> a + b,Integer::add);
		System.out.println(sum);
	}
	
	public static Map<Long, Map<String, BigDecimal>> sumByUserAndStatus(List<Order> orders) {
		return orders.stream().collect(Collectors.groupingBy(Order::getUserId,
				Collectors.groupingBy(Order::getStatus, Collectors.reducing(BigDecimal.ZERO, Order::getAmount, BigDecimal::add))));
	}
	
}
