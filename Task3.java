import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class DynamicJob implements Comparable<DynamicJob> {
    int id;
    int processingTime;
    int arrivalTime;

    public DynamicJob(int id, int processingTime, int arrivalTime) {
        this.id = id;
        this.processingTime = processingTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(DynamicJob other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }
}

public class Task3 {

    public static void main(String[] args) {
        try {
            File file = new File("src/task3-input.txt");
            Scanner scanner = new Scanner(file);
            List<DynamicJob> jobs = new ArrayList<>();
            int jobCount = 0;

            // Reading jobs from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\s+");

                // Parse the job information
                int id = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                int arrivalTime = Integer.parseInt(parts[2]);
                jobs.add(new DynamicJob(id, processingTime, arrivalTime));
                jobCount++; // Increment job count for each job read
            }
            scanner.close();

            jobs.sort((j1, j2) -> Integer.compare(j1.arrivalTime, j2.arrivalTime));

            PriorityQueue<DynamicJob> queue = new PriorityQueue<>();
            int currentTime = 0;
            int totalCompletionTime = 0;
            int index = 0;
            StringBuilder executionOrder = new StringBuilder("Execution order: [");

            while (index < jobs.size() || !queue.isEmpty()) {
                while (index < jobs.size() && jobs.get(index).arrivalTime <= currentTime) {
                    queue.add(jobs.get(index));
                    index++;
                }

                if (!queue.isEmpty()) {
                    DynamicJob job = queue.poll();
                    currentTime += job.processingTime;
                    totalCompletionTime += (currentTime - job.arrivalTime);
                    executionOrder.append(job.id).append(", ");
                } else {
                    currentTime = jobs.get(index).arrivalTime;
                }
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
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the input file. Please check the format of your input.");
            e.printStackTrace();
        }
    }
}