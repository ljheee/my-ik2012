package com.ljheee.ik;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by lijianhua04 on 2018/9/16.
 */
public class Test {


    public static void main(String[] args) throws IOException {


        String content = "我是中国人";

        //第2个参数为true，表示会查找到最深的节点。
        IKSegmenter ik = new IKSegmenter(new StringReader(content), false);
        Lexeme l = null;
        while ((l = ik.next()) != null) {
            System.out.println(l.toString());
            String word = l.getLexemeText();
            System.out.println("word=" + word);

        }
    }
}
