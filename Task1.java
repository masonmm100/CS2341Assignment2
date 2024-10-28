import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

class Job implements Comparable<Job> {
    int id;
    int processingTime;

    public Job(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }
}

public class Task1 {

    public static void main(String[] args) {
        try {
            File file = new File("src/task1-input.txt");
            Scanner scanner = new Scanner(file);
            PriorityQueue<Job> queue = new PriorityQueue<>();
            int jobCount = 0;

            // Reading jobs from the file
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                int processingTime = scanner.nextInt();
                queue.add(new Job(id, processingTime));
                jobCount++;
            }
            scanner.close();

            int currentTime = 0;
            int totalCompletionTime = 0;
            StringBuilder executionOrder = new StringBuilder("Execution order: [");

            while (!queue.isEmpty()) {
                Job job = queue.poll();
                currentTime += job.processingTime;
                totalCompletionTime += currentTime;
                executionOrder.append(job.id).append(", ");
            }

            if (executionOrder.length() > 18) {
                executionOrder.setLength(executionOrder.length() - 2);
            }
            executionOrder.append("]");

            double averageCompletionTime = (double) totalCompletionTime / jobCount;
            System.out.println(executionOrder);
            System.out.printf("Average completion time: %.1f\n", averageCompletionTime);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}