import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args){
        String folderPath = "/Project/Исходная папка/";
        File file = new File(folderPath);

        FolderSizeCalc calc = new FolderSizeCalc(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calc);
        System.out.println(size);
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files){
            sum += getFolderSize(file);
        }
        return sum;
    }
}
