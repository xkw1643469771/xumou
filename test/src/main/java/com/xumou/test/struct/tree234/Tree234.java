package com.xumou.test.struct.tree234;

import java.util.Arrays;

/**
 *
 */
public class Tree234 {

    private Node root;

    public static void main(String[] args) {
        Node node = new Node();
        for (int i = 0; i < 3; i++) {
            node.insert(i);
            System.out.println(node.printItems());
        }
        for (int i = 0; i < 3; i++) {
            node.delete(0);
            System.out.println(node.printItems());
        }
    }

    private static class Node {

        private Comparable[] items;
        private Node[] nodes;


        public Node(){
            items = new Comparable[3];
            nodes = new Node[4];
        }

        /** 数据项是满的 */
        public boolean isFull(){
            for (int i = 0; i < items.length; i++) {
                if(items[i] == null)
                    return false;
            }
            return true;
        }

        /** 该节点是叶子节点 */
        public boolean isLeaf(){
            for (int i = 0; i < nodes.length; i++) {
                if(nodes[i] != null){
                    return false;
                }
            }
            return true;
        }

        /** 插入项 */
        public int insert(Comparable obj){
            int idx = 0;
            for (int i = items.length - 1; i >= 0; i--) {
                if(items[i] == null)
                    continue;
                if(items[i].compareTo(obj) > 0) {
                    items[i + 1] = items[i];
                }else{
                    idx = i + 1;
                    break;
                }
            }
            items[idx] = obj;
            return idx;
        }

        /** 删除项 */
        public void delete(int idx){
            for (; idx < items.length - 1; idx++) {
                items[idx] = items[idx+1];
            }
            items[idx] = null;
        }

        /** 打印项 */
        public String printItems() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < items.length; i++) {
                if(items[i] == null)
                    break;
                sb.append(items[i]).append(",");
            }
            if(sb.toString().endsWith(",")){
                sb.delete(sb.length() - 1, sb.length());
            }
            sb.append("]");
            return sb.toString();
        }
    }

}