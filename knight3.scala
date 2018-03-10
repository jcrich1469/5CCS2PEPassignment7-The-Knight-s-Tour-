// Part 3 about finding a single tour using the Warnsdorf Rule
//=============================================================

// copy any function you need from files knight1.scala and
// knight2.scala
import scala.annotation.tailrec;
type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

//(3a) Complete the function that calculates a list of onward
// moves like in (1b) but orders them according to the Warnsdorf’s 
// rule. That means moves with the fewest legal onward moves 
// should come first.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = (x._1 >= 0 && x._2 >= 0 && x._1 < dim && x._2 < dim) && !path.contains(x);

def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2)) if(is_legal(dim,path)(posX+x._1,posY+x._2))) yield (posX+x._1,posY+x._2);

def ordered_moves(dim: Int, path: Path, x: Pos): List[Pos] = {
//val moves= legal_moves(dim,path,x)
	
 	val receivedMoves = legal_moves(dim,path,x);


	receivedMoves.sortWith((a,b)=>sortFunction(dim,path,a,b));

	/*
	val countList2 = for(position <- r) yield{
		
		legal_moves(dim,r,position).length		
		
	}*/
	//println(countList2);
	//receivedMoves.sortWith((a,b)=>legal_moves(dim,path,a).length<legal_moves(dim,path,b).length);
	// just working with position
	
	// list of legal moves
	
	// : List[Path] do not do, too much trouble CAn just omit.
	// should ideally be a sorted List of positions.
	
	//val warnsdorffList = for( position <- receivedMoves) yield
		
		//legal_moves(dim,x::path,position);//.length			
	
	

	//println(receivedMoves);
		
	//println("check the path sizes");
	//warnsdorffList.sortBy(x => x.length);// x is a path and sorted by the path's length

	//println(warnsdorffList);
	
	//println(receivedMoves)
	//receivedMoves.sortBy(position => legal_moves(dim,x::path,x).length);
	//receivedMoves.sortWith((x,y) =>legal_moves(dim,x::path,x).length < legal_moves(dim,x::path,x).length);
	
	/*
	for(r <- receivedMoves){
		
		print(legal_moves(dim,r::path,r).length);		
			
	}
	println();*/
	//receivedMoves.sortWith((x,y) =>legal_moves(dim,path,x).length < legal_moves(dim,path,y).length);
	/*
	for(r <- receivedMoves){
		
		print(legal_moves(dim,r::path,r).length);		
			
	}
	println();*/
	//recievedMoves = recievedMoves.sortWith((x, y) => x.length < y.length)
	//warnsdorffList
	//receivedMoves;
}

def sortFunction(dim:Int, path:Path, x:Pos, y:Pos)={
	
	legal_moves(dim,path,x).length<legal_moves(dim,path,y).length
	
}

//println(ordered_moves(8,List(),(4,4)))
//println(ordered_moves(8,List(),(1,3)))
//(3b) Complete the function that searches for a single *closed* 
// tour using the ordered moves function.

def first(xs: List[Pos], f: Pos => Option[Path]): Option[Path] = xs match{
	
	case Nil => None;
	case position::xs => {
		val value = f(position)
		if(value != None){
			
			value;
		
		} else {
			
			first(xs,f);
		}
	}
}

def first_closed_tour_heuristic(dim: Int, path: Path): Option[Path] = {
	
	if(path.length == dim*dim){

		//println(path.last);
		//println(path.head);	
		
		//val list = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2))
		val rangeList = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2))) yield (posX+path.head._1,posY+path.head._2);
						
		//println(path);
		if(rangeList.contains(path.last)){
		
			//println(rangeList);
			//println("Inside legal moves"+path.head);
				
			Option(path);
						
			
		} else {
			
			None;								
			
		}
		
		//Option(path);
	} else {
		
		//first(legal_moves(dim,path,path.head), f=>first_tour(dim,path));// do not use.
		first(ordered_moves(dim,path,path.head),(position:Pos)=>first_closed_tour_heuristic(dim,position::path));
		// was just doing for each unordered position
		// 

	}	
}
//println("getting tour");
/*
for(x<- 0 to 5){
	for(y <- 0 to 5){
	
		if(!(x == 0 && y == 0) && !(x == 5 && y == 5) && !(x == 0 && y == 5) && !(x == 5 && y == 0)){
			first_closed_tour_heuristic(6,List((x,y)));
			println("done x:"+x+"and y:"+y);
		}
	}
}
*/
//println(first_closed_tour_heuristic(6,List((4,4))));
//val path = List((5,4),(3,3));
//println(legal_moves(6,path,path.head));


//val list = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2)))yield (posX+path.head._1,posY+path.head._2);

//println(list);

//println(list.contains(path.last));

//(3c) Same as (3b) but searches for *open* tours.



def first_tour_heuristic(dim: Int, path: Path): Option[Path] = {

	ftCall(dim,List(path))

}



@tailrec def ftCall(dim : Int, pathList: List[Path]): Option[Path] = pathList match{
	// an adaptation from lecture three of scala, the coin problem.
	case Nil => None;
	case q::qs => 
		
		
   		if (dim*dim == q.size) Some(q);

		else ftCall(dim, ordered_moves(dim,q,q.head).map(_::q) ::: qs)
}
/*

def searchT(total: Int, coins: List[Int], 
            acc_cs: List[List[Int]]): Option[List[Int]] = acc_cs match {
  case Nil => None
  case x::xs => 
    if (total < x.sum) searchT(total, coins, xs)
    else if (x.sum == total) Some(x) 
    else searchT(total, coins, coins.filter(_ > 0).map(_::x) ::: xs)
}	
}
*/

//val dim : Int = 50;
//val path = List((4,3),(5,5));
//println(legal_moves(6,path,path.head));


//val listRange = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2)))yield (posX+path.head._1,posY+path.head._2);

//println(listRange);

//println(!listRange.contains(path.last));
//println(first_tour_heuristic(dim,List((dim/2,dim/2))));
