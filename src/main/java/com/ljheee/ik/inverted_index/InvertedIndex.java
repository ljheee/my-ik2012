package com.ljheee.ik.inverted_index;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 倒排索引实现
 * 简单版
 */
public class InvertedIndex {


    // 用于保存  单词----倒排列表[文档id列表]
    private Map<String, List<String>> map = new HashMap<String, List<String>>();

    private Map<String, Integer> times = new HashMap<String, Integer>();

    /**
     * 创建 倒排索引
     * 最关键的步骤：是完成文档 分词，并构建
     * key -> docid
     * 单词----倒排列表[文档id列表]
     *
     * @param docId   文档ID
     * @param content 文档内容
     * @throws Exception
     */
    public void creatIndex(Integer docId, String content) throws Exception {
        // 分词
        IKSegmenter ik = new IKSegmenter(new StringReader(content), false);
        Lexeme l = null;
        while ((l = ik.next()) != null) {
            String word = l.getLexemeText();
            // 写入Map结构当中
            if (map.containsKey(word)) {
                List<String> list = map.get(word);
                list.add(docId.toString());
                map.put(word, list);
            } else { // 当前还没包含词
                List<String> list = new ArrayList<String>();
                list.add(docId.toString());
                map.put(word, list);
                times.put(word, 1);
            }
        }
    }

    /**
     * 从倒排索引中 查找关键字
     * 返回包含关键字的 文档ID列表
     *
     * @param invertedIndex
     * @param key
     * @return
     */
    public List<String> search(InvertedIndex invertedIndex, String key) {
        return invertedIndex.map.get(key);
    }

    public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();
        try {
            invertedIndex.creatIndex(1, "创建 倒排索引，最关键的步骤：是完成文档 分词，并构建单词----倒排列表[文档id列表]");
            invertedIndex.creatIndex(2, "最初的步骤：需要把所有文档 都进行分词，存储起来。倒排索引的前提是完成分词。");

            String searchWord = "索引";
            System.out.println(searchWord + "搜索结果:");

            List<String> docIds = invertedIndex.search(invertedIndex, searchWord);
            docIds.stream().forEach(System.out::println);

//            List<String> lists = invertedIndex.map.get(searchWord);
//            if (lists != null && !lists.isEmpty()) {
//                for (String str : lists) {
//                    System.out.print(str + " ");
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
