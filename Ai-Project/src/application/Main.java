
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import application.Graph.Edge;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	ArrayList<vertex> v = new ArrayList<>();
	ArrayList<vertex> vButton = new ArrayList<>();
	HashMap<String, vertex> name = new HashMap<>();
	  Map<String, String> parents = new HashMap<>();
	   Line line;
	Pane pp = new Pane();
	double imageWidth = 0;
	double imageHeight = 0;
	Circle marker;
	String path = "";
	TextField tt2;
	ComboBox<String> c = new ComboBox<String>();
	ComboBox<String> c2 = new ComboBox<String>();
	Graph g=new Graph();
	vertex v1 = null;
	vertex v2 = null;
	TextArea tt = new TextArea();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(10,10,10,10));
			
			 root.setStyle("-fx-background-color:LIGHTBLUE");

				ImageView a2 = new ImageView("palMAPP.jpg");
				a2.setFitHeight(800);
				a2.setFitWidth(600);
				Button b2 = new Button(null, a2);
				b2.setAlignment(Pos.CENTER);
				pp.getChildren().add(b2);
				root.setCenter(pp);
			Scene scene = new Scene(root,1550, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		
			
		File myObj = new File("C:\\Users\\hp\\Desktop\\cities.csv");
			if (myObj.length() != 0) {

				try {
					Scanner myReader = new Scanner(myObj);
					while (myReader.hasNext()) {
						String data = myReader.nextLine();
						g.addVertex(data);
						 
				}
					myReader.close();
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				
				
				File myObj2 = new File("C:\\\\Users\\\\hp\\\\Desktop\\\\Roads.csv");
				if (myObj2.length() != 0) {

					try {
						Scanner myReaderr = new Scanner(myObj2);
						while (myReaderr.hasNext()) {
							 
							String data = myReaderr.nextLine();
							data=data.trim();
							System.out.println(data);
							String[] tokens = data.split(",");
							System.out.println(tokens[0]);
							g.addEdge(tokens[0],tokens[1], Double.parseDouble(tokens[2]));
							 
					}
						g.print();
						myReaderr.close();
					} catch (FileNotFoundException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}
				
				}
			}
			
			System.out.println("..............................................");
			File myObj3 = new File("C:\\\\Users\\\\hp\\\\Desktop\\\\AirDistance.csv");
			if (myObj3.length() != 0) {

				try {
					Scanner myReader = new Scanner(myObj3);
					while (myReader.hasNext()) {
						String data = myReader.nextLine();
						System.out.println(data);
						String[] tokens = data.split(",");
						//g.AddAirDistance(tokens[0],tokens[1],Double.parseDouble(tokens[2]));
						g.addheur(tokens[0],tokens[1],Double.parseDouble(tokens[2]));
						
						
				}
					 g.print1();
					myReader.close();
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			
			
		}
		
			File myObj4 = new File("C:\\\\Users\\\\hp\\\\Desktop\\\\cities_longlat.txt");
			if (myObj4.length() != 0) {

				try {
					Scanner myReader = new Scanner(myObj4);
					while (myReader.hasNext()) {
						String data = myReader.nextLine();
						
						String[] tokens = data.split(":");
						vertex ver = new vertex(tokens[0], Double.parseDouble(tokens[1]),
								Double.parseDouble(tokens[2]));
						v.add(ver);
						name.put(tokens[0], ver);
						 
				}
					myReader.close();
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			
			
		}
			Circle[] button = new Circle[v.size()];
			HashMap<Circle, String> ss = new HashMap<>();
			for (int k = 0; k < v.size(); k++) {
				
				double longitude = v.get(k).getLongitude();
				double latitude = v.get(k).getLatitude();
				imageWidth = a2.getFitWidth();
				imageHeight = a2.getFitHeight();

			
				double y=(latitude/919) * (800+10);

				double x=(longitude/585)* (600+10);
				button[k] = new Circle(x, y, 4, Color.BLACK);

				pp.getChildren().addAll(button[k]);
				ss.put(button[k], v.get(k).getName());

			}
   

			for (Circle buttonn : button) {
				int index = Arrays.asList(button).indexOf(buttonn);

				buttonn.setOnMouseClicked(event -> {
					System.out.println("Button " + index + " clicked");

					String country = ss.get(buttonn);
				
					vertex v = name.get(country);
					vButton.add(v);
					
					 if (vButton.size() == 1) {
						c.setValue(country);
						}
					 else {
						 c2.setValue(country);
							}
						

				});
			}

			c.setMaxSize(200, 100);
			c.setStyle("-fx-font-size: 15pt;");
			
			List<String> items = new ArrayList<>();
			for (int i = 0; i <v.size(); i++) {
			    items.add(v.get(i).getName());
			}
			Collections.sort(items);
			
			for (String item : items) {
			    c.getItems().add(item);
			}
			
			
			
			Label l1 = new Label("Source");
			l1.setFont(Font.font(17));
			l1.setFont(Font.font(null, FontWeight.BOLD, l1.getFont().getSize()));
			Label l2 = new Label("Target");
			l2.setFont(Font.font(17));
			l2.setFont(Font.font(null, FontWeight.BOLD, l2.getFont().getSize()));
			
			c2.setMaxSize(200, 100);
			c2.setStyle("-fx-font-size: 15pt;");
			
			for (String item : items) {
			    c2.getItems().add(item);
			}
			
			HBox h=new HBox();
			

			Button aStar = new Button("A*");
			aStar.setFont(Font.font(15));
			aStar.setFont(Font.font(null, FontWeight.BOLD, aStar.getFont().getSize()));
			aStar.setMaxSize(100, 100);
			
			Button bfs = new Button("BFS");
			bfs.setFont(Font.font(15));
			bfs.setFont(Font.font(null, FontWeight.BOLD, bfs.getFont().getSize()));
			bfs.setMaxSize(100, 100);
			h.getChildren().addAll(aStar,bfs);
			h.setSpacing(2);

			HBox h2 = new HBox();
			Label l3 = new Label("Path:");
			l3.setFont(Font.font(17));
			l3.setFont(Font.font(null, FontWeight.BOLD, l3.getFont().getSize()));
		
			tt.setMaxSize(250, 250);
			tt.setFont(Font.font(20));
			h2.getChildren().addAll(l3, tt);

			Label l4 = new Label("Distance:");
			l4.setFont(Font.font(17));
			l4.setFont(Font.font(null, FontWeight.BOLD, l4.getFont().getSize()));
			 tt2 = new TextField();
			tt2.setFont(Font.font(20));
			
			Button b3 = new Button("Clear");
			b3.setFont(Font.font(15));
			b3.setFont(Font.font(null, FontWeight.BOLD, b3.getFont().getSize()));
			b3.setMaxSize(100, 100);
			GridPane gg = new GridPane();
		
			gg.setPadding(new Insets(5,5,5,5));
			gg.setVgap(10);
			
			Label ll=new Label("Palestine Map");
			ll.setFont(Font.font(25));
			ll.setAlignment(Pos.CENTER);
			ll.setFont(Font.font("Arial",FontWeight.BOLD, FontPosture.ITALIC,25));
			gg.add(ll, 1, 0);
			gg.add(l1, 0, 1);
			gg.add(c, 1, 1);

			gg.add(l2, 0, 2);
			gg.add(c2, 1, 2);
			gg.add(h, 1, 3);

			gg.add(l3, 0, 4);
			gg.add(tt, 1, 4);

			gg.add(l4, 0, 5);
			gg.add(tt2, 1, 5);
			gg.add(b3,1,6);
			gg.setAlignment(Pos.TOP_LEFT);
			root.setLeft(gg);
		
			
			bfs.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

				
					String s1 = c.getValue();
					String s2 = c2.getValue();
					System.out.println("hi");
					if (s1 != null && s2 != null) {
						v1 = name.get(s1);
						v2 = name.get(s2);
					} else if (vButton.size() == 2) {
					
						v1 = vButton.get(0);
						c.setValue(v1.getName());
						v2 = vButton.get(1);
						c2.setValue(v2.getName());
					}
					if (v1 != null && v2 != null) {
						tt2.setText(String.valueOf(BFS(v1.getName(),v2.getName())));
						
						if(tt2.getText().compareToIgnoreCase("0")!=0) {
					
						
					     	List<String> path=reconstructPath(v2.getName());
					     	int i=0;
					     	while(path.size()!=i) {
					     		tt.appendText(path.get(i)+"\n");
					     		i++;
					     	}
						
						
						drawLine(v2,reconstructPath(v2.getName()));
					}
					}

				}
			});
			aStar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					vertex v1 = null;
					vertex v2 = null;
					String s1 = c.getValue();
					String s2 = c2.getValue();
					
					if (s1 != null && s2 != null) {
						v1 = name.get(s1);
						v2 = name.get(s2);
					} else if (vButton.size() == 2) {
						v1 = vButton.get(0);
						c.setValue(v1.getName());
						v2 = vButton.get(1);
						c2.setValue(v2.getName());
					}
					if (v1 != null && v2 != null) {
						tt2.setText(String.valueOf(BFS(v1.getName(),v2.getName())));
						
						if(tt2.getText().compareToIgnoreCase("-1.0")!=0) {
							tt2.setText(String.valueOf(g.findShortestDistance(v1.getName(), v2.getName())));
							g.printPath (v1.getName() , v2.getName());
							drawpath( v1.getName(),v2.getName());
					}
					}

					
				}
			});
			
			b3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

					v1=null;
					v2=null;
				c.setValue(null);
				c2.setValue(null);
				tt.setText("");
				tt2.setText("");
				pp.getChildren().clear();
				vButton.clear();

				ImageView a2 = new ImageView("palMAPP.jpg");
				a2.setFitHeight(800);
				a2.setFitWidth(600);
				Button b2 = new Button(null, a2);
				b2.setAlignment(Pos.CENTER_LEFT);
				pp.getChildren().add(b2);


				Circle[] button = new Circle[v.size()];
				HashMap<Circle, String> ss = new HashMap<>();
				for (int k = 0; k < v.size(); k++) {

				double longitude = v.get(k).getLongitude();
				double latitude = v.get(k).getLatitude();
				imageWidth = a2.getFitWidth();
				imageHeight = a2.getFitHeight();
				double y=(latitude/919) * (800+10);

				double x=(longitude/585)* (600+10);
				button[k] = new Circle(x, y, 4, Color.BLACK);

				pp.getChildren().addAll(button[k]);
				ss.put(button[k], v.get(k).getName());

				}
				for (Circle buttonn : button) {
					int index = Arrays.asList(button).indexOf(buttonn);

					buttonn.setOnMouseClicked(event -> {
						System.out.println("Button " + index + " clicked");

						String country = ss.get(buttonn);
						vertex v = name.get(country);
						vButton.add(v);
						
						 if (vButton.size() == 1) {
							c.setValue(country);
							}
						 else {
							 c2.setValue(country);
								}
							

					});
				}
				
				}
				});
			for (Circle buttonn : button) {
				int index = Arrays.asList(button).indexOf(buttonn);

				buttonn.setOnMouseClicked(event -> {
					System.out.println("Button " + index + " clicked");

					String country = ss.get(buttonn);
				
					vertex v = name.get(country);
					vButton.add(v);
					
					 if (vButton.size() == 1) {
						c.setValue(country);
						}
					 else {
						 c2.setValue(country);
							}
						

				});
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void drawLine(vertex Destination, List<String> path) {
	    List<vertex> vertices = new ArrayList<>();
	    for (String vertexName : path) {
	        vertex v = name.get(vertexName);
	        vertices.add(v);
	    }

	    for (int i = 0; i < vertices.size() - 1; i++) {
	        vertex u = vertices.get(i);
	        vertex v = vertices.get(i + 1);

	        Line line = new Line();
	        line.setStroke(Color.DARKRED);
	        line.setStrokeWidth(2);

	        line.setStartX(Xvalue(u.longitude));
	        line.setStartY(Yvalue(u.latitude));
	        line.setEndX(Xvalue(v.longitude));
	        line.setEndY(Yvalue(v.latitude));

	        // Add the line to the scene graph
	        pp.getChildren().add(line);
	    }}
	
	

	public double BFS(String source, String destination) {
	    Set<String> visited = new HashSet<>();
	    Queue<String> queue = new LinkedList<>();
	    Map<String, Double> distances = new HashMap<>();

	    vertex s = name.get(source);
	    s.setPrevious(null);
	    visited.add(source);
	    queue.add(source);
	    distances.put(source, 0.0);
	    parents.put(source, null);

	    while (!queue.isEmpty()) {
	        String node = queue.poll();
	        if (node.equals(destination)) {
	            return distances.get(node);
	        }

	        List<Edge> neighbors = g.adjList.get(node);

	        if (neighbors != null) {
	            for (Edge neighbor : neighbors) {
	                if (!visited.contains(neighbor.getDestination())) {
	                    visited.add(neighbor.getDestination());
	                    queue.add(neighbor.getDestination());
	                    double distanceToNeighbor = distances.get(node) + neighbor.weight;
	                    distances.put(neighbor.getDestination(), distanceToNeighbor);
	                    parents.put(neighbor.getDestination(), node);
	                    vertex neighborVertex = name.get(destination);
	                  neighborVertex.setPrevious(name.get(node));
	                }
	            }
	        }
	    }

	    return -1.0; // means that the Destination is not found
	}

 List<String> reconstructPath(String destination) {
    List<String> path = new ArrayList<>();
    String current = destination;
    while (current != null) {
        path.add(current);
        current = parents.get(current);
    }
    Collections.reverse(path);
    return path;
}

public List<String> getPathBFS(String source, String destination) {
    List<String> path = new ArrayList<>();
    String current = destination;
    while (!current.equals(source)) {
        path.add(0, current);
        current = parents.get(current);
    }
    path.add(0, source);
    return path;
}
public double Xvalue(double longitude) {
double x=(longitude/585)* (600+10);

return x;
}

public double Yvalue(double latitude) {	
	double y=(latitude/919) * (800+10);
return y;

}
public void drawpath(String source, String goal) {
	List<String> path=g.printPath(source, goal);
	tt.setFont(Font.font(15));
	tt.setFont(Font.font(null, FontWeight.BOLD, tt.getFont().getSize()));
	int j=0;
	double x1=0;
	double y1=0;
	if(path.size()>1) {
	for (int i = 0; i <path.size();++i) {
	j=i+1;
	tt.appendText(path.get(i)+"\n");
	   if(j<path.size()) {
	    String cor= search (path.get(i));
	    String [] token=cor.split(",");
 	    System.out.println(cor);
	    x1=Double.parseDouble(token[0]);
	    y1=Double.parseDouble(token[1]);
		double y=(y1/919) * (800+10);
		double x=(x1/585)* (600+10);
	    String corr= search (path.get(j));
	   Line line=new Line();
	   String[] token2=corr.split(",");
	    double x2=Double.parseDouble(token2[0]);
	    double y2=Double.parseDouble(token2[1]);
	    double yy=(y2/919) * (800+10);
		double xx=(x2/585)* (600+10);
	     line.setStartX(x);
	    line.setStartY(y);
	    line.setEndX(xx);
	   line.setEndY(yy);
	   line.setStroke(Color.BLACK);
	   line.setStrokeWidth(2);
	   pp.getChildren().add(line);
		}
	}

	}else {
		tt.appendText("There is no path"+"\n");

	}
	

}
	public String search(String m) {
		String lon="";
		for(int i=0;i<v.size();++i) {
			if(m.compareToIgnoreCase(v.get(i).name)==0) {
				lon=String.valueOf(v.get(i).getLongitude())+","+String.valueOf(v.get(i).getLatitude());	
			}
		}
		return lon;
		
	}


}