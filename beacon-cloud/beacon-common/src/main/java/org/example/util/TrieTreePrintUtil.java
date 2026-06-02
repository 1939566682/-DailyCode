package org.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TrieTreePrintUtil
 *
 * @author Yang QingBo
 * @date 2026-06-02 20:17
 * @description 修复了isEnd字段类型转换异常，兼容null、Integer、String三种类型
 */
public class TrieTreePrintUtil {
	
	// ====================== Java 8 兼容的字符串重复工具方法 ======================
	private static String repeat(String str, int count) {
		if (count <= 0) return "";
		StringBuilder sb = new StringBuilder(str.length() * count);
		for (int i = 0; i < count; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	// ====================== 方式1：空格缩进打印（简洁清晰） ======================
	public static void printTreeByBlank(Map<String, Object> root) {
		dfsBlank(root, "root", 0);
	}
	
	private static void dfsBlank(Map<String, Object> node, String nodeName, int level) {
		String indent = repeat("  ", level);
		// 修复：安全获取isEnd值，兼容null、Integer、String类型
		Object isEndObj = node.get("isEnd");
		String isEndStr = getSafeIsEndString(isEndObj);
		System.out.printf("%s%s : isEnd=%s%n", indent, nodeName, isEndStr);
		
		// 遍历所有子节点（排除isEnd字段）
		for (Map.Entry<String, Object> entry : node.entrySet()) {
			String key = entry.getKey();
			if ("isEnd".equals(key)) continue;
			Map<String, Object> child = (Map<String, Object>) entry.getValue();
			dfsBlank(child, key, level + 1);
		}
	}
	
	// ====================== 方式2：标准树形符号 │ ├ └ 可视化 ======================
	public static void printTreeBySymbol(Map<String, Object> root) {
		dfsSymbol(root, "root", "", true);
	}
	
	private static void dfsSymbol(Map<String, Object> node, String nodeName, String prefix, boolean isLast) {
		// 修复：安全获取isEnd值，兼容null、Integer、String类型
		Object isEndObj = node.get("isEnd");
		String isEndStr = getSafeIsEndString(isEndObj);
		System.out.printf("%s%s : isEnd=%s%n", prefix + (isLast ? "└── " : "├── "), nodeName, isEndStr);
		
		// 筛选子节点key
		List<String> childKeys = new ArrayList<>();
		for (String k : node.keySet()) {
			if (!"isEnd".equals(k)) childKeys.add(k);
		}
		
		for (int i = 0; i < childKeys.size(); i++) {
			String childKey = childKeys.get(i);
			Map<String, Object> childNode = (Map<String, Object>) node.get(childKey);
			boolean last = i == childKeys.size() - 1;
			// 拼接下层前缀
			String newPrefix = prefix + (isLast ? "    " : "│   ");
			dfsSymbol(childNode, childKey, newPrefix, last);
		}
	}
	
	// ====================== 附加：提取所有完整词语（isEnd=1） ======================
	public static List<String> collectAllWord(Map<String, Object> root) {
		List<String> res = new ArrayList<>();
		dfsCollect(root, new ArrayList<>(), res);
		return res;
	}
	
	private static void dfsCollect(Map<String, Object> node, List<String> path, List<String> res) {
		// 修复：安全判断isEnd是否为1，兼容Integer(1)和String("1")
		if (isEndTrue(node.get("isEnd"))) {
			res.add(String.join("", path));
		}
		for (Map.Entry<String, Object> e : node.entrySet()) {
			String k = e.getKey();
			if ("isEnd".equals(k)) continue;
			path.add(k);
			dfsCollect((Map<String, Object>) e.getValue(), path, res);
			path.remove(path.size() - 1);
		}
	}
	
	// ====================== 新增：安全工具方法（核心修复） ======================
	/**
	 * 安全获取isEnd字段的字符串表示，兼容null、Integer、String类型
	 */
	private static String getSafeIsEndString(Object isEndObj) {
		if (isEndObj == null) {
			return "null";
		}
		if (isEndObj instanceof Integer) {
			return String.valueOf(isEndObj);
		}
		if (isEndObj instanceof String) {
			return (String) isEndObj;
		}
		// 其他类型直接调用toString()
		return isEndObj.toString();
	}
	
	/**
	 * 安全判断isEnd是否为真（值为1或"1"）
	 */
	private static boolean isEndTrue(Object isEndObj) {
		if (isEndObj == null) {
			return false;
		}
		if (isEndObj instanceof Integer) {
			return (Integer) isEndObj == 1;
		}
		if (isEndObj instanceof String) {
			return "1".equals(isEndObj);
		}
		return false;
	}
}
