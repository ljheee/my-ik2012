package com.ljheee.ik.trie;

/**
 * [字母]字典树
 * <p>
 * 题目：给出一字符串，找出以xxx开头的个数。
 */
public class TrieTree {

    static class TreeNode {

        public final static int MAX_SIZE = 26;
        int time;        //表示出现多少次了
        char ch;        //表示当前的字符
        TreeNode[] childs;

        public TreeNode() {
            time = 1;
            childs = new TreeNode[MAX_SIZE];
        }
    }


    /**
     * 构建字典树
     * 插入字符串 str
     *
     * @param node 传进来的是根节点
     * @param str  传进来的字符串
     */
    private void creatTree(TreeNode node, String str) {
        if (str == null || str.trim().length() <= 0) return;

        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] > 'z' || cs[i] < 'a') continue; //a-z

            int loc = cs[i] - 'a'; //0~26 ascii
            if (node.childs[loc] == null) {
                node.childs[loc] = new TreeNode();
                node.childs[loc].ch = cs[i];
            } else {
                node.childs[loc].time++;
            }
            //node.ch = cs[i];
            node = node.childs[loc];
        }
    }

    /**
     * 查找以prefix为前缀的字符串 个数
     *
     * @param node
     * @param prefix 查找的字符串
     * @return
     */
    private int searchPrefix(TreeNode node, String prefix) {
        if (prefix == null || prefix.trim().length() <= 0) return -1;

        char[] cs = prefix.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int loc = cs[i] - 'a';
            if (node.childs[loc] == null) {
                return 0;
            } else {
                node = node.childs[loc];
            }
        }
        return node.time;
    }

    public static void main(String[] args) {
        String sts[] = {"baaa", "ban", "be", "acm"};
        TrieTree trieTree = new TrieTree();
        TreeNode rootNode = new TreeNode();
        for (String s : sts) {
            s = s.toLowerCase();        //转小写 不能有特殊字段 只能是a-z
            trieTree.creatTree(rootNode, s);
        }

        System.out.println("字典树建立完成");

        String prefix = "ba";
        int time = trieTree.searchPrefix(rootNode, prefix);

        System.out.println(prefix + "为前缀的个数:" + time);
    }


}
