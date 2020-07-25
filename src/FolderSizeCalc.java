import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalc extends RecursiveTask<Long> {
        private final File folder;

        public FolderSizeCalc(File folder) {
            this.folder = folder;
        }

        @Override
        protected Long compute() {
            if(folder.isFile()){
                return folder.length();
            }
            long sum = 0;

            List<FolderSizeCalc> subTasks = new LinkedList<>();
            File[] files = folder.listFiles();

            for(File file : files) {
                FolderSizeCalc task = new FolderSizeCalc(file);
                task.fork(); // запустим асинхронно
                subTasks.add(task);
            }

            for(FolderSizeCalc task : subTasks) {
                sum += task.join(); // дождёмся выполнения задачи и прибавим результат
            }

            return sum;
        }

    }
