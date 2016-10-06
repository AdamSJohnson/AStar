import java.io.*;
import java.util.*;

public class AStar{
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("Data.dat");
        Scanner sc = new Scanner(f);
        Map<String, Node> map = new HashMap<>();
        
        //go through each line in the file
        while(sc.hasNextLine()){
            String name = sc.nextLine();

            int distance = Integer.parseInt(sc.nextLine());

            int n = Integer.parseInt(sc.nextLine());

            Edge[] edges = new Edge[n];
            for(int i = 0; i < n; i++){
                String edge = sc.nextLine();

                Scanner ss = new Scanner(edge);
                //get the name
                String to = ss.next();
                
                //get the distance to the node
                int dis = Integer.parseInt(ss.next());
                
                //get the road condition
                int rc = Integer.parseInt(ss.next());
                
                //get the danger level
                double dan = Double.parseDouble(ss.next());
                
                //put the edge into the array
                edges[i] = new Edge(to, dis, rc, dan);
                
            }
            Node nod = new Node(name, distance, edges);
            map.put(name, nod);

            sc.nextLine();
        }
        //System.out.println(map);
        
        //at this point the map is assembled we can start doing our
    }
    
}

class Node{
    String name;
    int distance;
    Edge[] edges;
    public Node(String name, int distance, Edge[] e){
        this.name = name;
        this.distance = distance;
        this.edges = e;  
    }
    
    public String toString(){
        return Arrays.toString(edges);
    }
}

class Edge{
    String to;
    int distance;
    int roadcon;
    double danger;
    
    public Edge(String a, int dis, int rc, double dan){
        to = a;
        distance = dis;
        roadcon = rc;
        danger = dan;
    }
    
    public String toString(){
        return to;
    }
}