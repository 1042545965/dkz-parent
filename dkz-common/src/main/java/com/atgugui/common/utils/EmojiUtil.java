package com.atgugui.common.utils;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

public class EmojiUtil {
	/**
	 * @param source
	 * @return
	 */
	public static String emojiToStr(String source) {
    	if (EmojiManager.isEmoji(source)) {
			//是表情字符
    		String parseToAliases = EmojiParser.parseToAliases(source);
    		return parseToAliases;
		}
		return source;
    }
	
	public static String strToEmoji(String source) {
		return EmojiParser.parseToUnicode(source);
    }
}
