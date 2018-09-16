package com.ljheee.ik;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;

/**
 * Created by lijianhua04 on 2018/9/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String text = "ik支持两种分词模式：最细粒度和智能分词模式，如果构造函数参数为false，那么使用最细粒度分词。";
        Analyzer analyzer = new IKAnalyzer(false);
        StringReader reader = new StringReader(text);
        TokenStream ts = analyzer.tokenStream("", reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        while (ts.incrementToken()) {
            System.out.println(term.toString());
        }
        analyzer.close();
        reader.close();
    }
}
