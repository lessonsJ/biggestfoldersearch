import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int STEP = 1024;
    private static final int ONE = 1;

    public static void main(String[] args){
        String folderPath = "/Project/Исходная папка/";
        File file = new File(folderPath);

        FolderSizeCalc calc = new FolderSizeCalc(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calc);
        System.out.println("Размер папки/файла = " + getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable(getHumanReadableSize(size)));
    }

    public static String getHumanReadableSize(long size){
        long kb = size / STEP;
        long mb = kb / STEP;
        long gb = mb / STEP;
        long tb = gb / STEP;

        if (kb > ONE) {
            return(kb + " Kb");
        }

        if (mb > ONE) {
            return(mb + " Mb");
        }

        if (gb > ONE) {
            return (mb + " Gb");
        }

        if (tb > ONE) {
            return (mb + " Tb");
        }
        return Long.toString(size); // B
    }

    public static long getSizeFromHumanReadable(String size){

        long getSize = Long.parseLong(size.replaceAll("\\D+",""));
        String getName = size.substring(size.length() - 2).trim();
        System.out.println(getName + "  " + getSize);
        if (getName.equals("B")){
            return getSize;
        }
        if (getName.equals("Kb")){
            return getSize * STEP;
        }
        if (getName.equals("Gb")){
            return getSize * STEP * STEP;
        }
        if (getName.equals("Tb")){
            return getSize * STEP * STEP * STEP;
        }
        return 0;
    }

    private static void message(String messageName) {
        System.out.println(messageName);
    }
}
