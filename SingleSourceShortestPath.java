import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/***
 * INPUT
 * 1.input :numOftops , numOftedges , startPoint
 * 2.loop : sum of edge
 *   input: s , t:edge of top (s -> t)   , d: weight of the edge(s -> t)
 *
 * OUTPUT
 * sum of weight WHEN Single Source Shortest Path
 *
 */

/***
 *
 *
 */
public class SingleSourceShortestPath extends StudyAbstract{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int numOfTops = sc.nextInt();
		final int numOtEdges = sc.nextInt();
		final int startPoint = sc.nextInt();

		ArrayList<InputData> data = new ArrayList<InputData>();

		for (int i = 0 ;i < numOtEdges ; i++){
			InputData input = new InputData();
			input.setTop1(sc.nextInt());
			input.setTop2(sc.nextInt());
			input.setWeightOfEdge(sc.nextInt());
			data.add(input);
		}
		sc.close();
		// define initial map
		Map<Integer,Node> map = getInitialPath(numOfTops ,startPoint);

		//calc shortest path
		calcShortestPath(data , numOfTops ,startPoint,map);

	}
	private static Map<Integer,Node> getInitialPath(int numOfTops,int startPoint) {
		Map<Integer,Node> initialMap = new HashMap<Integer,Node>();
		for(int i = 0;i < numOfTops; i++){
			Node node = new Node();
			if(i == startPoint){
				node.costFromStart = 0;
				node.isDone = true;
			}
			initialMap.put(i, node);
		}
		return initialMap;
	}
	/**
	 * 1. find node which has smallest distance from start node
	 * 2.
	 */


	/***
	 * この関数でやりたいこと
	 * データを一つずつ読み込んでくる
	 * 最初のノードからデータの数だけ順番に処理を行う
	 * 1.今見ているノードから出ている辺を探す
	 * 2.現在の最短距離と見つけた辺の距離を比べる
	 * 3.短かったら竿短距離を更新する
	 * 4.更新したノードについて1−3の処理を行う
	 * 5.全部のノードに対して上記を行ったら終了
	 *
	 */
	private static void calcShortestPath(ArrayList<InputData> datalist ,int numOfTops,int searchPoint, Map<Integer, Node> map){
		boolean hasNext = true;
		while(hasNext){
			int nextNode=0;
			int isNeighborNode = 0;
			for(InputData data :datalist){
				if(data.getTop1() == searchPoint){
					isNeighborNode = data.getTop2();
					int cost = data.getWeightOfEdge() + map.get(searchPoint).getCostFromStart();
					if(cost < map.get(isNeighborNode).getCostFromStart()){
						//updete the shortest node
						map.get(isNeighborNode).setCostFromStart(cost);
					}
				}
			}
			int min = Integer.MAX_VALUE;
			for(int j=0;j<numOfTops;j++){
				if(map.get(j).getCostFromStart()< min && map.get(j).isDone == false){
					min = map.get(j).getCostFromStart();
					nextNode = j;
				}
			}
			if(min == Integer.MAX_VALUE ) break;
			map.get(nextNode).isDone = true;
			searchPoint = nextNode;
		}
		for(int i=0;i<map.size();i++){
			System.out.println((map.get(i).getCostFromStart() != Integer.MAX_VALUE ? map.get(i).getCostFromStart() : "INF"));
		}
	}

	//	private static int findNeighborNode(int data1,int data2,int comparison){
	//		if(data1 == comparison){
	//			return Integer.valueOf(data2);
	//		}else if(data2 == comparison){
	//			return Integer.valueOf(data1);
	//		}else{
	//			return Integer.valueOf(-1);
	//		}
	//	}
}
class Node{
	boolean isDone;
	int costFromStart;
	Node(){
		isDone = false;
		costFromStart = Integer.MAX_VALUE;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public int getCostFromStart() {
		return costFromStart;
	}
	public void setCostFromStart(int costFromStart) {
		this.costFromStart = costFromStart;
	}
}


class InputData{
	private int top1;
	public int getTop1() {
		return top1;
	}
	public void setTop1(int top1) {
		this.top1 = top1;
	}
	public int getTop2() {
		return top2;
	}
	public void setTop2(int top2) {
		this.top2 = top2;
	}
	public int getWeightOfEdge() {
		return weightOfEdge;
	}
	public void setWeightOfEdge(int weightOfEdge) {
		this.weightOfEdge = weightOfEdge;
	}
	private int top2;
	private int weightOfEdge;

}