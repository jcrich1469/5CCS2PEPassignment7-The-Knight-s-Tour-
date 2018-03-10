// Part 2 about finding a single tour for a board
//================================================

// copy any function you need from file knight1.scala

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

//(2a) Implement a first-function that finds the first 
// element, say x, in the list xs where f is not None. 
// In that case return f(x), otherwise none.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = (x._1 >= 0 && x._2 >= 0 && x._1 < dim && x._2 < dim) && !path.contains(x);

def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = for((posX,posY)<-List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2)) if(is_legal(dim,path)(posX+x._1,posY+x._2))) yield (posX+x._1,posY+x._2);


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

//(2b) Implement a function that uses the first-function for
// trying out onward moves, and searches recursively for an 
// *open* tour on a dim * dim-board.

def first_tour(dim: Int, path: Path): Option[Path] = {
	
	if(path.length == dim*dim){
	
		Option(path);

	} else {
		
		//first(legal_moves(dim,path,path.head), f=>first_tour(dim,path));// do not use.
		first(legal_moves(dim,path,path.head), (position:Pos)=>first_tour(dim,position::path));
	}

}

//println(first_tour(5,List((0,0))))
 
