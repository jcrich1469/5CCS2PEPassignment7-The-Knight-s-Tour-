// Part 1 about finding and counting Knight's tours
//==================================================
//object HelloWorld {//REMOVE THIS AFTERWARDS

//def main(args: Array[String]) {

//println("Hello, world!")

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions// A Path is ((x,y)...) pos is a coordinate
//val p = Pos 3,3;
//def value p = (1,0)
//(1a) ComDEFplete the function that tests whether the position 
// is inside the board and not yet element in the path.
//println(takePair((2,2)));
//def takePair(p:Pos):Pos ={p}
//println(takePath(List((2,2),(5,5))));
//def takePath(path:Path):Path = {path}
//val pa = ((1,1),(2,2));
// takes in a dimension, a List of positions,borard not yet... and a position.
def is_legal(dim: Int, path: Path)(x: Pos): Boolean = (x._1 >= 0 && x._2 >= 0 && x._1 < dim && x._2 < dim) && !path.contains(x);
	// dim 0-3 or 1-4??? assume 0-3
	// 1. check valid  position inside the board
	// 2. is the position inside the path yet??? hopefully not...
	//dim is the board dimension ??? 4,4 = 4??? 
//val lst = List[(String)]

//val p1 = Pos(1,1)
/*
println(is_legal(4,List((1,1),(2,2)))((3,3)));
println(is_legal(4,List((1,1),(3,3)))((3,3)));
println(is_legal(4,List((1,1),(3,3)))((6,6)));
*/
// is the square inside the board, and is it not in the knights' path??? yet.




//(1b) Complete the function that calculates for a position 
// all legal onward moves that are not already in the path. 
// The moves should be ordered in a "clockwise" order.

def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2)) if(is_legal(dim,path)(posX+x._1,posY+x._2))) yield (posX+x._1,posY+x._2);
//{ 

	//val numListY : List[Int] = (2,1,-1,-2,-2,-1,1,2);
	//val numListX : List[Int] = (1,2,2,1,-1,1,-2,-2,1);
	// = List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2))
	//val incPos : List[Pos] = 
		//if(is_legal)
		//add these to the x Position... check and then add.
	// 
	/*
	for()
		if(is_legal(4,path)(pos))
	}
	*/
	// clearly a loop her: 
	
	//val xNum : Int num = x._1;
	//val yNum : Int num = x._1;
	//if(is_legal(4,path)((x._1+1,x._2+2)))// x(+1,+2) 
	//val ur : Pos = (1,1);
	/*
	pos = pos(+2,+1)
	pos = pos(+2,-1)
	pos = pos(+1,-2)
	pos = pos(-1,-2)
	pos = pos(-2,-1)
	pos = pos(-2,+1)
	pos = pos(-1,+2)
	*/
	//incPos;
	// posList : List[Pos] = List(ur,(2,2),(3,3));
	//incPos;
// not in
//}

//println(legal_moves(5,List((1,1),(2,2)),(4,4)));

assert(legal_moves(8, Nil, (2,2)) ==   List((3,4), (4,3), (4,1), (3,0), (1,0), (0,1), (0,3), (1,4)))
//println(legal_moves(8, Nil, (2,2)));
assert(legal_moves(8, Nil, (7,7)) == List((6,5), (5,6)))
//println(legal_moves(8, Nil, (7,7)));
assert(legal_moves(8, List((4,1), (1,0)), (2,2)) ==  List((3,4), (4,3), (3,0), (0,1), (0,3), (1,4)))
//println(legal_moves(8, List((4,1), (1,0)), (2,2)));
assert(legal_moves(8, List((6,6)), (7,7)) == List((6,5), (5,6)))
//println(legal_moves(8, List((6,6)), (7,7)));

//(1c) Complete the two recursive functions below. 
// They exhaustively search for open tours starting from the 
// given path. The first function counts all possible open tours, 
// and the second collects all open tours in a list of paths.


//1. have a position inside a list of paths...assume for now, (((0,0))), make sure that you start from every single position.
//2. we look at possible onward positions for each position.
//3. we assume that this position is part of a correct solution and add the () position to our current Path.
//4. we move to that position and continue recursively for each available position, doing one path at a time.
//5. we know we have an open tour, when a path in our tour contains every single position on the board, there are no legal moves left within the 
// board and the current position is outside of the range of the first move...
//6. afterwrds we can count the size of the list...
// open if first pos is not reachable.
def count_tours(dim: Int, path: Path): Int = {
	//var c = path
	if(path.length == dim*dim){

		if(!legal_moves(dim,path,path.head).contains(path.tail)){
			
			1;	
	
			
		} else {
			
			0;			
					
		}

	} else {

		val l = for(xPos <- legal_moves(dim,path,path.head)) yield {// l here is automatically recognised as a list of ints. i.e. {32,4... etc}
		// it understands we are returning int's so makes it into a list of ints, adding an extra dimension.
			
			count_tours(dim, xPos::path);	
			
		}
		
		l.sum;// sum of the list of ints.
		
	}
}
// enumTours.size....????
// depends on number of recursive calls.
	//enum_Tours(5,);

def enum_tours(dim: Int, path: Path): List[Path] = {// list of paths ((()))

		//
	if(path.length == dim*dim){

		if(!legal_moves(dim,path,path.head).contains(path.tail)){

			// add;
			List(path)
					
		} else {
			// do not add.	
						
			List()
		}

	} else {

		val l = for(xPos <- legal_moves(dim,path,path.head)) yield {
			
			enum_tours(dim, xPos::path);	
			
		}
		
		l.flatten;// flatten the extra dimension we were given. It returns a List of paths so it makes a List of a List of paths.
		
	}
		
}

//println(count_tours(5, List((0,0))));
/*
var sum : Int = 0;
for(x <- 0 to 4){
	
	for(y <- 0 to 4){
		
		//sum += count_tours(5, List((x,y)))
		sum += enum_tours(5, List((x,y))).length;
	}
	
	
}
*/

//println(sum);
//println(enum_tours(5, List((0,0))).length);
//var lists = List();

//println(lists::List(List()));
//}
//}
