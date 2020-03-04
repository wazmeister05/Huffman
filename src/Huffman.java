import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    public static void printCode(HuffmanNode root, String s){
        if(root.left == null && root.right == null && Character.isLetter(root.c)){
            System.out.println(root.c + ":" + s);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static class HuffmanNode{
        int data;
        char c;

        HuffmanNode left;
        HuffmanNode right;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        String text = "Batman";
        int n = text.length();
        char[] charArray = text.toCharArray();

        int countB = n - text.replace("B", "").length();
        int countA = n - text.replace("a", "").length();
        int countT = n - text.replace("t", "").length();
        int countM = n - text.replace("m", "").length();
        int countN = n - text.replace("n", "").length();


        int[] charFreq = {countB, countA, countT, countM, countA, countN};         //Bx1 Ax2 Tx1 Mx1 Ax2 Nx1

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(n, new MyComparator());

        for(int i = 0; i < n; i++){
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];

            hn.left = null;
            hn.right = null;

            queue.add(hn);
        }

        HuffmanNode root = null;

        while(queue.size() > 1){
            HuffmanNode x = queue.peek();
            queue.poll();
            HuffmanNode y = queue.peek();
            queue.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;       //freq at which the characters occur

            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            queue.add(f);
        }
        printCode(root, "");
    }

    static class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y){
            return x.data - y.data;
        }
    }
}
