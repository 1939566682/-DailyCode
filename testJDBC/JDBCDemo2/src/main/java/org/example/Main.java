package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-15 13:49
 */

public class Main {
	
	private static final LocalDate START_TIME = LocalDate.of(2006, 1, 1);
	private static final Path ROOT_PATH = Paths.get("C:\\");
	
	public static void main(String[] args) {
		System.out.println("开始遍历 C 盘，筛选修改日期 > 2006-01-01 的 .exe 文件...");
		
		try {
			Files.walkFileTree(ROOT_PATH, new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
					if (file.getFileName().toString().toLowerCase().endsWith(".exe")) {
						LocalDate modifiedDate = attrs.lastModifiedTime()
								.toInstant()
								.atZone(ZoneId.systemDefault())
								.toLocalDate();
						if (modifiedDate.isAfter(START_TIME)) {
							System.out.println(file.toAbsolutePath().normalize());
						}
					}
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					return FileVisitResult.CONTINUE;
				}
			});
			System.out.println("\n扫描完毕。");
		} catch (IOException e) {
			System.err.println("遍历启动失败：" + e.getMessage());
		}
	}
}
