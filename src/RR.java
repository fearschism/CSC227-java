class rrProcess{
		
		String jn;
		int jt;
		rrProcess(String jn,int jt){
			this.jn=jn;
			this.jt=jt;
		}
}
public class RR {
	int wt[];
	public void WaitingTime(rrProcess proc[],int n) {
		wt=new int[n];
		wt[0]=0;
		for(int i =1;i<n;i++) 
			wt[i]=proc[i-1].jt + wt[i-1];
	}
	int ct[];
	public void CompTime(rrProcess proc[] ,int n) {
		ct=new int[n];
		for(int i=0;i<n;i++) {
			ct[i]=proc[i].jt + wt[i];
		}
	}
	
	public void AvTime(rrProcess proc[],int n){
		WaitingTime(proc, n);
		CompTime(proc, n);
		
		
		int totalWT=0;
		int totalCT=0;
		for(int i=0;i<n;i++) {
			totalWT+=wt[i];
			totalCT+=ct[i];
			System.out.println("ProcessName:  "+proc[i].jn+"   burstTime(ms): "+proc[i].jt+"   WaitingTime(starting): "+wt[i]+"   ComplationTime(ending): "+ct[i]);
	}
		System.out.println("------------------------------------------------------------------");
		System.out.println("Avrig waiting time = "+(float)totalWT/(float)n);
		System.out.println("Avrig complation time = "+(float)totalCT/(float)n);
}
	public void RRscheduling(rrProcess proc[],int n) {
		System.out.println("Order of processes by (File order)");
		System.out.println("");
		for(int i =0;i<n;i++) 
			System.out.print("  "+(i+1)+"-"+proc[i].jn+"  ");
		System.out.println("");
		System.out.println("------------------------------------------------------------------");
		
		AvTime(proc, n);
}
}
