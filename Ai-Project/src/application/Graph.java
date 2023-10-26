package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Graph {


	  HashMap<String,List<Edge>> adjList=new HashMap<>();
	  HashMap<String, Map<String, Double>> heuristicValues=new HashMap<>();
	  public void print() {
	    	for (Entry<String, List<Edge>> entry : adjList.entrySet()) {
	    	
	             System.out.println("Key: " + entry.getKey() +" is ");
	    	      List<Edge> edgeList = entry.getValue();
	    	      for (Edge edge : edgeList) {
	    	          System.out.println("Destination: " + edge.getDestination() + ", Weight: " + edge.getWeight());
	    	      }
	    	      }
	    	      System.out.println();   
	    	    }
	  
	  public void print1() {
		  for (Map.Entry<String, Map<String, Double>> entry : heuristicValues.entrySet()) {
			    String key = entry.getKey();
			    Map<String, Double> innerMap = entry.getValue();

			    System.out.println("Key: " + key);
			    System.out.println("Values: " + innerMap);
			}  
	    	    }
	  
	 
	    	
	  public void addheur(String Source,String Destination,double AirDistance) {
		  if (heuristicValues.containsKey(Source)) {
	            Map<String, Double> distances = heuristicValues.get(Source);
	            distances.put(Destination,AirDistance);
	        } else {
	            Map<String, Double> distances = new HashMap<>();
	            distances.put(Destination, AirDistance);
	            heuristicValues.put(Source, distances);
	        }
	    }
		 
	  
	  
	  
	 
	    public void addVertex(String vertex) {
	    	 adjList.putIfAbsent(vertex,new LinkedList<>());
	       
	    }
	  
	    static Map<String, String> visited = new HashMap<>();

	    public double findShortestDistance(String source, String goal) {
	       visited = new HashMap<>();
	        PriorityQueue<Node> Queue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.total));
	        Queue.add(new Node(source, 0, getHeuristicValue(source, goal)));
	        while (!Queue.isEmpty()) {
	            Node current = Queue.poll();
	            String currentCity = current.city;

	            if (currentCity.equals(goal)) {
	                return current.distance;
	            }

	            List<Edge> adjascents = adjList.get(currentCity);
	            if (adjascents != null) {
	                for (int i = 0; i < adjascents.size(); ++i) {
	                    double distance = adjascents.get(i).weight;
	                    double newdistance = current.distance + distance;
	                    double heur = getHeuristicValue(adjascents.get(i).destination, goal);
	                    double fScore = newdistance + heur;
	                    if (visited.containsKey(adjascents.get(i).destination)) {
	                        continue; 
	                    }
	                    Queue.add(new Node(adjascents.get(i).destination, newdistance,heur));
	                    visited.put(adjascents.get(i).destination, currentCity);
	                }
	            }
	        }
	        return -1.0;
	    }
	    private double getHeuristicValue(String city, String goal) {
	        Map<String, Double> sourceMap = heuristicValues.get(city);
	       
	        if (sourceMap != null) {
	        	
	            Double heuristicValue = sourceMap.get(goal);
	            System.out.println(city+"--------------------"+heuristicValue );
	            if (heuristicValue != null) {
	                return heuristicValue;
	            }
	        }
	       
	        return -1.0;
	    }
	  

	    public static List<String>printPath(String source, String goal) {
	        List <String> path=new ArrayList<>();
	        String current=goal;
	        if(visited.containsKey(current)) {
	        	while((current.compareToIgnoreCase(source)!=0)) {
	        	path.add(current);
	        	current=visited.get(current);
	        	}
	        path.add(source);
	        Collections.reverse(path);
	        }
	        System.out.println("Shortest Path:");
	        for (String city : path) {
	            System.out.print(city + " -> ");
	        }
	        
	        return path;
	        
	    }


	    
	    
	    
	   
	        	
	    public void addEdge(String source, String destination, double weight) {
	     
	    	 List<Edge> edges = adjList.get(source);
	    	    
	    	    if (edges == null) {
	    	        edges = new ArrayList<>();
	    	        adjList.put(source, edges);
	    	    }

	    	    Edge edge = new Edge(destination, weight);
	    	    edges.add(edge);
	        
	    }
	    
	    private static class Node {
	        private String city;
	        private double distance;
	        private double heur;
	        private double total;

	        public Node(String city, double distance, double heur) {
	            this.city = city;
	            this.distance = distance;
	            this.heur = heur;
	            this.total = distance + heur;
	        }
	    }
	    
	    
	    
	    
     
	   
	    

	    class Edge {
	        String destination;
	       double weight;

	        public Edge(String destination, double weight) {
	            this.destination = destination;
	            this.weight = weight;
	        }
	      

			public String getDestination() {
				return destination;
			}

			public void setDestination(String destination) {
				this.destination = destination;
			}

			public double getWeight() {
				return weight;
			}

			
			public void setWeight(double weight) {
				this.weight = weight;
			}
	        
	    }
	    
	    
	    
	}
