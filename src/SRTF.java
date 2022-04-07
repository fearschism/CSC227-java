import java.util.*;

class srtfProcess
{
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
     
    srtfProcess(int pid, int bt, int art){
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}
 
public class SRTF
{
    // Method to find the waiting time for all
    // processes
	int wt[];
    public void findWaitingTime(srtfProcess proc[], int n) {
        
    	wt=new int[n];
        
    	int rt[] = new int[n];
      
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;
      
        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
      
        // Process until all processes gets
        // completed
        while (complete != n) {
      
            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((proc[j].art <= t) &&
                  (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
      
            if (check == false) {
                t++;
                continue;
            }
      
            // Reduce remaining time by one
            rt[shortest]--;
      
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;
      
            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {
      
                // Increment complete
                complete++;
                check = false;
      
                // Find finish time of current
                // process
                finish_time = t + 1;
      
                // Calculate waiting time
                wt[shortest] = finish_time -
                             proc[shortest].bt -
                             proc[shortest].art;
      
                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }
     int tat[];
    // Method to calculate turn around time
    public void findTurnAroundTime(srtfProcess proc[], int n) {
    	
    	tat=new int[n];
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }
      
    // Method to calculate average time
    public void srtfScheduling(srtfProcess proc[], int n)
    {
        
        int  total_wt = 0, total_tat = 0;
      
        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n);
      
        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n);
      
        // Display processes along with all
        // details
       
      
        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt+= wt[i];
            total_tat+=tat[i];
            System.out.println("ProcessId:  "+proc[i].pid+"   burstTime(ms): "+proc[i].bt+"   ArrivelTime(ms): "+proc[i].art+"   WaitingTime: "+wt[i]+"   ComplationTime: "+tat[i]);
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average waiting time = " +
                          (float)total_wt / (float)n);
        System.out.println("Average turn around time = " +
                           (float)total_tat / (float)n);
    }
}