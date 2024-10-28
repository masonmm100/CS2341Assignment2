import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

class PriorityJob implements Comparable<PriorityJob> {
    int id;
    int processingTime;
    int priority;

    public PriorityJob(int id, int processingTime, int priority) {
        this.id = id;
        this.processingTime = processingTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityJob other) {
        if (this.priority == other.priority) {
            return Integer.compare(this.processingTime, other.processingTime);
        }
        return Integer.compare(this.priority, other.priority);
    }
}

public class Task2 {

    public static void main(String[] args) {
        try {
            File file = new File("src/task2-input.txt");
            Scanner scanner = new Scanner(file);
            PriorityQueue<PriorityJob> queue = new PriorityQueue<>();
            int jobCount = 0;

            // Reading jobs from the file
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                int processingTime = scanner.nextInt();
                int priority = scanner.nextInt();
                queue.add(new PriorityJob(id, processingTime, priority));
                jobCount++;
            }
            scanner.close();

            int currentTime = 0;
            int totalCompletionTime = 0;
            StringBuilder executionOrder = new StringBuilder("Execution order: [");

            while (!queue.isEmpty()) {
                PriorityJob job = queue.poll();
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