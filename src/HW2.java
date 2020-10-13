import java.util.*;

public class HW2 {
    public static void main(String args[]) {
        System.out.print("Numbers of Element, n : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        for (int i=0; i<n; i++){
            Random random = new Random();
            arr[i]=random.nextInt(1000);
        }
        Integer[] copiedarr = new Integer[n];
        System.arraycopy(arr, 0, copiedarr, 0, n);
        Comparator<Integer> C= new Comparator<Integer>(){
            public int compare(Integer d1, Integer d2){
                return d1.compareTo(d2);
            }
        };
        System.out.print("Before merge sort : ");
        for (int i=0; i<n; i++) System.out.print(arr[i] + " ");
        System.out.println();
        long startTime1 = System.nanoTime();
        mergeSort(arr,C);
        long stopTime1 = System.nanoTime();
        System.out.println("Running time for merge sort: " +(stopTime1-startTime1));
        System.out.print("After merge sort : ");
        for (int i=0; i<n; i++) System.out.print(arr[i] + " ");
        System.out.println("\n");

        System.out.print("Before quick sort : ");
        for (int i=0; i<n; i++) System.out.print(copiedarr[i] + " ");
        System.out.println();
        long startTime2 = System.nanoTime();
        quickSort(copiedarr,0,copiedarr.length-1,C);
        long stopTime2 = System.nanoTime();
        System.out.println("Running time for quick sort: " + (stopTime2-startTime2));
        System.out.print("After quick sort : ");
        for (int i=0; i<n; i++) System.out.print(copiedarr[i] + " ");
        System.out.println();
    }

    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++];
            else
                S[i + j] = S2[j++];
        }
    }

    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2) return;
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);
        K[] S2 = Arrays.copyOfRange(S, mid, n);
        mergeSort(S1, comp);
        mergeSort(S2, comp);
        merge(S1, S2, S, comp);
    }

    public static <K> void quickSort(K[] S, int left, int right, Comparator<K> comp){
        int ll=left;
        int rr=right;

        if(rr>ll){
            K pivot = S[(ll+rr)/2];
            while (ll<=rr){
                while(ll<right && comp.compare(S[ll],pivot)<0){
                    ll+=1;
                }
                while(rr>left && comp.compare(S[rr],pivot)>0){
                    rr-=1;
                }
                if(ll<=rr){
                    K t= S[ll];
                    S[ll]=S[rr];
                    S[rr]=t;
                    ll+=1;
                    rr-=1;
                }
            }
            if(left<rr){
                quickSort(S,left,rr,comp);
            }
            if(ll<right){
                quickSort(S,ll,right,comp);
            }
        }
    }
}
