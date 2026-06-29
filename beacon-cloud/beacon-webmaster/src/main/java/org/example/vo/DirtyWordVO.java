package org.example.vo;

import lombok.Data;

/**
 * DirtyWordVO - 敏感词VO
 * 字段名严格匹配前端 dirtyword.js（路径/sys/message/*）
 * 映射：dirtyword同名, owntype←extend1, creater←extend2
 */
@Data
public class DirtyWordVO {
    private Long id;
    private String dirtyword;
    private String owntype;
    private String creater;
}
