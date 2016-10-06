import java.io.*;
import java.util.*;

public class AStar{
    public static void main(String[] args) throws FileNotFoundException{
        //yes my data file is hard coded
        File f = new File("Data.dat");
        
        //setup a scanner on that file
        Scanner sc = new Scanner(f);
        
        //create my map
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
        //heuristical stuff
        boolean s = true;
        Scanner ls = new Scanner(System.in);
        String start = "";
        while(s){
            System.out.println("Please pick a city to start from:");
            for(String name : map.keySet()){
                System.out.println(name.replaceAll("_", " "));
            }
            start = ls.nextLine();
            System.out.println(start);
            if(map.containsKey(start.replaceAll(" ", "_"))){
                s = !s;
                System.out.println("City: " + start);
            } else {
                System.out.println("INVALID CITY press enter");
                ls.nextLine();
            } 
            
        }
        
        
        //at this point our map is setup and our start point is created WOO
        
        
    }
    
}

//this object will do all the work like normal
class AStarTree{
    ASTNode root;
    Map map;
    public AStarTree(ASTNode r, Map m){
        this.root = r;
        this.map = m;
    }
    
}

//the nodes used in the AStarTree
class ASTNode{
    //the node should point to its parent node
    ASTNode parent;
    
    //the node should know its children's psuedo root
    ASTNode child;
    
    //the node hsould know its siblings
    ASTNode leftSib;
    ASTNode rightSib;
    
    //the node should know which city it represents
    Node city;
    
    //the node should know its g(n) aka cost so far
    Double gcost;
    
    //creates an ASTNode with no childen
    public ASTNode(ASTNode p, ASTNode r, ASTNode l, Node c, double g){
        this.parent = p;
        this.rightSib = r;
        this.leftSib = l;
        this.city = c;
        gcost = g;
        
    }
    
    public void setChild(ASTNode c){
        this.child = c;
    }
    
    public void expand(Map m){
        //we cannot expand a node if its child is null
        if(child != null){
            throw new IllegalArgumentException();
        }
        
        ASTNode nul = null;
        ASTNode left = null;
        ASTNode curr = null;
        //at this point we can create the children based on the edges of the nodes
        for(int i = 0; i < city.getEdges().length; i++){
            //grab the node associated with the edge name
            Node n = (Node) m.get(city.getEdges()[i].to);
            
            //create a new ASTNode based on this node as the parent, the right nodes
            //null, the left is the left node and the g(n) will be this gcost plus
            //the cost to traverse the edge
            curr = new ASTNode(this, nul, left,n,this.gcost + city.getEdges()[i].distance);
            
            //if the left node is not null the make its right sibling the current 
            //node
            if(left != null){
                left.rightSib = curr;
                
            //if the left node is null then the curr node is the child this node
            //should point to for the children
            } else {
                child = curr;
           }
        }
        //at this point we have taken each edge from the node and expaneded them 
        //into children of this node. Each child contains its parent, the node 
        //it represents, and pointers to its siblings 
    }
    
    public boolean expaneded(){
        return child != null;
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
    
    public Edge[] getEdges(){
        return edges;
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