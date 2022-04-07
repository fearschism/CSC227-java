import java.util.*;

	
	class Process{
		
		String jn;
		int jt;
		int jp;
		Process(String jn,int jt,int jp){
			this.jn=jn;
			this.jt=jt;
			this.jp=jp;
		}
		public int Prior() {
			return jp;
		}
		public int tPrior() {
			return jt;
		}
	}
	


	public class Cpu{
		int wt[];
		public void WaitingTime(Process proc[],int n) {
			wt=new int[n];
			wt[0]=0;
			for(int i =1;i<n;i++) 
				wt[i]=proc[i-1].jt + wt[i-1];
		}
		int ct[];
		public void CompTime(Process proc[] ,int n) {
			ct=new int[n];
			for(int i=0;i<n;i++) {
				ct[i]=proc[i].jt + wt[i];
			}
		}
		
		public void AvTime(Process proc[],int n) {
			WaitingTime(proc, n);
			CompTime(proc, n);
			
			
			int totalWT=0;
			int totalCT=0;
			for(int i=0;i<n;i++) {
				totalWT+=wt[i];
				totalCT+=ct[i];
				System.out.println("ProcessName:  "+proc[i].jn+"   burstTime(ms): "+proc[i].jt+"   Priority: "+proc[i].jp+"   WaitingTime(starting): "+wt[i]+"   ComplationTime(ending): "+ct[i]);
		}
			System.out.println("------------------------------------------------------------------");
			System.out.println("Avrig waiting time = "+(float)totalWT/(float)n);
			System.out.println("Avrig complation time = "+(float)totalCT/(float)n);
	}
		
		
		
		
		
		public void priorityScheduling(Process proc[],int n) {
			
			Arrays.sort(proc, new Comparator<Process>() {
				public int compare(Process a,Process b) {
					return a.Prior() - b.Prior();
				}
			});
			
			System.out.println("Order of processes by priority");
			System.out.println("");
			for(int i =0;i<n;i++) 
				System.out.print("  "+(i+1)+"-"+proc[i].jn+"  ");
			System.out.println("");
			System.out.println("------------------------------------------------------------------");
			
			
			AvTime(proc, n);
		}
		
		
	}

